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
		// System.out.println("Server Tick!");
		
		// Tick the lobby, passing the active session (which will be null if there is no active session).
		this.lobby.tick(this.session);
		
		// If in session, do session specific stuff.
		if (this.state == ServerState.SESSION) {
			// TODO Process input messages from clients (not lobby messages), some of which will update server-side representation of client control input.
			
			// TODO Tick the session.
			
			// TODO Process session output event queue.
		}
	}
}
