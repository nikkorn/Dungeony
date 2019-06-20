package com.dumbpug.dungeony.session;

import com.dumbpug.dungeony.session.input.IPlayerInputState;

/**
 * A participant in the session.
 */
public class SessionParticipant {
	/**
	 * The client id of the participant.
	 */
	private int id;
	/**
	 * The player name of the participant.
	 */
	private String playerName;
	/**
	 * The player input state of the participant.
	 */
	private IPlayerInputState playerInputState;
	
	/**
	 * Creates a new instance of the 
	 * @param id The client id of the participant.
	 * @param playerName The player name of the participant.
	 * @param playerInputState The player input state of the participant.
	 */
	public SessionParticipant(int id, String playerName, IPlayerInputState playerInputState) {
		this.id               = id;
		this.playerName       = playerName;
		this.playerInputState = playerInputState;
	}
	
	/**
	 * Gets the participant id.
	 * @return The participant id.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Gets the name that the participant will use as their player name.
	 * @return The name that the participant will use as their player name.
	 */
	public String getPlayerName() {
		return this.playerName;
	}
	
	/**
	 * Gets the information about a players input.
	 * @return The information about a players input.
	 */
	public IPlayerInputState getPlayerInputState() {
		return this.playerInputState;
	}
}
