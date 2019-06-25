package com.dumbpug.dungeony.server.lobby;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.dumbpug.dungeony.networking.messaging.DungeonyMarshallerProviderFactory;
import com.dumbpug.dungeony.networking.messaging.MessageInputStream;
import com.dumbpug.dungeony.networking.messaging.MessageMarshallerProvider;
import com.dumbpug.dungeony.networking.messaging.MessageOutputStream;
import com.dumbpug.dungeony.networking.messaging.messages.JoinFailure;
import com.dumbpug.dungeony.networking.messaging.messages.JoinSuccess;
import com.dumbpug.dungeony.server.networking.ConnectedClient;
import com.dumbpug.dungeony.server.networking.ConnectedClientStatus;
import com.dumbpug.dungeony.session.Session;

/**
 * The game lobby.
 * Hosts clients and prepares sessions.
 */
public class Lobby {
	/**
	 * The lobby slots.
	 */
	@SuppressWarnings("serial")
	private ArrayList<LobbySlot> slots = new ArrayList<LobbySlot>() {{
		add(new LobbySlot(1));
		add(new LobbySlot(2));
		add(new LobbySlot(3));
		add(new LobbySlot(4));
	}};
	
	/**
	 * Tick the lobby.
	 * @param activeSession The active session, or null if no session is active. 
	 */
	public void tick(Session activeSession) {
		// Handle any ConnectedClient instances that have disconnected.
		processClientDisconnections();
		
		// If there is an active session then ...
		if (activeSession != null) {
			// ... do some stuff, like maybe if any clients are waiting to join just send them a message to tell them to wait.
		} else {
			// There is no active session!
			
			// Handle any client requests to join the lobby as there is no active session that they have to wait to finish.
			processClientJoinRequests();
			
			// TODO Process any messages from any clients that relate ONLY to the lobby, such as:
			//  - Changes to the client 'ready' state.
			//  - Changes to the select client colour.
		}
	}
	
	/**
	 * Start listening for clients connecting to the lobby.
	 */
	public void startListeningForConnections(int port) {
		try {
			// Create the ServerSocket instance on which we will accept new connections.
			final ServerSocket serverSocket = new ServerSocket(port);
			
			// Create a new thread on which to sit and listen for client connections.
			Thread connectionListenerThread = new Thread(new Runnable() {
				@Override
				public void run() {
					// We will just listen forever.
					while(true) {
						// Sit here and listen here for a connection attempt.
						try {
							// Block and wait for a client socket.
							Socket clientSocket = serverSocket.accept();
							
							// Create the message marshaller provider for our message stream.
							MessageMarshallerProvider marshallerProvider = DungeonyMarshallerProviderFactory.create();
							
							// Create a message input stream for the new client.
							MessageInputStream messageInputStream = new MessageInputStream(clientSocket.getInputStream(), marshallerProvider);
							
							// We expect a string to be sent by the client, which will be 'JOIN_REQUEST'
							String initalMessage = messageInputStream.readUTF();
							
							// We got a message from the client! Check that it was expected handshake string.
							if (!initalMessage.equals("JOIN_REQUEST")) {
								// Close the client socket to kill the connection with the client, the client will know it is not wanted.
								clientSocket.close();
								
								// Spit out some some sensible output to the server console.
								System.out.println("Client attempted connection but failed to send expected string.");
							}
							
							// Process the new client connection.
							processNewClientConnection(clientSocket, messageInputStream, marshallerProvider);
						} catch (IOException e) {
							// An IO exception was thrown in accepting a new client connection.
							// In this case just give up and go back to listening for new connections.
							continue;
						}
					}
				}
			});
			
			// This connection listener thread should not prevent the server from stopping.
			connectionListenerThread.setDaemon(true);
			
			// Start listening for client connections.
			connectionListenerThread.start();
		} catch (BindException e) {
			// We failed to bind to the socket!
			System.out.println("We failed to bind to the socket. Is a server already running?");
		} catch (IOException e) {
			// An IO exception was thrown in creating a new ServerSocket instance.
			e.printStackTrace();
		}
	}
	
