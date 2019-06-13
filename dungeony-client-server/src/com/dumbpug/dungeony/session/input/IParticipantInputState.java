package com.dumbpug.dungeony.session.input;

/**
 * Exposes information about a participants input outside the context of an active session.
 */
public interface IParticipantInputState {
	
	/**
	 * Gets whether the participant is pressing the UP button.
	 * @return Whether the participant is pressing the UP button.
	 */
	boolean isPressingUpButton();
	
	// ...
}
