package com.dumbpug.dungeony.session.events;

import com.dumbpug.dungeony.session.level.Position;

/**
 * An event raised when a player spawns at a position in a level.
 */
public class PlayerSpawnEvent implements ISessionEvent {
	/**
	 * The id of the client represented by the spawning player.
	 */
	private int clientId;
	/**
	 * The depth of the level at which the player is spawning.
	 */
	private int depth;
	/**
	 * The position of the spawn.
	 */
	private Position spawnPosition;
	
	/**
	 * Creates a new instance of the PlayerSpawnEvent class.
	 * @param id The id of the client represented by the spawning player.
	 * @param depth The depth of the level at which the player is spawning.
	 * @param spawn The position of the spawn.
	 */
	public PlayerSpawnEvent(int clientId, int depth, Position spawnPosition) {
		this.clientId      = clientId;
		this.depth         = depth;
		this.spawnPosition = spawnPosition;
	}
	
	/**
	 * Gets the id of the client represented by the spawning player.
	 * @return The id of the client represented by the spawning player.
	 */
	public int getClientId() {
		return this.clientId;
	}
	
	/**
	 * Gets the level depth.
	 * @return The level depth.
	 */
	public int getDepth() {
		return this.depth;
	}

	/**
	 * Gets the position of the spawn.
	 * @return The position of the spawn.
	 */
	public Position getSpawnPosition() {
		return this.spawnPosition;
	}

	@Override
	public SessionEvent getEvent() { return SessionEvent.PLAYER_SPAWN; }
}
