package com.dumbpug.dungeony.lobby;

/**
 * Represents the state of a lobby slot.
 */
public class LobbySlotState {
	/**
	 * The slot index.
	 */
	private int index;
	/**
	 * The player id of the client occupying the slot, or null if it is empty.
	 */
	private String playerId;
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
	 * @param index The slot index.
	 * @param playerId The player id of the client occupying the slot, or null if it is empty.
	 * @param isReady Whether the client is ready to join a session.
	 * @param colour The colour of the lobby slot.
	 */
	public LobbySlotState(int index, String playerId, boolean isReady, Colour colour) {
		this.index    = index;
		this.playerId = playerId;
		this.isReady  = isReady;
		this.colour   = colour;
	}
	
	/**
	 * Gets the slot index.
	 * @return The slot index.
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Gets the player id of the client occupying the slot, or null if it is empty.
	 * @return The player id of the client occupying the slot, or null if it is empty.
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * Gets whether the client is ready to join a session.
	 * @return whether the client is ready to join a session.
	 */
	public boolean isReady() {
		return isReady;
	}

	/**
	 * Gets the slot colour.
	 * @return The slot colour.
	 */
	public Colour getColour() {
		return colour;
	}
}
