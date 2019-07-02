package com.dumbpug.dungeony.client;

import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.networking.messaging.messages.LobbyStateUpdate;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;

/**
 * Processor or messages sent to the client.
 */
public class ClientMessageProcessor {
	/**
	 * The client.
	 */
	private Client client;
	
	/**
	 * creates a new instance of the ClientMessageProcessor class.
	 * @param client The client.
	 */
	public ClientMessageProcessor(Client client) {
		this.client = client;
	}
	
	/**
	 * Process the specified message.
	 * @param message The message to process.
	 */
	public void process(IMessage message) {
		switch (message.getTypeId()) {
			case MessageIdentifier.LOBBY_STATE_UPDATE:
				this.process((LobbyStateUpdate)message);
				break;
			
			default:
				throw new RuntimeException("unexpected message of type '" + message.getTypeId() + "' received by client");
		}
	}
	
	/**
	 * Process a LobbyStateUpdate message
	 * @param message The LobbyStateUpdate message.
	 */
	private void process(LobbyStateUpdate message) {
		// TODO Apply changes to client state.
		System.out.println("Got LobbyStateUpdate message at client");
	}
}
