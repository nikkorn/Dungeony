package com.dumbpug.dungeony.server.networking;

import java.io.IOException;
import com.dumbpug.dungeony.networking.QueuedMessageReader;
import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.networking.messaging.MessageInputStream;
import com.dumbpug.dungeony.networking.messaging.MessageOutputStream;
import com.dumbpug.dungeony.networking.messaging.MessageQueue;

/**
 * Represents a client connected to the server.
 */
public class ConnectedClient {
	/**
	 * The player id of the client.
	 */
	private String playerId;
	/**
	 * The status of the connected client.
	 */
	private ConnectedClientStatus status = ConnectedClientStatus.WAITING_TO_JOIN;
	/**
	 * The queued message reader for the client.
	 */
	private QueuedMessageReader queuedMessageReader;
	/**
	 * The message output stream for the client.
	 */
	private MessageOutputStream messageOutputStream;
	
	/**
	 * Creates a new instance of the ConnectedClient class.
	 * @param playerId The player id of the client.
	 * @param messageInputStream The message input stream for the client.
	 * @param messageOutputStream The message output stream for the client.
	 */
	public ConnectedClient(String playerId, MessageInputStream messageInputStream, MessageOutputStream messageOutputStream) {
		this.playerId            = playerId;
		this.messageOutputStream = messageOutputStream;
		this.queuedMessageReader = new QueuedMessageReader(messageInputStream);
		
		// Our queued message reader needs to start reading incoming messages.
		Thread messageReaderThread = new Thread(queuedMessageReader);
		messageReaderThread.setDaemon(true);
		messageReaderThread.start();
	}

	/**
	 * Gets the player id of the client.
	 * @return The player id of the client.
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * Sets the player id of the client.
	 * @param playerId The player id of the client.
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	/**
	 * Gets the status of the client.
	 * @return The status of the client.
	 */
	public ConnectedClientStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status of the client.
	 * @param status The status of the client.
	 */
	public void setStatus(ConnectedClientStatus status) {
		this.status = status;
	}
	
	/**
	 * Get a queue of any messages received from the client.
	 * @return A queue of any messages received from the client.
	 */
	public MessageQueue getReceivedMessageQueue() {
		return this.queuedMessageReader.getQueuedMessages();
	}
	
	/**
	 * Send a message to the client.
	 * @param message The message to send.
	 */
	public void sendMessage(IMessage message) {
		try {
			this.messageOutputStream.writeMessage(message);
		} catch (IOException e) {
			// We failed to send the message to the client, do nothing here as disconnected clients will be removed.
		}
	}
}
