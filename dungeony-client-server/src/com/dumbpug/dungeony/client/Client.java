package com.dumbpug.dungeony.client;

import java.io.IOException;
import com.dumbpug.dungeony.client.lobby.LobbyState;
import com.dumbpug.dungeony.client.session.SessionState;
import com.dumbpug.dungeony.input.ClientKeyInputState;
import com.dumbpug.dungeony.lobby.Colour;
import com.dumbpug.dungeony.networking.QueuedMessageReader;
import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.networking.messaging.MessageOutputStream;
import com.dumbpug.dungeony.networking.messaging.MessageQueue;
import com.dumbpug.dungeony.networking.messaging.messages.ClientKeyInputStateChanged;
import com.dumbpug.dungeony.networking.messaging.messages.LobbySetSlotColour;
import com.dumbpug.dungeony.networking.messaging.messages.LobbySetSlotReady;

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
	 * The processor of messages sent to the client.
	 */
	private ClientMessageProcessor clientMessageProcessor = new ClientMessageProcessor(this);
	/**
	 * The client key input state.
	 */
	private ClientKeyInputState clientKeyInputState = new ClientKeyInputState();
	/**
	 * The state of the active session, or null if the client is not in an active session.
	 */
	private LobbyState lobbyState = new LobbyState();
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
	 * Gets the lobby state.
	 * @return the lobby state.
	 */
	public LobbyState getLobbyState() {
		// Call refresh to ensure we can provide a fresh value.
		this.refresh();
				
		return lobbyState;
	}
	
	/**
	 * Gets the active session state.
	 * @return The lobby state, or null if there is no active session
	 */
	public SessionState getActiveSessionState() {
		// Call refresh to ensure we can provide a fresh value.
		this.refresh();
				
		return this.sessionState;
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
	 * Sets whether the client is ready to join an active session.
	 * @param isReady Whether the client is ready to join an active session.
	 */
	public void setReady(boolean isReady) {
		sendMessage(new LobbySetSlotReady(isReady));
	}
	
	/**
	 * Sets the colour for the client lobby slot.
	 * @param colour The colour for the client lobby slot.
	 */
	public void setSlotColour(Colour colour) {
		sendMessage(new LobbySetSlotColour(colour));
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
		// Update the key input state and get whether the state has actually changed.
		boolean keyInputStateChanged = this.clientKeyInputState.update(
				isPrimaryKeyDown, 
				isSecondaryKeyDown, 
				isTertiaryKeyDown, 
				isUpKeyDown, 
				isDownKeyDown, 
				isLeftKeyDown, 
				isRightKeyDown);
		
		// If the input state has changed then we will need to notify the server.
		if (keyInputStateChanged) {
			sendMessage(new ClientKeyInputStateChanged(this.clientKeyInputState));
		}
	}
	
	/**
	 * Refresh the client with the connected server instance, processing any messages received from the server since the last refresh.
	 */
	public void refresh() {
		// There is nothing to do if there are no pending messages queued.
		if (!queuedMessageReader.hasQueuedMessages()) {
			return;
		}
		
		// Get the pending messages in the input queue.
		MessageQueue queue = queuedMessageReader.getQueuedMessages();

		// Process every message in the input message queue.
		while (queue.hasNext()) {
			this.clientMessageProcessor.process(queue.next());
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
