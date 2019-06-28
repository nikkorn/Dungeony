package com.dumbpug.dungeony.client.level;

import com.dumbpug.dungeony.session.level.Position;

/**
 * A client-side representation of server-side player state.
 */
public class PlayerState extends CharacterState {
	/**
	 * The client id of the participant.
	 */
	private int id;
	/**
	 * The player name of the participant.
	 */
	private String name;
	
	/**
	 * Creates a new instance of the PlayerState class.
	 * @param id The client id of the participant.
	 * @param name The player name of the participant.
	 * @param position The initial position of the player.
	 * @param angleOfView The initial angle of view of the player.
	 */
	public PlayerState(int id, String name, Position position, double angleOfView) {
		super(position, angleOfView);
		this.id   = id;
		this.name = name;
	}

	/**
	 * Gets the client id of the participant.
	 * @return The client id of the participant.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the player name of the participant.
	 * @return The player name of the participant.
	 */
	public String getName() {
		return name;
	}
}
