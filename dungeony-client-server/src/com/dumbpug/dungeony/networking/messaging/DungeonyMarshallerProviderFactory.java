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
		/**
		 * The marshallers relating to server -> client messages.
		 */
		// Create the marshaller provider.
		MessageMarshallerProvider provider = new MessageMarshallerProvider();
		// Add the marshaller used for reading/writing join success messages.
		provider.addMarshaller(new JoinSuccessMarshaller());
		// Add the marshaller used for reading/writing join failure messages.
		provider.addMarshaller(new JoinFailureMarshaller());
		// Add the marshaller used for reading/writing player moved messages.
		provider.addMarshaller(new PlayerPositionUpdateMarshaller());
		// Add the marshaller used for reading/writing player spawn messages.
		provider.addMarshaller(new PlayerSpawnedMarshaller());

		/**
		 * The marshallers relating to client -> server messages.
		 */
		// Add the marshaller used for reading/writing move player messages.
		provider.addMarshaller(new RequestMovePlayerMarshaller());

		// Return the provider.
		return provider;
	}
}
