package com.dumbpug.dungeony.session;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.events.SessionEventQueue;
import com.dumbpug.dungeony.session.level.Level;
import com.dumbpug.dungeony.session.level.LevelFactory;

/**
 * Represents a game session.
 */
public class Session {
	/**
	 * The session participants.
	 */
	private SessionParticipants participants;
	/**
	 * The active levels in the session. 
	 */
	private ArrayList<Level> levels = new ArrayList<Level>();
	/**
	 * The session seed.
	 */
	private long seed;
	/**
	 * The session event queue.
	 */
	private SessionEventQueue sessionEventQueue = new SessionEventQueue();
	
	/**
	 * Creates a new instance of the Session class.
	 * @param participant The session participants.
	 * @param seed The seed to use throughout the session.
	 */
	public Session(SessionParticipants participants, long seed) {
		this.participants = participants;
		this.seed         = seed;
	}
	
	/**
	 * Gets the session event queue.
	 * @return The session event queue.
	 */
	public SessionEventQueue getSessionEventQueue() {
		return this.sessionEventQueue;
	}
	
	/**
	 * Initialise the session.
	 */
	public void initialise() {
		// Create the initial level.
		Level initialLevel = LevelFactory.createLevel(this.seed, 0, this.sessionEventQueue);
		
		// Add the initial level to the list of active levels.
		levels.add(initialLevel);
		
		// Add each participant to the initial level.
		for (SessionParticipant participant : participants.getAll()) {
			initialLevel.addPlayer(participant);
		}
	}
	
	/**
	 * Tick the session.
	 */
	public void tick() {		
		// Tick every active level. 
		for (Level level : this.levels) {
			if (level.isActive()) {
				level.tick();
			}
		}
		
		// TODO Process any players that should be despawning from their current level and spawn them in the next.
	}
	
	/**
	 * Consume all of the session events in the session event queue.
	 * @param eventConsumer The consumer of the session events.
	 */
	public void consumeSessionEvents(ISessionEventConsumer eventConsumer) {
		while (sessionEventQueue.hasNext()) {
			eventConsumer.consume(sessionEventQueue.next());
		}
	}
}