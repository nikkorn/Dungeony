package com.dumbpug.dungeony.server.networking;

/**
 * Represents a client connected to the server.
 */
public class ConnectedClient {
	/**
	 * The id of the client.
	 */
	private int id;
	/**
	 * The status of the connected client.
	 */
	private ConnectedClientStatus status = ConnectedClientStatus.WAITING_TO_JOIN;
	/**
	 * Whether the client is in the 'ready' state.
	 * This is used to determine when the session countdown should begin.
	 */
	private boolean isReady = false;
	
	// TODO Add output message queue.
	
	/**
	 * Creates a new instance of the ConnectedClient class.
	 * @param id The client id.
	 */
	public ConnectedClient(int id) {
		this.id = id;
	}
}
