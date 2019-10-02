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
	 * The position of the spawn.
	 */
	private Position spawnPosition;
	
	/**
	 * Creates a new instance of the PlayerSpawnEvent class.
	 * @param id The id of the client represented by the spawning player.
	 * @param spawn The position of the spawn.
	 */
	public PlayerSpawnEvent(int clientId, Position spawnPosition) {
		this.clientId      = clientId;
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
	 * Gets the position of the spawn.
	 * @return The position of the spawn.
	 */
	public Position getSpawnPosition() {
		return this.spawnPosition;
	}

	@Override
	public SessionEvent getEvent() { return SessionEvent.PLAYER_SPAWN; }
}
