package com.dumbpug.dungeony.session.level.grid;

import com.dumbpug.dungeony.session.level.Position;

/**
 * Represents an entity that can be placed within a spatial grid.
 */
public interface IGridPositionedEntity {
	
	/**
	 * Gets the position of the entity.
	 * @return The position of the entity.
	 */
	public Position getPosition();
	
	/**
	 * Gets the width of the entity.
	 * @return The width of the entity.
	 */
	public float getWidth();
	
	/**
	 * Gets the height of the entity.
	 * @return The height of the entity.
	 */
	public float getHeight();
}
