package com.dumbpug.dungeony.networking.messaging.marshallers;

import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;
import com.dumbpug.dungeony.networking.messaging.messages.RequestMovePlayer;
import com.dumbpug.dungeony.session.character.Movement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The marshaller responsible for reading/writing RequestMovePlayer messages.
 */
public class RequestMovePlayerMarshaller implements IMessageMarshaller<RequestMovePlayer> {

	@Override
	public RequestMovePlayer read(DataInputStream dataInputStream) throws IOException {
		return new RequestMovePlayer(Movement.values()[dataInputStream.readInt()]);
	}

	@Override
	public void write(RequestMovePlayer message, DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(message.getMovement().ordinal());
	}

	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.MOVE_PLAYER;
	}
}
