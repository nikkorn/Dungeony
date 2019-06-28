package com.dumbpug.dungeony.client.lobby;

import com.dumbpug.dungeony.lobby.Colour;

/**
 * A client-side representation of server-side lobby slot state.
 */
public class LobbySlotState {
	/**
	 * The slot index.
	 */
	private int index;
	/**
	 * The name of the player of the client occupying this slot.
	 */
	private String playerName = null;
	/**
	 * Whether the client is ready to join a session.
	 */
	private boolean isReady = false;
	/**
	 * The colour of the lobby slot.
	 */
	private Colour colour = Colour.NOT_SET;
	
	/**
	 * Creates a new instance of the LobbySlotState class.
	 * @param index The lobby slot index.
	 */
	public LobbySlotState(int index) {
		this.index = index;
	}

	/**
	 * Gets the index of the lobby slot.
	 * @return The index of the lobby slot.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the name of the player of the client occupying this slot.
	 * @return The name of the player of the client occupying this slot, or null if unoccupied.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Sets the name of the player of the client occupying this slot.
	 * @param playerName The name of the player of the client occupying this slot, or null if unoccupied.
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Gets whether the client occupying the slot is ready to join a session.
	 * @return Whether the client occupying the slot is ready to join a session.
	 */
	public boolean isReady() {
		return isReady;
	}

	/**
	 * Sets whether the client occupying the slot is ready to join a session.
	 * @return isReady Whether the client occupying the slot is ready to join a session.
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	/**
	 * Gets the colour of the lobby slot.
	 * @return The colour of the lobby slot.
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * Sets the colour of the lobby slot.
	 * @param colour The colour of the lobby slot.
	 */
	public void setColour(Colour colour) {
		this.colour = colour;
	}
}
