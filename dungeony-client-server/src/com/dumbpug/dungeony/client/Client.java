package com.dumbpug.dungeony.client;

import com.dumbpug.dungeony.Constants;

/**
 * The game client.
 */
public class Client {
	/**
	 * The client mode.
	 */
	private ClientMode mode;
	/**
	 * The server address (if applicable)
	 */
	private String address = "127.0.0.1";
	/**
	 * The server port (if applicable)
	 */
	private int port = Constants.DEFAULT_PORT;
	
	/**
	 * Create a new instance of the Client class where the client will not be depending on a server instance.
	 */
	public Client() {
		// The client will not be depending on a server instance.
		this.mode = ClientMode.LOCAL;
	}
	
	/**
	 * Create a new instance of the Client class where the client will be hosting a server instance.
	 * @param port The server port.
	 */
	private Client(int port) {
		// The client will be hosting a server instance.
		this.mode = ClientMode.HOSTING;
		this.port = port;
	}
	
	/**
	 * Create a new instance of the Client class where the client will be connecting to a remote server instance.
	 * @param address The server address.
	 * @param port The server port.
	 */
	private Client(String address, int port) {
		// The client will be connecting to a remote server instance.
		this.mode    = ClientMode.REMOTELY_CONNECTING;
		this.address = address;
		this.port    = port;
	}
	
	/**
	 * Create a Client instance that is hosting a server instance.
	 * @param port The server port.
	 * @return A Client instance that is hosting a server instance.
	 */
	public static Client host(int port) {
		return new Client(port);
	}
	
	/**
	 * Create a Client instance that is connected to a remote server instance.
	 * @param address The server address.
	 * @param port The server port.
	 * @return A Client instance that is connected to a remote server instance.
	 */
	public static Client connect(String address, int port) {
		// TODO Attempt to connect to remote server.
		return new Client(address, port);
	}
}
