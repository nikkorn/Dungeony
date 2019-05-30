package com.dumbpug.dungeony.session.level.tile.decoration;

import com.dumbpug.dungeony.session.level.Direction;

/**
 * Decoration that can be attached to a tile.
 */
public class Decoration {
	/**
	 * The type of the decoration.
	 */
	private DecorationType type;
	/**
	 * The direction of the tile.
	 */
	private Direction direction;
	
	/**
	 * Create a new instance of the Decoration class.
	 * @param type The decoration type.
	 * @param direction The decoration direction.
	 */
	public Decoration(DecorationType type, Direction direction) {
		this.type      = type;
		this.direction = direction;
	}
	
	/**
	 * Gets the type of the decoration.
	 * @return The type of the decoration.
	 */
	public DecorationType getType() {
		return this.type;
	}
	
	/**
	 * Gets the direction of the decoration.
	 * @return The direction of the decoration.
	 */
	public Direction getDirection() {
		return this.direction;
	}
}
