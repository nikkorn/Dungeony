package com.dumbpug.dungeony.client.level;

import com.dumbpug.dungeony.session.level.Position;

/**
 * A client-side representation of server-side character state.
 */
public class CharacterState {
	/**
	 * The position of the character.
	 */
	private Position position;
	/**
	 * The angle of the characters view around the z axis.
	 */
	private double angleOfView = 0;
	
	/**
	 * Creates a new instance of the CharacterState class.
	 * @param position The initial position of the character.
	 * @param angleOfView The initial angle of view of the character.
	 */
	public CharacterState(Position position, double angleOfView) {
		this.position    = position;
		this.angleOfView = angleOfView;
	}
	
	/**
	 * Gets the character angle of view.
	 * @return The character angle of view.
	 */
	public double getAngleOfView() {
		return angleOfView;
	}
	
	/**
	 * Sets the character angle of view.
	 * @param The character angle of view.
	 */
	public void setAngleOfView(double angleOfView) {
		this.angleOfView = angleOfView;
	}
	
	/**
	 * Gets the character position.
	 * @return The character position.
	 */
	public Position getPosition() {
		return position;
	}
}
