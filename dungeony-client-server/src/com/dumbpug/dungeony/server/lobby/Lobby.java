package com.dumbpug.dungeony.server.lobby;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import com.dumbpug.dungeony.networking.messaging.DungeonyMarshallerProviderFactory;
import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.networking.messaging.MessageInputStream;
import com.dumbpug.dungeony.networking.messaging.MessageMarshallerProvider;
import com.dumbpug.dungeony.networking.messaging.MessageOutputStream;
import com.dumbpug.dungeony.networking.messaging.messages.JoinFailure;
import com.dumbpug.dungeony.networking.messaging.messages.LobbyStateUpdate;
import com.dumbpug.dungeony.server.networking.ConnectedClient;
import com.dumbpug.dungeony.session.Session;

/**
 * The game lobby.
 * Hosts clients and prepares sessions.
 */
public class Lobby {
	/**
	 * The collection of lobby slots.
	 */
	private LobbySlots slots = new LobbySlots();
	/**
	 * The lobby timer.
	 */
	private LobbyTimer timer = new LobbyTimer();
	
	/**
	 * Tick the lobby.
	 * @param activeSession The active session, or null if no session is active. 
	 */
	public void tick(Session activeSession) {
		// Lock on the slots collection as slots can be updated by the thread that processes joining clients.
		synchronized (this.slots) {
			// Create a flag that will be set in the event of any lobby slot state changes.
			boolean hasLobbyStateChanged = false;
			
			// We will need to reset any slots that are populated by disconnected clients.
			if (this.slots.resetDisconnectedSlots()) {
				// A lobby slot was reset as a result of a client disconnecting.
				hasLobbyStateChanged = true;
			}
			
			// If there is an active session then ...
			if (activeSession != null) {
				// ... do some stuff, like maybe if any clients are waiting to join just send them a message to tell them to wait.
			} else {
				// Handle any client requests to join the lobby as there is no active session that they have to wait to finish.
				if (this.slots.processJoinRequests()) {
					// A lobby slot was updated as a result of a client joining the lobby.
					hasLobbyStateChanged = true;
				}
				
				// Process any messages from any clients that relate ONLY to the lobby, such as:
				//  - Changes to the client 'ready' state.
				//  - Changes to the select client colour.
				if (this.slots.processLobbyTargetedMessages()) {
					// The lobby state was updated.
					hasLobbyStateChanged = true;
				}
			}
			
			// Get the last known lobby timer coutndown and the current one.
			Integer lastCountdown = this.timer.getLastCountdown();
			Integer countdown     = this.timer.getCountdown(this.slots.areAllReady() && !this.slots.areAllEmpty());
			
			// Check whether the lobby time value has changed.
			if (lastCountdown != countdown) {
				hasLobbyStateChanged = true;
			}
			
			// If the state of the lobby has changed in any way as part of this tick then we will have to notify the connected clients. 
			if (hasLobbyStateChanged) {
				sendMessage(new LobbyStateUpdate(this.slots.getSnapShot(), countdown));
			}
		}
	}
	
	/**
	 * Send the specified message to all clients.
	 * @param message The message to send to all clients.
	 */
	public void sendMessage(IMessage message) {
		for (int slotNumber = 1; slotNumber <= 4; slotNumber++) {
			sendMessage(message, slotNumber);
		}
	}
	
	/**
	 * Send a message to the client (if there is one) in the slot with the specified slot number.
	 * @param message The message to send.
	 * @param slotNumber The slot number.
	 */
	public void sendMessage(IMessage message, int slotNumber) {
		// Get the slot.
		LobbySlot slot = this.slots.getSlot(slotNumber);
		
		// Get the client for the slot.
		ConnectedClient client = slot.getClient();
		
		// Send the message to the client in the slot, if there is one.
		if (client != null) {
			client.sendMessage(message);
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
							
							System.out.println("Connection to lobby was made!");
							
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
				LobbySlot nextFreeSlot = slots.getNextFreeSlot();
				
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
}