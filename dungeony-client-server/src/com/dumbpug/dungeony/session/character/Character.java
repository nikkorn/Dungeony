package com.dumbpug.dungeony.session.character;

import com.dumbpug.dungeony.session.level.Position;

/**
 * A character entity.
 */
public class Character {
	/**
	 * The position of the character.
	 */
	private Position position;
	
	/**
	 * Creates a new instance of the character class.
	 * @param position The initial character position.
	 */
	public Character(Position position) {
		this.position = position;
	}
	
	/**
	 * Gets the character position.
	 * @return The character position.
	 */
	public Position getPosition() {
		return this.position;
	}
}