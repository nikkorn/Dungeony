package com.dumbpug.dungeony.client;

import com.dumbpug.dungeony.session.Session;
import com.dumbpug.dungeony.session.SessionParticipants;

/**
 * Represents a session that runs within the context of the client application.
 */
public class LocalSession extends Session {
	/**
	 * The local session clock.
	 */
	private LocalSessionClock sessionClock;
	
	/**
	 * Create a new instance of the LocalSession class.
	 * @param participant The session participants.
	 * @param seed The seed to use throughout the session.
	 */
	public LocalSession(SessionParticipants participants, long seed) {
		super(participants, seed);
		
		// Create the session clock with which to tick this session.
		this.sessionClock = new LocalSessionClock(this);
		
		// Start the session clock on its own thread.
		this.sessionClock.start();
	}
}