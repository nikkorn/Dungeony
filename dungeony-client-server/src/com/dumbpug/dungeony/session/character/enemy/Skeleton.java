package com.dumbpug.dungeony.session.character.enemy;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.session.character.LevelCharacter;
import com.dumbpug.dungeony.session.level.Position;

/**
 * A basic skeleton enemy.
 */
public class Skeleton extends LevelCharacter {

	/**
	 * Creates a new instance of the Skeleton class.
	 * @param position The initial character position.
	 */
	public Skeleton(Position position) {
		super(position);
	}
	
	@Override
	public float getSize() {
		return Constants.CHARACTER_SIZE_MEDIUM;
	}

	@Override
	public float getMovementSpeed() {
		return Constants.CHARACTER_MOVEMENT_SPEED_SLOW;
	}
}
