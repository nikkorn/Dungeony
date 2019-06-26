package com.dumbpug.dungeony.networking.messaging.marshallers;

import com.dumbpug.dungeony.input.ClientKeyInputState;
import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.ClientKeyInputStateChanged;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The marshaller responsible for reading/writing ClientKeyInputStateChanged messages.
 */
public class ClientKeyInputStateChangedMarshaller implements IMessageMarshaller<ClientKeyInputStateChanged> {

	@Override
	public ClientKeyInputStateChanged read(DataInputStream dataInputStream) throws IOException {
		int packedInputState = dataInputStream.readInt();
		System.out.println("KEY STATE CHANGED: " + packedInputState);
		return new ClientKeyInputStateChanged(ClientKeyInputState.fromPackedInt(packedInputState));
	}

	@Override
	public void write(ClientKeyInputStateChanged message, DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(message.getClientInputState().toPackedInt());
	}

	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.CLIENT_KEY_INPUT_STATE_CHANGED;
	}
}
