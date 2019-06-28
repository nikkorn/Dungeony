package com.dumbpug.dungeony.networking.messaging.messages;

import com.dumbpug.dungeony.lobby.Colour;
import com.dumbpug.dungeony.networking.messaging.IMessage;

/**
 * A message sent to the server whenever the client requests a lobby slot colour.
 */
public class LobbySetSlotColour implements IMessage {
	/**
	 * The slot colour.
	 */
	private Colour colour;
	
	/**
	 * Create a new instance of the LobbySetSlotColour class.
	 * @param colour The slot colour.
	 */
	public LobbySetSlotColour(Colour colour) {
		this.colour = colour;
	}
	
	/**
	 * Gets the colour.
	 * @return The colour.
	 */
	public Colour getColour() {
		return colour;
	}

	@Override
	public int getTypeId() {
		return MessageIdentifier.LOBBY_SET_SLOT_COLOUR;
	}
}
