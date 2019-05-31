package com.dumbpug.dungeony.session.level;

import com.dumbpug.dungeony.session.level.grid.IGridPositionedEntity;

public interface ILevelPositionedEntity extends IGridPositionedEntity {
	/**
	 * Gets the type of the level posiitoned entity.
	 * @return The type of the level posiitoned entity.
	 */
	public LevelPositionedEntityType getLevelPositionedEntityType();
	
	/**
	 * Gets whether this level positioned entity will collide (not be able to pass through) another entity.
	 * @param entity The other entity.
	 * @return Whether this level positioned entity will collide (not be able to pass through) another entity.
	 */
	public boolean collidesWith(ILevelPositionedEntity entity);
}
