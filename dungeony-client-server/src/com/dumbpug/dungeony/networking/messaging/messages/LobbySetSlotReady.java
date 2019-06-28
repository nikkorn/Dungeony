package com.dumbpug.dungeony.networking.messaging.messages;

import com.dumbpug.dungeony.networking.messaging.IMessage;

/**
 * A message sent to the server whenever the client requests to set a lobby slot ready state.
 */
public class LobbySetSlotReady implements IMessage  {
	/**
	 * The slot ready state.
	 */
	private boolean isReady;
	
	/**
	 * Create a new instance of the LobbySetSlotReady class.
	 * @param isReady The slot ready state.
	 */
	public LobbySetSlotReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	/**
	 * Gets slot ready state.
	 * @return The slot ready state.
	 */
	public boolean isReady() {
		return this.isReady;
	}

	@Override
	public int getTypeId() {
		return MessageIdentifier.LOBBY_SET_SLOT_READY;
	}
}
