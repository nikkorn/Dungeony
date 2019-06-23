package com.dumbpug.dungeony.networking.messaging.messages;

import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.session.level.Position;

/**
 * The message sent to all clients when a player spawns.
 */
public class PlayerSpawned implements IMessage {
	/**
	 * The id of the spawning client.
	 */
	private int clientId;
	/**
	 * The position of the spawning client.
	 */
	private Position spawnPosition;
	/**
	 * The angle of view of the client.
	 */
	private Short angleOfView;
	
	/**
	 * Create a new instance of the PlayerSpawned class.
	 * @param clientId The id of the client.
	 * @param position The position of the spawning client.
	 * @param angleOfView The angle of view of the spawning client.
	 */
	public PlayerSpawned(int clientId, Position position, Short angleOfView) {
		this.clientId      = clientId;
		this.spawnPosition = position;
		this.angleOfView   = angleOfView;
	}
	
	/**
	 * Get the id of the spawning client.
	 * @return The id of the spawning client.
	 */
	public int getClientId() {
		return this.clientId;
	}

	/**
	 * Get the position of the spawning player.
	 * @return The position of the spawning player.
	 */
	public Position getSpawnPosition() {
		return this.spawnPosition;
	}

	/**
	 * Get the angle of view of the spawning client.
	 * @return The angle of view of the spawning client.
	 */
	public Short getSpawnAngleOfView() {
		return this.angleOfView;
	}
	
	@Override
	public int getTypeId() {
		return MessageIdentifier.PLAYER_SPAWNED;
	}
}
