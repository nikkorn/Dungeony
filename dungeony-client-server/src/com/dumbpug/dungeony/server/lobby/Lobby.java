package com.dumbpug.dungeony.server.lobby;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Lobby {
	/**
	 * The lobby slots.
	 */
	@SuppressWarnings("serial")
	private ArrayList<LobbySlot> slots = new ArrayList<LobbySlot>() {{
		add(new LobbySlot(1));
		add(new LobbySlot(2));
		add(new LobbySlot(3));
		add(new LobbySlot(4));
	}};
	
	/**
	 * Start listening for clients connecting to the lobby.
	 */
	public void startListeningForConnections(int port) {
		try {
			// Create the ServerSocket instance on which we will accept new connections.
			final ServerSocket serverSocket = new ServerSocket(port);
			
			// Create a new thread on which to sit and listen for client connections.
			Thread connectionListenerThread = new Thread(new Runnable() {
				@Override
				public void run() {
					// We will just listen forever.
					while(true) {
						// Sit here and listen here for a connection attempt.
						try {
							Socket clientSocket = serverSocket.accept();
							
							// TODO Handle a client request to join the lobby.

						} catch (IOException e) {
							// An IO exception was thrown in accepting a new client connection.
							// In this case just give up and go back to listening for new connections.
							continue;
						}
					}
				}
			});
			
			// This connection listener thread should not prevent the server from stopping.
			connectionListenerThread.setDaemon(true);
			
			// Start listening for client connections.
			connectionListenerThread.start();
		} catch (BindException e) {
			// We failed to bind to the socket!
			System.out.println("We failed to bind to the socket. Is a server already running?");
		} catch (IOException e) {
			// An IO exception was thrown in creating a new ServerSocket instance.
			e.printStackTrace();
		}
	}
}