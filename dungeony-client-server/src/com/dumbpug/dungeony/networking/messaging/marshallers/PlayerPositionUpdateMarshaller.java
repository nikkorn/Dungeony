package com.dumbpug.dungeony.networking.messaging.marshallers;

import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;
import com.dumbpug.dungeony.networking.messaging.messages.PlayerPositionUpdate;
import com.dumbpug.dungeony.session.level.Position;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The marshaller responsible for reading/writing PlayerPositionUpdate messages.
 */
public class PlayerPositionUpdateMarshaller implements IMessageMarshaller<PlayerPositionUpdate> {

	@Override
	public PlayerPositionUpdate read(DataInputStream dataInputStream) throws IOException {
		// Get the id of the moving player.
		int clientId = dataInputStream.readInt();
		// Get the new x/y position of the client.
		float positionX = dataInputStream.readFloat();
		float positionY = dataInputStream.readFloat();
		// Get the angle of view of the client
		Short angleOfView = dataInputStream.readShort();
		// Return the constructed message.
		return new PlayerPositionUpdate(clientId, new Position(positionX, positionY), angleOfView);
	}

	@Override
	public void write(PlayerPositionUpdate message, DataOutputStream dataOutputStream) throws IOException {
		// Write the id of the client.
		dataOutputStream.writeInt(message.getClientId());
		// Write the new x/y position of the client.
		dataOutputStream.writeFloat(message.getNewPosition().getX());
		dataOutputStream.writeFloat(message.getNewPosition().getY());
		// Write the direction that the player has moved in to reach the position.
		dataOutputStream.writeShort(message.getNewAngleOfView());
	}
	
	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.PLAYER_POSITION_UPDATE;
	}
}
