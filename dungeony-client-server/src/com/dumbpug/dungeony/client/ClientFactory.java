package com.dumbpug.dungeony.client;

import java.io.IOException;
import java.net.Socket;
import com.dumbpug.dungeony.networking.QueuedMessageReader;
import com.dumbpug.dungeony.networking.messaging.DungeonyMarshallerProviderFactory;
import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.networking.messaging.MessageInputStream;
import com.dumbpug.dungeony.networking.messaging.MessageMarshallerProvider;
import com.dumbpug.dungeony.networking.messaging.MessageOutputStream;
import com.dumbpug.dungeony.networking.messaging.messages.JoinFailure;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;

/**
 * Factory for creating Client instances.
 */
public class ClientFactory {
	
	/**
	 * Connect to a remote server instance and return a Client instance representing the connection.
	 * @param host The host address.
	 * @param port The host port.
	 * @param playerId The player id of the client.
	 * @return A Client instance representing the connection.
	 * @throws IOException
	 * @throws ServerJoinRequestRejectedException Thrown when the request to join the server is rejected.
	 */
	public static Client create(String host, int port, String playerId) throws IOException, ServerJoinRequestRejectedException {
		// Create the socket on which to connect to the server.
		Socket connectionSocket = new Socket(host, port);
		
		// Create the message marshaller provider for our message stream.
		MessageMarshallerProvider marshallerProvider = DungeonyMarshallerProviderFactory.create();
		
		// Create the message output stream used to write messages to the server.
		MessageOutputStream messageOutputStream = new MessageOutputStream(connectionSocket.getOutputStream(), marshallerProvider);
		
		// Wait for the response form the server. We are expecting either a join success or failure.
		// Firstly, we need to create our message input stream in order to grab the server response.
		MessageInputStream messageInputStream = new MessageInputStream(connectionSocket.getInputStream(), marshallerProvider);
		
		// Send the handshake string that the server expects.
		messageOutputStream.writeUTF("JOIN_REQUEST");
		
		// Send the client player id.
		messageOutputStream.writeUTF(playerId);
		
		// Read the server response message. This operation blocks until we get it.
		IMessage response = messageInputStream.readMessage();
		
		// We got a response from the server!
		switch (response.getTypeId()) {
			case MessageIdentifier.JOIN_SUCCESS:
				// The server sent us a message to let us know we successfully joined!
				// Firstly, create the queued message reader that will be used by the server proxy.
				QueuedMessageReader queuedMessageReader = new QueuedMessageReader(messageInputStream);
				
				// Next, create the actual client instance.
				Client client = new Client(queuedMessageReader, messageOutputStream);
				
				// Lastly, our queued message reader needs to start reading incoming messages.
				Thread messageReaderThread = new Thread(queuedMessageReader);
				messageReaderThread.setDaemon(true);
				messageReaderThread.start();
				
				// We are finished, return the successfully created client.
				return client;
			case MessageIdentifier.JOIN_FAIL:
				// The server sent us a message to let us know we failed to join!
				throw new ServerJoinRequestRejectedException(((JoinFailure)response).getReason());
			default:
				throw new RuntimeException("Received unexpected response from server.");
		}	
	}
}
