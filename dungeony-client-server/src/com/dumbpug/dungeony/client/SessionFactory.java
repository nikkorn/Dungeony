package com.dumbpug.dungeony.client;

/**
 * Factory for creating LocalSession and NetworkedSession instances.
 */
public class SessionFactory {
	
	public static LocalSession createLocalSession(String username) {
		return null;
	}
	
	public static NetworkedSession createHostedSession(String userId, String username,int port) {
		return null;
	}
	
	public static NetworkedSession createRemoteSession(String userId, String username, String address, int port) {
		return null;
	}
}
