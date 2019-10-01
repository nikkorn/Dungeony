package com.dumbpug.dungeony.session.character.player;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.session.character.LevelCharacter;
import com.dumbpug.dungeony.session.level.LevelTransition;
import com.dumbpug.dungeony.session.level.Position;

/**
 * Represents a player character in the context of a single level.
 */
public class Player extends LevelCharacter {
	/**
	 * The id of the participant that the player represents.
	 */
	private int participantId;
	/**
	 * The level transition for the player.
	 */
	private LevelTransition levelTransition = LevelTransition.NONE;

	/**
	 * Creates a new instance of the Player class.
	 * @param participantId The id of the participant that the player represents.
	 * @param position The initial player position.
	 */
	public Player(int participantId, Position position) {
		super(position);
		this.participantId = participantId;
	}
	
	/**
	 * Gets the participant id.
	 * @return The participant id.
	 */
	public int getParticipantId() {
		return participantId;
	}

	@Override
	public float getSize() {
		return Constants.CHARACTER_SIZE_MEDIUM;
	}

	@Override
	public float getMovementSpeed() {
		return Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM;
	}

	/**
	 * Gets the level transition for the player.
	 * @return The level transition for the player.
	 */
	public LevelTransition getLevelTransition() {
		return levelTransition;
	}

	/**
	 * Sets the level transition for the player.
	 * @param levelTransition The level transition for the player.
	 */
	public void setLevelTransition(LevelTransition levelTransition) {
		this.levelTransition = levelTransition;
	}
}
