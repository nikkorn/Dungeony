package com.dumbpug.dungeony.networking.messaging.messages;

import com.dumbpug.dungeony.input.ClientKeyInputState;
import com.dumbpug.dungeony.networking.messaging.IMessage;

/**
 * A message sent to the server whenever the input state of a player changes.
 */
public class ClientKeyInputStateChanged implements IMessage {
	/**
	 * The client key input state.
	 */
	private ClientKeyInputState clientInputState;
	
	/**
	 * Creates a new instance of the ClientKeyInputStateChanged class.
	 * @param clientInputState
	 */
	public ClientKeyInputStateChanged(ClientKeyInputState clientInputState) {
		this.clientInputState = clientInputState;
	}

	/**
	 * Gets the client input state.
	 * @return The client input state.
	 */
	public ClientKeyInputState getClientInputState() {
		return this.clientInputState;
	}
	
	@Override
	public int getTypeId() {
		return MessageIdentifier.CLIENT_KEY_INPUT_STATE_CHANGED;
	}
}
