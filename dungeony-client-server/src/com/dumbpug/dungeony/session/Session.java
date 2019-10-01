package com.dumbpug.dungeony.session;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.events.SessionEventQueue;
import com.dumbpug.dungeony.session.input.IPlayerInputState;
import com.dumbpug.dungeony.session.input.IPlayerInputStateProvider;
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
	 * The session event queue populated with even information to be consumed by a session observer.
	 */
	private SessionEventQueue sessionEventQueue = new SessionEventQueue();
	/**
	 * The player input state provider to be used as part of the session update.
	 */
	private IPlayerInputStateProvider playerInputStateProvider;
	
	/**
	 * Creates a new instance of the Session class.
	 * @param participant The session participants.
	 * @param seed The seed to use throughout the session.
	 */
	public Session(SessionParticipants participants, long seed) {
		this.participants = participants;
		this.seed         = seed;
		
		// Create the player input state provider to be used as part of the session update.
		this.playerInputStateProvider = new IPlayerInputStateProvider(){
			@Override
			public IPlayerInputState getState(int participantId) {
				// Get the participant.
				SessionParticipant participant = participants.getParticipant(participantId);
				
				// Return the player input state for the participant if they exist, otherwise return null.
				return participant != null ? participant.getPlayerInputState() : null;
			}			
		};
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
				// Tick the active level, passing a player input provider with which to update players.
				level.tick(playerInputStateProvider);
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