package com.dumbpug.dungeony.networking.messaging.marshallers;

import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.JoinFailure;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The marshaller responsible for reading/writing JoinFailure messages.
 */
public class JoinFailureMarshaller implements IMessageMarshaller<JoinFailure> {

	@Override
	public JoinFailure read(DataInputStream dataInputStream) throws IOException {
		return new JoinFailure(dataInputStream.readUTF());
	}

	@Override
	public void write(JoinFailure message, DataOutputStream dataOutputStream) throws IOException {
		// Write the failure reason.
		dataOutputStream.writeUTF(message.getReason());
	}

	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.JOIN_FAIL;
	}
}