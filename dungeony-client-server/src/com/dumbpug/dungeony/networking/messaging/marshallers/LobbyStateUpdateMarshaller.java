package com.dumbpug.dungeony.networking.messaging.marshallers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.dumbpug.dungeony.lobby.Colour;
import com.dumbpug.dungeony.lobby.LobbySlotState;
import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.LobbyStateUpdate;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;

/**
 * The marshaller responsible for reading/writing LobbyStateUpdate messages.
 */
public class LobbyStateUpdateMarshaller implements IMessageMarshaller<LobbyStateUpdate> {

	@Override
	public LobbyStateUpdate read(DataInputStream dataInputStream) throws IOException {
		// Read the lobby countdown value.
		Short countdown = dataInputStream.readShort();
		
		ArrayList<LobbySlotState> slotStates = new ArrayList<LobbySlotState>();
		
		// Read the slot states.
		for (int slotNumber = 1; slotNumber <= 4; slotNumber++) {
			String playerId = dataInputStream.readUTF();
			Colour colour   = Colour.values()[dataInputStream.readShort()];
			boolean isReady = dataInputStream.readBoolean();
			slotStates.add(new LobbySlotState(slotNumber, playerId.equals("") ? null : playerId, isReady, colour));
		}
		
		return new LobbyStateUpdate(slotStates, countdown == -1 ? null : (int)countdown);
	}

	@Override
	public void write(LobbyStateUpdate message, DataOutputStream dataOutputStream) throws IOException {
		// Write the lobby countdown value.
		dataOutputStream.writeShort(message.getCountdown() == null ? -1 : message.getCountdown());
		
		// Write the slot states.
		for (int index = 0; index < 4; index++) {
			String playerId = message.getLobbySlotStates().get(index).getPlayerId();
			dataOutputStream.writeUTF(playerId == null ? "" : playerId);
			dataOutputStream.writeShort(message.getLobbySlotStates().get(index).getColour().ordinal());
			dataOutputStream.writeBoolean(message.getLobbySlotStates().get(index).isReady());
		}
	}

	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.LOBBY_STATE_UPDATE;
	}
}
