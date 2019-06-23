package com.dumbpug.dungeony.networking.messaging.marshallers;

import com.dumbpug.dungeony.networking.messaging.IMessageMarshaller;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;
import com.dumbpug.dungeony.networking.messaging.messages.PlayerSpawned;
import com.dumbpug.dungeony.session.level.Position;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The marshaller responsible for reading/writing PlayerSpawned messages.
 */
public class PlayerSpawnedMarshaller implements IMessageMarshaller<PlayerSpawned> {

	@Override
	public PlayerSpawned read(DataInputStream dataInputStream) throws IOException {
		// Get the id of the spawning player.
		int clientId = dataInputStream.readInt();
		// Get the x/y position of the spawning player.
		Float positionX = dataInputStream.readFloat();
		Float positionY = dataInputStream.readFloat();
		// Get the angle of view of the client
		Short angleOfView = dataInputStream.readShort();
		// Return the constructed message.
		return new PlayerSpawned(clientId, new Position(positionX, positionY), angleOfView);
	}

	@Override
	public void write(PlayerSpawned message, DataOutputStream dataOutputStream) throws IOException {
		// Write the id of the spawning player.
		dataOutputStream.writeInt(message.getClientId());
		// Write the x/y position of the spawning client.
		dataOutputStream.writeFloat(message.getSpawnPosition().getX());
		dataOutputStream.writeFloat(message.getSpawnPosition().getY());
		// Write the angle of view of the spawning client.
		dataOutputStream.writeInt(message.getSpawnAngleOfView());
	}

	@Override
	public int getMessageTypeId() {
		return MessageIdentifier.PLAYER_SPAWNED;
	}
}