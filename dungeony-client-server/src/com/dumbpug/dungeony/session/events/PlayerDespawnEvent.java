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
	 * Creates a new instance of the PlayerDespawnEvent class.
	 * @param id The id of the client represented by the despawning player.
	 */
	public PlayerDespawnEvent(int clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * Gets the id of the client represented by the despawning player.
	 * @return The id of the client represented by the despawning player.
	 */
	public int getClientId() {
		return this.clientId;
	}

	@Override
	public SessionEvent getEvent() { return SessionEvent.PLAYER_DESPAWN; }
}
