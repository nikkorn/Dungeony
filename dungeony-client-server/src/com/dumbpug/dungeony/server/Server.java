package com.dumbpug.dungeony.server;

import com.dumbpug.dungeony.Constants;

/**
 * The server that hosts dungeony game sessions.
 */
public class Server {
	/**
	 * The server state.
	 */
	private ServerState state = ServerState.LOBBY;
	
	// TODO Add list of ConnectedClient instances.
	
	// TODO Add the active session
	
	// TODO Add the server state (lobby/session)
	
	/**
	 * Create a new instance of the Server class.
	 * @param port The port.
	 */
	public Server(int port) {
		System.out.println("###################### Dungeony Server #######################");
		System.out.println("##############################################################");
		System.out.println("version: " + Constants.VERSION);
		System.out.println("##############################################################");
		
		// Create a server clock and pass it a new instance of Server.
		ServerClock serverClock = new ServerClock(this);
		
		// Start the server clock.
		serverClock.start();
	}
	
	/**
	 * Program entry point.
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		// Load the server configuration from disk.
		Configuration serverConfig = Configuration.loadFromDisk();
		
		// Create a Server instance.
		new Server(serverConfig.getPort());
	}
	
	/**
	 * The server tick.
	 */
	public void tick() {
		System.out.println("Tick!");
		
		// TODO Handle any ConnectedClient instances that have disconnected.
		
		// TODO Check for and handle queued requests by clients to join the server.
		
		// TODO Process input messages from clients, some of which will update server-side representation of client control input.
		
		// TODO Tick the session.
		
		// TODO Process session output event queue.
	}
}
