package com.dumbpug.dungeony.session.input;

/**
 * A provider of input state for participants.
 */
public interface IPlayerInputStateProvider {
	
	/**
	 * Gets the player input state for the given participant.
	 * @param participantId The participant id.
	 * @return The player input state for the given participant, or null if the participant is not available.
	 */
	public IPlayerInputState getState(int participantId);
}
