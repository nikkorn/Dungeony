package com.dumbpug.dungeony.networking.messaging;

/**
 * Represents a message to be transmitted over a message stream.
 */
public interface IMessage {
	
	/**
	 * Get the message type id.
	 * @return The message type id.
	 */
	int getTypeId();
}
