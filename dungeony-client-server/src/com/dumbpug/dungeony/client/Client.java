package com.dumbpug.dungeony.client;

import java.io.IOException;
import com.dumbpug.dungeony.client.session.SessionState;
import com.dumbpug.dungeony.input.ClientKeyInputState;
import com.dumbpug.dungeony.networking.QueuedMessageReader;
import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.networking.messaging.MessageOutputStream;
import com.dumbpug.dungeony.networking.messaging.messages.ClientKeyInputStateChanged;

/**
 * A client-side representation of a Dungeony server client.
 */
public class Client {
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
	 * The state of the active session, or null if the client is not in an active session.
	 */
	private SessionState sessionState = null;
	
	/**
	 * Creates a new instance of the Client class.
	 * @param queuedMessageReader The queued message reader used to read and queue messages sent from the server.
	 * @param messageOutputStream The message output stream for the client to use in sending messages to the server.
	 */
	public Client(QueuedMessageReader queuedMessageReader, MessageOutputStream messageOutputStream) {
		this.queuedMessageReader = queuedMessageReader;
		this.messageOutputStream = messageOutputStream;
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
	 * Gets whether the client is in an active session.
	 * @return Whether the client is in an active session.
	 */
	public boolean isInSession() {
		// Call refresh to ensure we can provide a fresh value.
		this.refresh();
		
		// If we have client-side representation of session state then we can regard the client as being in-session.
		return sessionState != null;
	}
	
	/**
	 * Refresh the client with the connected server instance, processing any messages received from the server since the last refresh.
	 */
	public void refresh() {
		// TODO Check for any pending messages in the input message queue and do nothing if there are none.
		// TODO Process every message in the input message queue.
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
		
		// If the input state has changed then we will need to notify the server.
		if (oldPackedInputState != newPackedInputState) {
			sendMessage(new ClientKeyInputStateChanged(this.clientInputState));
		}
	}
	
	/**
	 * Send a message to the server.
	 * @param message The message to send.
	 */
	private void sendMessage(IMessage message) {
		try {
			messageOutputStream.writeMessage(message);
		} catch (IOException e) {
			System.out.println("failed to send message with id '" + message.getTypeId() + "' to server.");
		}	
	}
}
