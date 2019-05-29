package com.dumbpug.dungeony.session.level;

/**
 * Exposes information about a players input.
 */
public interface IPlayerInputState {
	/**
	 * Gets whether the player is moving forwards.
	 * @return Whether the player is moving forwards.
	 */
	public boolean isMovingForwards();
	
	/**
	 * Gets whether the player is moving backwards.
	 * @return Whether the player is moving backwards.
	 */
	public boolean isMovingBackwards();
	
	/**
	 * Gets whether the player is strafing to the left.
	 * @return Whether the player is strafing to the left.
	 */
	public boolean isStrafingLeft();
	
	/**
	 * Gets whether the player is strafing to the right.
	 * @return Whether the player is strafing to the right.
	 */
	public boolean isStrafingRight();
	
	/**
	 * Gets the angle of view around the Z axis.
	 * @return The angle of view around the Z axis.
	 */
	public double getAngleOfView();
}
