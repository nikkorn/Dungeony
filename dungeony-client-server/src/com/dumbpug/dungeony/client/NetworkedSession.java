package com.dumbpug.dungeony.client;

import com.dumbpug.dungeony.Constants;

/**
 * Represents a proxy session that points towards a server instance that is hosting the actual game session.
 */
public class NetworkedSession {
	/**
	 * The server address (if applicable)
	 */
	private String address = "127.0.0.1";
	/**
	 * The server port (if applicable)
	 */
	private int port = Constants.DEFAULT_PORT;
	
	/**
	 * Create a new instance of the NetworkedSession class where the client will be hosting a server instance.
	 * @param port The server port.
	 */
	private NetworkedSession(int port) {
		this.port = port;
	}
	
	/**
	 * Create a new instance of the NetworkedSession class where the session will be connecting to a remote server instance.
	 * @param address The server address.
	 * @param port The server port.
	 */
	private NetworkedSession(String address, int port) {
		this.address = address;
		this.port    = port;
	}
}
