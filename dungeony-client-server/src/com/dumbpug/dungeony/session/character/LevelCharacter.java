package com.dumbpug.dungeony.session.character;

import java.util.HashSet;
import java.util.Iterator;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.ICollidableEntity;
import com.dumbpug.dungeony.session.level.CollidableEntityType;
import com.dumbpug.dungeony.session.level.Position;
import com.dumbpug.dungeony.session.level.grid.SpatialGrid;

/**
 * A character within the context of a level.
 */
public abstract class LevelCharacter implements ICollidableEntity {
	/**
	 * The position of the character.
	 */
	private Position position;
	/**
	 * The angle of the characters view around the z axis.
	 */
	private double angleOfView = 0;
	
	/**
	 * Creates a new instance of the character class.
	 * @param position The initial character position.
	 */
	public LevelCharacter(Position position) {
		this.position = position;
	}
	
	/**
	 * Gets the character position.
	 * @return The character position.
	 */
	@Override
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Gets the width of the character.
	 * @return The width of the character.
	 */
	@Override
	public float getWidth() {
		return this.getSize();
	}

	/**
	 * Gets the height of the character.
	 * @return The height of the character.
	 */
	@Override
	public float getHeight() {
		return this.getSize();
	}
	
	/**
	 * Gets the characters angle of view around the z axis.
	 * @return The characters angle of view around the z axis.
	 */
	public double getAngleOfView() {
		return this.angleOfView;
	}
	
	/**
	 * Sets the characters angle of view around the z axis.
	 * @param value The characters angle of view around the z axis.
	 */
	public void setAngleOfView(double value) {
		this.angleOfView = value;
	}
	
	/**
	 * Gets the cardinal facing direction of the character based on the angle of view.
	 * @return The cardinal facing direction of the character based on the angle of view.
	 */
	public Direction getFacingDirection() {
		return Direction.fromAngle(this.angleOfView);
	}
	
	/**
	 * Gets the size of the character.
	 * @return The size of the character.
	 */
	public abstract float getSize();
	
	/**
	 * Gets the movement speed of the character.
	 * @return The movement speed of the character.
	 */
	public abstract float getMovementSpeed();

	/**
	 * Gets the type of the level positioned entity.
	 * @return The type of the level positioned entity.
	 */
	@Override
	public CollidableEntityType getCollidableEntityType() {
		return CollidableEntityType.CHARACTER;
	}

	/**
	 * Gets whether this level positioned entity will collide (not be able to pass through) another entity.
	 * @param entity The other entity.
	 * @return Whether this level positioned entity will collide (not be able to pass through) another entity.
	 */
	@Override
	public boolean collidesWith(ICollidableEntity entity) {
		switch (entity.getCollidableEntityType()) {
			case CHARACTER:
				return true;
			case TILE:
				return entity.collidesWith(this);
			default:
				return false;
		}
	}
	
	/**
	 * Move the character and update their position based on an x/y offset, handling any resulting collisions.
	 * @param xOffset The offset to move on the x axis.
	 * @param yOffset The offset to move on the y axis.
	 * @param spatialGrid The spatial grid to use in finding collisions along the movement path.
	 */
	public void move(float xOffset, float yOffset, SpatialGrid<ICollidableEntity> spatialGrid) {
		// There is nothing to do if no positional offset is being applied.
		if (xOffset == 0 && yOffset == 0) {
			return;
		}
		
		// Find any level positioned entities that the character could potentially collide with while moving.
		HashSet<ICollidableEntity> collisionCandidates = spatialGrid.getCollisionCandidates(this, this.getMovementSpeed());

		// Remove any collision candidates that are close enought ot be considered but cannot actually collide.
		Iterator<ICollidableEntity> collidablesIterator = collisionCandidates.iterator();
		while (collidablesIterator.hasNext()) {
			ICollidableEntity collidable = collidablesIterator.next();
			if (!collidable.collidesWith(this)) {
				collidablesIterator.remove();
			}
		}
		
		// Try to find any entities that the character now collides with on the x axis if we are actually moving on it.
		if (xOffset != 0) {
			// Update the x axis.
			this.position.setX(this.position.getX() + xOffset);
			
			for (ICollidableEntity entity : collisionCandidates) {
				// Ignore an entities that we are not intersecting.
				if (!SpatialGrid.doEntitiesIntersect(this, entity)) {
					continue;
				}
				
				// TODO Try moving this entity against the edge of the closest colliding entity on this axis.
				// For now, we will simply reset the characters position on this axis to what it was before.
				this.position.setX(this.position.getX() - xOffset);
				break;
	    	}
		}
		
		// Try to find any entities that the character now collides with on the y axis if we are actually moving on it.
		if (yOffset != 0) {
			// Update the y axis.
			this.position.setY(this.position.getY() + yOffset);
			
			for (ICollidableEntity entity : collisionCandidates) {
				// Ignore an entities that we are not intersecting.
				if (!SpatialGrid.doEntitiesIntersect(this, entity)) {
					continue;
				}
				
				// TODO Try moving this entity against the edge of the closest colliding entity on this axis.
				// For now, we will simply reset the characters position on this axis to what it was before.
				this.position.setY(this.position.getY() - yOffset);
				break;
	    	}
		}
		
		// Update the character within the context of the spatial grid.
		spatialGrid.update(this);
	}
}