package com.dumbpug.dungeony.networking.messaging.messages;

import com.dumbpug.dungeony.networking.messaging.IMessage;

/**
 * The message sent to the client on a successful join.
 */
public class JoinSuccess implements IMessage {
	/**
	 * The server seed.
	 */
	private long seed;
	
	/**
	 * Create a new instance of the JoinSuccess class.
	 * @param seed The world seed.
	 */
	public JoinSuccess(long seed) {
		this.seed = seed;
	}
	
	/**
	 * Get the server seed.
	 * @return The server seed.
	 */
	public long getSeed() {
		return this.seed;
	}

	@Override
	public int getTypeId() {
		return MessageIdentifier.JOIN_SUCCESS;
	}
}
