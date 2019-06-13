package com.dumbpug.dungeony.session;

import com.dumbpug.dungeony.session.input.IPlayerInputState;

/**
 * Represents a participant in the session.
 */
public interface ISessionParticipant {
	
	/**
	 * Gets the participant id.
	 * @return The participant id.
	 */
	public String getId();
	
	/**
	 * Gets the name that the participant will use as their player name.
	 * @return The name that the participant will use as their player name.
	 */
	public String getPlayerName();
	
	/**
	 * Gets the information about a players input.
	 * @return The information about a players input.
	 */
	public IPlayerInputState getPlayerInputState();
}
