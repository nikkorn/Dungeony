package com.dumbpug.dungeony.session.input;

/**
 * Exposes information about a players input.
 */
public interface IPlayerInputState {
	/**
	 * Gets whether the player is moving forwards.
	 * @return Whether the player is moving forwards.
	 */
	boolean isMovingForwards();
	
	/**
	 * Gets whether the player is moving backwards.
	 * @return Whether the player is moving backwards.
	 */
	boolean isMovingBackwards();
	
	/**
	 * Gets whether the player is strafing to the left.
	 * @return Whether the player is strafing to the left.
	 */
	boolean isStrafingLeft();
	
	/**
	 * Gets whether the player is strafing to the right.
	 * @return Whether the player is strafing to the right.
	 */
	boolean isStrafingRight();
	
	/**
	 * Gets the angle of view around the Z axis.
	 * @return The angle of view around the Z axis.
	 */
	double getAngleOfView();
}
