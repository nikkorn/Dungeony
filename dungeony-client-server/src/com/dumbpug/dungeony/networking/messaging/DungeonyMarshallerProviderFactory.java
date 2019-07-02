package com.dumbpug.dungeony.networking.messaging;

import com.dumbpug.dungeony.networking.messaging.marshallers.*;

/**
 * Factory for creating MessageMarshallerProvider instances.
 */
public class DungeonyMarshallerProviderFactory {
	
	/**
	 * Create MessageMarshallerProvider for client/server messaging.
	 * @return A MessageMarshallerProvider for client/server messaging.
	 */
	public static MessageMarshallerProvider create() {
		// Create the marshaller provider.
		MessageMarshallerProvider provider = new MessageMarshallerProvider();

		/**
		 * The marshallers relating to server -> client messages.
		 */
		// Add the marshaller used for reading/writing join success messages.
		provider.addMarshaller(new JoinSuccessMarshaller());
		// Add the marshaller used for reading/writing join failure messages.
		provider.addMarshaller(new JoinFailureMarshaller());
		// Add the marshaller used for reading/writing player moved messages.
		provider.addMarshaller(new PlayerPositionUpdateMarshaller());
		// Add the marshaller used for reading/writing player spawn messages.
		provider.addMarshaller(new PlayerSpawnedMarshaller());
		// Add the marshaller used for reading/writing lobby state update messages.
		provider.addMarshaller(new LobbyStateUpdateMarshaller());

		/**
		 * The marshallers relating to client -> server messages.
		 */
		// Add the marshaller used for reading/writing move player messages.
		provider.addMarshaller(new ClientKeyInputStateChangedMarshaller());
		// Add the marshaller used for reading/writing lobby set slot colour messages.
		provider.addMarshaller(new LobbySetSlotColourMarshaller());
		// Add the marshaller used for reading/writing lobby set slot ready messages.
		provider.addMarshaller(new LobbySetSlotReadyMarshaller());

		// Return the provider.
		return provider;
	}
}
