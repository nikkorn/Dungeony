package com.dumbpug.dungeony.session.character.player;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.session.SessionParticipant;
import com.dumbpug.dungeony.session.character.LevelCharacter;
import com.dumbpug.dungeony.session.level.LevelTransition;
import com.dumbpug.dungeony.session.level.Position;

/**
 * Represents a player character in the context of a single level.
 */
public class Player extends LevelCharacter {
	/**
	 * The session participant that the player represents.
	 */
	private SessionParticipant participant;
	/**
	 * The level transition for the player.
	 */
	private LevelTransition levelTransition = LevelTransition.NONE;

	/**
	 * Creates a new instance of the Player class.
	 * @param participant The session participant that the player represents.
	 * @param position The initial player position.
	 * @param playerId The player id.
	 */
	public Player(SessionParticipant participant, Position position) {
		super(position);
		this.participant = participant;
	}
	
	/**
	 * Gets the session participant that the player represents.
	 * @return The session participant that the player represents.
	 */
	public SessionParticipant getParticipant() {
		return this.participant;
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
