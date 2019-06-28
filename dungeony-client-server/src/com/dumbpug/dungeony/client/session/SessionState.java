package com.dumbpug.dungeony.client.session;

import java.util.ArrayList;
import com.dumbpug.dungeony.client.level.LevelState;

/**
 * A client-side representation of server-side session state.
 */
public class SessionState {
	/**
	 * The session seed.
	 */
	private long seed;
	/**
	 * The list of active session levels.
	 */
	private ArrayList<LevelState> levels = new ArrayList<LevelState>();
	
	// TODO Add the session seed
	
	// TODO Add list of players that expose
	// - The player name.
	// - The player level depth.
	// - The player position.
	// - The player angle of view
	// - A flag defining whether the player is the clients player.
	
	/**
	 * Creates a new instance of the SessionState class.
	 * @param seed The session seed.
	 */
	public SessionState(long seed) {
		this.seed = seed;
	}

}
