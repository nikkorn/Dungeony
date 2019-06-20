package com.dumbpug.dungeony.server;

public class Server {
	/**
	 * The server state.
	 */
	private ServerState state = ServerState.LOBBY;
	
	// TODO Add list of ConnectedClient instances.
	
	// TODO Add the active session
	
	// TODO Add the server state (lobby/session)
	
	public void tick () {
		// TODO Handle any ConnectedClient instances that have disconnected.
		
		// TODO Check for and handle queued requests by clients to join the server.
		
		// TODO Process input messages from clients, some of which will update server-side representation of client control input.
		
		// TODO Tick the session.
		
		// TODO Process session output event queue.
	}
}
