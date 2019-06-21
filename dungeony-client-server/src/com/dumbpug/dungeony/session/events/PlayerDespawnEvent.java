package com.dumbpug.dungeony.session.events;

/**
 * An event raised when a player despawns at a position in a level.
 */
public class PlayerDespawnEvent implements ISessionEvent {
	/**
	 * The id of the client represented by the despawning player.
	 */
	private int clientId;
	/**
	 * The depth of the level at which the player is despawning.
	 */
	private int depth;
	
	/**
	 * Creates a new instance of the PlayerDespawnEvent class.
	 * @param id The id of the client represented by the despawning player.
	 * @param depth The depth of the level at which the player is despawning.
	 */
	public PlayerDespawnEvent(int clientId, int depth) {
		this.clientId = clientId;
		this.depth    = depth;
	}
	
	/**
	 * Gets the id of the client represented by the despawning player.
	 * @return The id of the client represented by the despawning player.
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

	@Override
	public SessionEvent getEvent() { return SessionEvent.PLAYER_DESPAWN; }
}
