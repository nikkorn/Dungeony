package com.dumbpug.dungeony.session.level;

/**
 * An x/y position.
 */
public class Position {
	/**
	 * The x position.
	 */
	private float x;
	/**
	 * The y position.
	 */
	private float y;
	
	/**
	 * Creates a new instance of the Position class.
	 * @param x The x position.
	 * @param y The y position.
	 */
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x position.
	 * @return The x position.
	 */
	public float getX() {
		return x;
	}

	/**
	 * Set the x position.
	 * @param x The x position.
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Get the y position.
	 * @return The y position.
	 */
	public float getY() {
		return y;
	}

	/**
	 * Set the y position.
	 * @param y The y position.
	 */
	public void setY(float y) {
		this.y = y;
	}
}