	/**
	 * Process a new connection for a client that has provided a valid handshake string and player id.
	 * @param clientSocket The client socket.
	 * @param messageInputStream The client message input stream.
	 * @param marshallerProvider The message marshaller provider for our message stream.
	 */
	private void processNewClientConnection(Socket clientSocket, MessageInputStream messageInputStream, MessageMarshallerProvider marshallerProvider) {
		try {
			// Create a message output stream with which to send messages to the client.
			MessageOutputStream messageOutputStream = new MessageOutputStream(clientSocket.getOutputStream(), marshallerProvider);
			
			// We expect a string to be sent by the client, which will be the player id of the client.
			String clientPlayerId = messageInputStream.readUTF();
			
			synchronized (this.slots) {
				// Firstly, try to get a free lobby slot.
				LobbySlot nextFreeSlot = getNextFreeSlot();
				
				// If we have no free slot then we need to send the player a join rejection message.
				if (nextFreeSlot == null) {
					// Send the player a join rejection message.
					messageOutputStream.writeMessage(new JoinFailure("NO_FREE_SLOT"));
					
					messageOutputStream.close();
					
					// There is nothing left to do for this client.
					return;
				}
				
				// Create a server-side representation of the connected client.
				ConnectedClient client = new ConnectedClient(clientPlayerId, messageInputStream, messageOutputStream);
				
				// Set the client in the available lobby slot.
				nextFreeSlot.setClient(client);
			}
		} catch (IOException exception) {
			// Any IO exception caught here means that the client connection failed and there is nothing to do.
		}
	}
	
	/**
	 * Gets the next free lobby slot, or null if there isn't one.
	 * @return The next free lobby slot, or null if there isn't one.
	 */
	private LobbySlot getNextFreeSlot() {
		for (LobbySlot slot : this.slots) {
			if (slot.getClient() == null) {
				return slot;
			}
		}
		
		// There was no free slot.
		return null;
	}
	
	/**
	 * Process any client disconnections.
	 */
	private void processClientDisconnections() {
		synchronized (this.slots) {
			// Create a flag to set if the lobby state has changed as a result of a client disconnecting.
			boolean hasLobbyStateChanged = false;
			
			for (LobbySlot slot : this.slots) {
				// Try to get the client in this slot.
				ConnectedClient clientInSlot = slot.getClient();
				
				// Is there a client in the slot and are they waiting to join the lobby?
				if (clientInSlot != null && !clientInSlot.isConnected()) {
					// Reset the current slot so thta it can be allocated to any future clients.
					slot.reset();
					
					// We will need to send a lobby state update to all clients.
					hasLobbyStateChanged = true;
				}
			}
			
			// If the lobby state has changed we will need to send a lobby state update to all clients.
			if (hasLobbyStateChanged) {
				// TODO Send a lobby state update to all clients.
				System.out.println("Send a lobby state update to all clients");
			}
		}
	}

	/**
	 * Process any client join requests.
	 */
	private void processClientJoinRequests() {
		synchronized (this.slots) {
			// Create a flag to set if the lobby state has changed as a result of a client disconnecting.
			boolean hasLobbyStateChanged = false;
						
			for (LobbySlot slot : this.slots) {
				// Try to get the client in this slot.
				ConnectedClient clientInSlot = slot.getClient();
				
				// Is there a client in the slot and are they waiting to join the lobby?
				if (clientInSlot != null && clientInSlot.getStatus() == ConnectedClientStatus.WAITING_TO_JOIN) {
					// Send a lobby join success message to the client.
					clientInSlot.sendMessage(new JoinSuccess());
					
					// We will need to send a lobby state update to all clients.
					hasLobbyStateChanged = true;
				}
			}
			
			// If the lobby state has changed we will need to send a lobby state update to all clients.
			if (hasLobbyStateChanged) {
				// TODO Send a lobby state update to all clients.
				System.out.println("Send a lobby state update to all clients");
			}
		}
	}
}