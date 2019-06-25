package com.dumbpug.dungeony.client;

import java.io.IOException;
import java.net.Socket;
import com.dumbpug.dungeony.input.ClientKeyInputState;
import com.dumbpug.dungeony.networking.QueuedMessageReader;
import com.dumbpug.dungeony.networking.messaging.DungeonyMarshallerProviderFactory;
import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.networking.messaging.MessageInputStream;
import com.dumbpug.dungeony.networking.messaging.MessageMarshallerProvider;
import com.dumbpug.dungeony.networking.messaging.MessageOutputStream;
import com.dumbpug.dungeony.networking.messaging.messages.JoinFailure;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;

/**
 * A client-side representation of a Dungeony server client.
 */
public class Client {
	/**
	 * The player id of the client.
	 */
	private String playerId;
	/**
	 * The queued message reader used to read and queue messages sent from the server.
	 */
	private QueuedMessageReader queuedMessageReader;
	/**
	 * The message output stream for the client to use in sending messages to the server.
	 */
	private MessageOutputStream messageOutputStream;
	/**
	 * The client key input state.
	 */
	private ClientKeyInputState clientInputState = new ClientKeyInputState();
	
	/**
	 * Creates a new instance of the Client class.
	 * @param playerId The player id of the client.
	 * @param queuedMessageReader The queued message reader used to read and queue messages sent from the server.
	 * @param messageOutputStream The message output stream for the client to use in sending messages to the server.
	 */
	private Client(String playerId, QueuedMessageReader queuedMessageReader, MessageOutputStream messageOutputStream) {
		this.playerId            = playerId;
		this.queuedMessageReader = queuedMessageReader;
		this.messageOutputStream = messageOutputStream;
	}

	/**
	 * Connect to a remote server instance and return a Client instance representing the connection.
	 * @param host The host address.
	 * @param port The host port.
	 * @param playerId The player id of the client.
	 * @return A Client instance representing the connection.
	 * @throws IOException
	 * @throws ServerJoinRequestRejectedException Thrown when the request to join the server is rejected.
	 */
	public static Client connect(String host, int port, String playerId) throws IOException, ServerJoinRequestRejectedException {
		// Create the socket on which to connect to the server.
		Socket connectionSocket = new Socket(host, port);
		
		// Create the message marshaller provider for our message stream.
		MessageMarshallerProvider marshallerProvider = DungeonyMarshallerProviderFactory.create();
		
		// Create the message output stream used to write messages to the server.
		MessageOutputStream messageOutputStream = new MessageOutputStream(connectionSocket.getOutputStream(), marshallerProvider);
		
		// Wait for the response form the server. We are expecting either a join success or failure.
		// Firstly, we need to create our message input stream in order to grab the server response.
		MessageInputStream messageInputStream = new MessageInputStream(connectionSocket.getInputStream(), marshallerProvider);
		
		// Send the handshake string that the server expects.
		messageOutputStream.writeUTF("JOIN_REQUEST");
		
		// Send the client player id.
		messageOutputStream.writeUTF(playerId);
		
		// Read the server response message. This operation blocks until we get it.
		IMessage response = messageInputStream.readMessage();
		
		// We got a response from the server!
		switch (response.getTypeId()) {
			case MessageIdentifier.JOIN_SUCCESS:
				// The server sent us a message to let us know we successfully joined!
				// Firstly, create the queued message reader that will be used by the server proxy.
				QueuedMessageReader queuedMessageReader = new QueuedMessageReader(messageInputStream);
				
				// Next, create the actual client instance.
				Client client = new Client(playerId, queuedMessageReader, messageOutputStream);
				
				// Lastly, our queued message reader needs to start reading incoming messages.
				Thread messageReaderThread = new Thread(queuedMessageReader);
				messageReaderThread.setDaemon(true);
				messageReaderThread.start();
				
				// We are finished, return the successfully created client.
				return client;
			case MessageIdentifier.JOIN_FAIL:
				// The server sent us a message to let us know we failed to join!
				throw new ServerJoinRequestRejectedException(((JoinFailure)response).getReason());
			default:
				throw new RuntimeException("Received unexpected response from server.");
		}
	}
	
	/**
	 * Get whether we are still connected with the server.
	 * @return Whether we are still connected with the server.
	 */
	public boolean isConnected() {
		// For now, we will check whether we are still connected by checking if our reader is still connected.
		return this.queuedMessageReader.isConnected();
	}
	
	/**
	 * Refresh the client, processing any messages received from the server since the last refresh.
	 */
	public void refresh() {
		
	}
	
	/**
	 * Update the client input state, notifying the server of any changes.
	 * @param isPrimaryKeyDown
	 * @param isSecondaryKeyDown
	 * @param isTertiaryKeyDown
	 * @param isUpKeyDown
	 * @param isDownKeyDown
	 * @param isLeftKeyDown
	 * @param isRightKeyDown
	 */
	public void updateKeyInputState(
		boolean isPrimaryKeyDown, 
		boolean isSecondaryKeyDown, 
		boolean isTertiaryKeyDown,
		boolean isUpKeyDown,
		boolean isDownKeyDown,
		boolean isLeftKeyDown,
		boolean isRightKeyDown
	) {
		// Get the last known client input state as a packed integer.
		int oldPackedInputState = this.clientInputState.toPackedInt();
		
		// Update the client input state.
		this.clientInputState.setPrimaryKeyDown(isPrimaryKeyDown);
		this.clientInputState.setSecondaryKeyDown(isSecondaryKeyDown);
		this.clientInputState.setTertiaryKeyDown(isTertiaryKeyDown);
		this.clientInputState.setUpKeyDown(isUpKeyDown);
		this.clientInputState.setDownKeyDown(isDownKeyDown);
		this.clientInputState.setLeftKeyDown(isLeftKeyDown);
		this.clientInputState.setRightKeyDown(isRightKeyDown);
		
		// Get the new client input state as a packed integer.
		int newPackedInputState = this.clientInputState.toPackedInt();
		
		// If the input state has chnaged then we will need to notify the server.
		if (oldPackedInputState != newPackedInputState) {
			
		}
	}
}
