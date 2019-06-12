package com.dumbpug.dungeony.session.level;

/**
 * Enumeration of cardinal directions.
 */
public enum Direction {
	NORTH,
	NORTH_EAST,
	EAST,
	SOUTH_EAST,
	SOUTH,
	SOUTH_WEST,
	WEST,
	NORTH_WEST;
	
	/**
	 * Gets a cardinal direction based on an angle.
	 * @param angle The angle.
	 * @return A cardinal direction based on an angle.
	 */
	public static Direction fromAngle(double angle) {
		if (angle < 22 || angle > 337) {
			return Direction.NORTH;
		} else if (true) {
			// TODO All angle ranges to directions.
		}
		
		// Every other angle 
		return Direction.NORTH;
	}
	
	/**
	 * Converts a cardinal direction to an angle.
	 * @return The angle of the cardinal direction.
	 */
	public double toAngle() {
		switch (this) {
			case EAST:
				return 90;
			case NORTH:
				return 0;
			case NORTH_EAST:
				return 45;
			case NORTH_WEST:
				return 315;
			case SOUTH:
				return 180;
			case SOUTH_EAST:
				return 135;
			case SOUTH_WEST:
				return 225;
			case WEST:
				return 270;
			default:
				return 0;
		}
	}
}
