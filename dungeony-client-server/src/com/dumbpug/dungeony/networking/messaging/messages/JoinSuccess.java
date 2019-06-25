package com.dumbpug.dungeony.networking.messaging.messages;

import com.dumbpug.dungeony.networking.messaging.IMessage;

/**
 * The message sent to the client on a successful join.
 */
public class JoinSuccess implements IMessage {

	@Override
	public int getTypeId() {
		return MessageIdentifier.JOIN_SUCCESS;
	}
}
