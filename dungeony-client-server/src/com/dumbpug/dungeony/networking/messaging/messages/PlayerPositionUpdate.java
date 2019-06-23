package com.dumbpug.dungeony.networking.messaging.messages;

import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.session.level.Position;

/**
 * A message sent to a client to notify of a player that has changed positions.
 */
public class PlayerPositionUpdate implements IMessage {
	/**
	 * The id of the client that has moved.
	 */
	private int clientId;
	/**
	 * The new position of the client.
	 */
	private Position position;
	/**
	 * The new angle of view of the client.
	 */
	private Short angleOfView;
	
	/**
	 * Create a new instance of the PlayerPositionUpdate class.
	 * @param clientId The id of the client that has moved.
	 * @param position The new position of the client.
	 * @param angleOfView The new angle of view of the client.
	 */
	public PlayerPositionUpdate(int clientId, Position position, Short angleOfView) {
		this.clientId    = clientId;
		this.position    = position;
		this.angleOfView = angleOfView;
	}
	
	/**
	 * Get the id of the moved client.
	 * @return The id of the moved client.
	 */
	public int getClientId() {
		return this.clientId;
	}

	/**
	 * Get the position of the client.
	 * @return The position of the client.
	 */
	public Position getNewPosition() {
		return this.position;
	}

	/**
	 * Get the angle of view of the client.
	 * @return The angle of view of the client.
	 */
	public Short getNewAngleOfView() {
		return this.angleOfView;
	}

	@Override
	public int getTypeId() {
		return MessageIdentifier.PLAYER_POSITION_UPDATE;
	}
}