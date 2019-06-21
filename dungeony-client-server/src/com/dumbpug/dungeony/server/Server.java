package com.dumbpug.dungeony.server;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.server.lobby.Lobby;
import com.dumbpug.dungeony.session.Session;

/**
 * The server that hosts dungeony game sessions.
 */
public class Server {
	/**
	 * The server lobby.
	 */
	private Lobby lobby;
	/**
	 * The server state.
	 */
	private ServerState state = ServerState.LOBBY;
	/**
	 * The active session.
	 */
	private Session session = null;
	
	/**
	 * Create a new instance of the Server class.
	 * @param port The port.
	 */
	public Server(int port) {
		System.out.println("###################### Dungeony Server #######################");
		System.out.println("##############################################################");
		System.out.println("version: " + Constants.VERSION);
		System.out.println("##############################################################");
		
		// Create a lobby to host any connecting clients.
		this.lobby = new Lobby();
		
		// Start the lobby listening for connecting clients.
		this.lobby.startListeningForConnections(port);
		
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
