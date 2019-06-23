package com.dumbpug.dungeony.networking.messaging.marshallers;

import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.JoinSuccess;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The marshaller responsible for reading/writing JoinSuccess messages.
 */
public class JoinSuccessMarshaller implements IMessageMarshaller<JoinSuccess> {

	@Override
	public JoinSuccess read(DataInputStream dataInputStream) throws IOException {
		// Read the server seed.
		long seed = dataInputStream.readLong();
		// Return the message.
		return new JoinSuccess(seed);
	}

	@Override
	public void write(JoinSuccess message, DataOutputStream dataOutputStream) throws IOException {
		// Write the world seed.
		dataOutputStream.writeLong(message.getSeed());
	}

	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.JOIN_SUCCESS;
	}
}