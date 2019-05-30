package com.dumbpug.dungeony.session.character.enemy;

import com.dumbpug.dungeony.session.character.LevelCharacter;
import com.dumbpug.dungeony.session.level.Position;

/**
 * The base enemy class.
 */
public abstract class Enemy extends LevelCharacter {
	/**
	 * Creates a new instance of the Enemy class.
	 * @param position The initial enemy position.
	 */
	public Enemy(Position position) {
		super(position);
	}
}