package com.dumbpug.dungeony.session.character;

import java.util.HashSet;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.ILevelPositionedEntity;
import com.dumbpug.dungeony.session.level.Position;
import com.dumbpug.dungeony.session.level.grid.SpatialGrid;

/**
 * A character within the context of a level.
 */
public abstract class LevelCharacter implements ILevelPositionedEntity {
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
		// TODO Calculate the correct cardinal direction based on the angle of view.
		return Direction.NORTH;
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
	 * Move the character and update their position based on an x/y offset, handling any resulting collisions.
	 * @param xOffset The offset to move on the x axis.
	 * @param yOffset The offset to move on the y axis.
	 * @param spatialGrid The spatial grid to use in finding collisions along the movement path.
	 */
	public void move(float xOffset, float yOffset, SpatialGrid<ILevelPositionedEntity> spatialGrid) {
		// Find any level positioned entities that the character could potentially collide with while moving.
		HashSet<ILevelPositionedEntity> collisionCandidates = spatialGrid.getCollisionCandidates(this, this.getMovementSpeed());
		
		// Update the x axis.
		this.position.setX(this.position.getX() + xOffset);
		
		// Try to find any entities that the character now collides with on the x axis if we are actually moving on it.
		if (xOffset != 0) {
			for (ILevelPositionedEntity entity : collisionCandidates) {
				if (xOffset > 0) {
					// ... character is moving east ... 
				} else {
					// ... character is moving west ... 
				}
	    	}
		}
		
		// Update the y axis.
		this.position.setY(this.position.getY() + yOffset);
		
		// Try to find any entities that the character now collides with on the y axis if we are actually moving on it.
		if (yOffset != 0) {
			for (ILevelPositionedEntity entity : collisionCandidates) {
				if (yOffset > 0) {
					// ... character is moving north ... 
				} else {
					// ... character is moving south ... 
				}
	    	}
		}
		
		// Update the character within the context of the spatial grid.
		spatialGrid.update(this);
	}
}