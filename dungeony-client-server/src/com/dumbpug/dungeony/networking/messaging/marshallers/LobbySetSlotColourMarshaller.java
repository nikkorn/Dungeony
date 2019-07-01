package com.dumbpug.dungeony.networking.messaging.marshallers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import com.dumbpug.dungeony.lobby.Colour;
import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.LobbySetSlotColour;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;

/**
 * The marshaller responsible for reading/writing LobbySetSlotColour messages.
 */
public class LobbySetSlotColourMarshaller implements IMessageMarshaller<LobbySetSlotColour> {

	@Override
	public LobbySetSlotColour read(DataInputStream dataInputStream) throws IOException {
		return new LobbySetSlotColour(Colour.values()[dataInputStream.readShort()]);
	}

	@Override
	public void write(LobbySetSlotColour message, DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeShort(message.getColour().ordinal());
	}

	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.LOBBY_SET_SLOT_COLOUR;
	}
}
