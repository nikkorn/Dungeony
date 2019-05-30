package com.dumbpug.dungeony.session.character;

import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.Position;

/**
 * A character within the context of a level.
 */
public abstract class LevelCharacter {
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
	public Position getPosition() {
		return this.position;
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
}