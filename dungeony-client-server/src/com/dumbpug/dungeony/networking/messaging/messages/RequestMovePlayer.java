package com.dumbpug.dungeony.networking.messaging.messages;

import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.session.character.Movement;

/**
 * A message sent to the server requesting to move a player in a direction.
 */
public class RequestMovePlayer implements IMessage {
	/*
	 * The direction of movement.
	 */
	private Movement movement;
	
	/**
	 * Create a new instance of the RequestMovePlayer class.
	 * @param movement The direction to move in.
	 */
	public RequestMovePlayer(Movement movement) {
		this.movement = movement;
	}
	
	/**
	 * Get the direction of movement.
	 * @return The direction of movement.
	 */
	public Movement getMovement() {
		return this.movement;
	}

	@Override
	public int getTypeId() {
		return MessageIdentifier.MOVE_PLAYER;
	}
}
