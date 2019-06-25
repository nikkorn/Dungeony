package com.dumbpug.dungeony.networking.messaging.marshallers;

import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.ClientInputStateChanged;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;
import com.dumbpug.dungeony.utilities.BitPacker;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The marshaller responsible for reading/writing RequestMovePlayer messages.
 */
public class ClientInputStateChangedMarshaller implements IMessageMarshaller<ClientInputStateChanged> {

	@Override
	public ClientInputStateChanged read(DataInputStream dataInputStream) throws IOException {
		int packedInputState       = dataInputStream.readInt();
		boolean isPrimaryKeyDown   = BitPacker.unpack(packedInputState, 0, 1) == 1;
		boolean isSecondaryKeyDown = BitPacker.unpack(packedInputState, 1, 2) == 1;
		boolean isTertiaryKeyDown  = BitPacker.unpack(packedInputState, 2, 3) == 1;
		boolean isUpKeyDown        = BitPacker.unpack(packedInputState, 3, 4) == 1;
		boolean isDownKeyDown      = BitPacker.unpack(packedInputState, 4, 5) == 1;
		boolean isLeftKeyDown      = BitPacker.unpack(packedInputState, 5, 6) == 1;
		boolean isRightKeyDown     = BitPacker.unpack(packedInputState, 6, 7) == 1;
		return new ClientInputStateChanged(
			isPrimaryKeyDown,
			isSecondaryKeyDown,
			isTertiaryKeyDown,
			isUpKeyDown,
			isDownKeyDown,
			isLeftKeyDown,
			isRightKeyDown
		);
	}

	@Override
	public void write(ClientInputStateChanged message, DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(toPackedInt(message));
	}
	
	/**
	 * Convert an integer representation of a ClientInputStateChanged message instance.
	 * @param message The ClientInputStateChanged message instance.
	 * @return An integer representation of a ClientInputStateChanged message instance.
	 */
	private static int toPackedInt(ClientInputStateChanged message) {
		int packed = BitPacker.pack(0, message.isPrimaryKeyDown() ? 1 : 0, 0, 1);
		packed     = BitPacker.pack(packed, message.isSecondaryKeyDown() ? 1 : 0, 1, 2);
		packed     = BitPacker.pack(packed, message.isTertiaryKeyDown() ? 1 : 0, 2, 3);
		packed     = BitPacker.pack(packed, message.isUpKeyDown() ? 1 : 0, 3, 4);
		packed     = BitPacker.pack(packed, message.isDownKeyDown() ? 1 : 0, 4, 5);
		packed     = BitPacker.pack(packed, message.isLeftKeyDown() ? 1 : 0, 5, 6);
		packed     = BitPacker.pack(packed, message.isRightKeyDown() ? 1 : 0, 6, 7);
		return packed;
	}

	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.CLIENT_INPUT_STATE_CHANGED;
	}
}
