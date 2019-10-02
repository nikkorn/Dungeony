package com.dumbpug.dungeony.session;

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
	 * The session level.
	 */
	private Level level;
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
		// Create the level!
		// TODO This will take a little while, potentially a long while, eventually have this in another thread.
		this.level = LevelFactory.createLevel(this.seed, this.sessionEventQueue);
		
		// Add each participant to the level as a player.
		for (SessionParticipant participant : participants.getAll()) {
			this.level.addPlayer(participant);
		}
	}
	
	/**
	 * Tick the session.
	 */
	public void tick() {		
		// Tick the active level, passing a player input provider with which to update players.
		level.tick(playerInputStateProvider);
		
		// TODO Process any players that should be spawning/despawning.
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