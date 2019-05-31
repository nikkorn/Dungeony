package com.dumbpug.dungeony.session.character.player;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.session.character.LevelCharacter;
import com.dumbpug.dungeony.session.level.ILevelPositionedEntity;
import com.dumbpug.dungeony.session.level.LevelPositionedEntityType;
import com.dumbpug.dungeony.session.level.Position;

/**
 * Represents a player character in the context of a single level.
 */
public class Player extends LevelCharacter {
	/**
	 * The player id.
	 */
	private String playerId;

	/**
	 * Creates a new instance of the Player class.
	 * @param position The initial player position.
	 * @param playerId The player id.
	 */
	public Player(Position position, String playerId) {
		super(position);
		this.playerId = playerId;
	}
	
	/**
	 * Gets the player id.
	 * @return The player id.
	 */
	public String getPlayerId() {
		return this.playerId;
	}

	@Override
	public float getSize() {
		return Constants.CHARACTER_SIZE_MEDIUM;
	}

	@Override
	public float getMovementSpeed() {
		return Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM;
	}
}
