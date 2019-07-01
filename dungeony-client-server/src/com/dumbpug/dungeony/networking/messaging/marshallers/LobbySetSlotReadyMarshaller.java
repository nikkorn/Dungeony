package com.dumbpug.dungeony.networking.messaging.marshallers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.LobbySetSlotReady;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;

/**
 * The marshaller responsible for reading/writing LobbySetSlotReady messages.
 */
public class LobbySetSlotReadyMarshaller implements IMessageMarshaller<LobbySetSlotReady> {

	@Override
	public LobbySetSlotReady read(DataInputStream dataInputStream) throws IOException {
		return new LobbySetSlotReady(dataInputStream.readBoolean());
	}

	@Override
	public void write(LobbySetSlotReady message, DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeBoolean(message.isReady());
	}

	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.LOBBY_SET_SLOT_READY;
	}
}
