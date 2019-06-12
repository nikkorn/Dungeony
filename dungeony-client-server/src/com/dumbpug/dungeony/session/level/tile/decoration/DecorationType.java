package com.dumbpug.dungeony.session.level.tile.decoration;

/**
 * Enumeration of tile decoration types.
 */
public enum DecorationType {
	WALL_TORCH;
	
	/**
	 * Gets whether a string value maps to an enum value.
	 * @param value The string value.
	 * @return Whether a string value maps to an enum value.
	 */
	public static boolean hasType(String value) {
		try {
			DecorationType.valueOf(value);
			return true;
		} catch (IllegalArgumentException exception) {
			return false;
		}
	}
}
