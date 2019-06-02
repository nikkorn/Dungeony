package com.dumbpug.dungeony.session.level;

import com.dumbpug.dungeony.session.level.grid.IGridPositionedEntity;

/**
 * Represents an entity that can collide with another entity.
 */
public interface ICollidableEntity extends IGridPositionedEntity {
	/**
	 * Gets the type of the collidable entity.
	 * @return The type of the collidable entity.
	 */
	CollidableEntityType getCollidableEntityType();
	
	/**
	 * Gets whether this collidable entity will collide (not be able to pass through) another entity.
	 * @param entity The other entity.
	 * @return Whether this collidable entity will collides (not be able to pass through) another entity.
	 */
	boolean collidesWith(ICollidableEntity entity);
}
