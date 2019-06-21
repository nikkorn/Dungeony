package com.dumbpug.dungeony.client;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.Session;
import com.dumbpug.dungeony.session.SessionParticipant;
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
	 * The local session partiticipant.
	 */
	private SessionParticipant participant;
	
	/**
	 * Create a new instance of the LocalSession class.
	 * @param participant The session participant.
	 * @param seed The seed to use throughout the session.
	 */
	public LocalSession(SessionParticipant participant, long seed) {
		super(new SessionParticipants(new ArrayList<SessionParticipant>() {{ add(participant); }}), seed);
		
		// Get a reference to the participant who is the only local player.
		this.participant = participant;
		
		// Create the session clock with which to tick this session.
		this.sessionClock = new LocalSessionClock(this);
		
		// Initialise the session.
		this.initialise();
		
		// Start the session clock on its own thread.
		this.sessionClock.start();
	}

	/**
	 * Gets the local session participant.
	 * @return The local session participant.
	 */
	public SessionParticipant getParticipant() {
		return participant;
	}
}