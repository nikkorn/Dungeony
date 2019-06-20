package com.dumbpug.dungeony.client;

import com.dumbpug.dungeony.session.SessionParticipant;
import com.dumbpug.dungeony.session.input.IPlayerInputState;

/**
 * Factory for creating LocalSession and NetworkedSession instances.
 */
public class SessionFactory {
	
	public static LocalSession createLocalSession(String username) {
		// TODO Create a type of IPlayerInputState which is just hooked up to keyboard/mouse input.
		IPlayerInputState playerInputState = null;
		
		// Create a SessionParticipant instance for the user.
		SessionParticipant user = new SessionParticipant(0, username, playerInputState);
		
		// Create and return the local session.
		return new LocalSession(user, 12345);
	}
	
	public static NetworkedSession createHostedSession(String userId, String username,int port) {
		return null;
	}
	
	public static NetworkedSession createRemoteSession(String userId, String username, String address, int port) {
		return null;
	}
}
