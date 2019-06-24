package com.dumbpug.dungeony.server.lobby;

import com.dumbpug.dungeony.server.networking.ConnectedClient;

/**
 * A slot in a lobby that can be occupied by a client.
 */
public class LobbySlot {
	/**
	 * The slot index.
	 */
	private int index;
	/**
	 * The connected client that is taking up the lobby slot.
	 */
	private ConnectedClient client = null;
	/**
	 * Whether the client is ready to join a session.
	 */
	private boolean isReady = false;
	/**
	 * The colour of the lobby slot.
	 */
	private Colour colour = Colour.NOT_SET;
	
	/**
	 * Creates a new instance of the LobbySlot class.
	 * @param index The lobby slot index
	 */
	public LobbySlot(int index) {
		this.index = index;
	}
	
	/**
	 * Gets the slot index.
	 * @return The slot index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the connected client that occupies this slot.
	 * @return The connected client that occupies this slot.
	 */
	public ConnectedClient getClient() {
		return client;
	}

	/**
	 * Sets the connected client that occupies this slot.
	 * @param client the connected client that occupies this slot.
	 */
	public void setClient(ConnectedClient client) {
		this.client = client;
	}

	/**
	 * Gets whether the client is ready to join a session.
	 * @return whether the client is ready to join a session.
	 */
	public boolean isReady() {
		return isReady;
	}

	/**
	 * Sets whether the client is ready to join a session.
	 * @param isReady Whether the client is ready to join a session.
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	/**
	 * Gets the slot colour.
	 * @return The slot colour.
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * Sets the slot colour.
	 * @param colour The slot colour.
	 */
	public void setColour(Colour colour) {
		this.colour = colour;
	}
}
