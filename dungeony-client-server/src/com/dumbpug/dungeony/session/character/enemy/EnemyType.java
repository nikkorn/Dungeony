package com.dumbpug.dungeony.session.character.enemy;

/**
 * Enumeration of enemy types.
 */
public enum EnemyType {
	SKELETON;
	
	/**
	 * Gets whether a string value maps to an enum value.
	 * @param value The string value.
	 * @return Whether a string value maps to an enum value.
	 */
	public static boolean hasType(String value) {
		try {
			EnemyType.valueOf(value);
			return true;
		} catch (IllegalArgumentException exception) {
			return false;
		}
	}
}
