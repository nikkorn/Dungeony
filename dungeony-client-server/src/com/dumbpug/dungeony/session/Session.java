package com.dumbpug.dungeony.session;

import com.dumbpug.dungeony.session.events.SessionEventQueue;
import com.dumbpug.dungeony.session.input.IPlayerInputState;
import com.dumbpug.dungeony.session.input.IPlayerInputStateProvider;
import com.dumbpug.dungeony.session.level.Level;
import com.dumbpug.dungeony.session.level.LevelCreationResult;
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
	 * The result of the level generation attempt made on creating this session.
	 */
	private LevelCreationResult levelCreationResult;
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
	 * @param seed The session seed to use.
	 */
	public Session(SessionParticipants participants, long seed) {
		this.participants = participants;
		
		// Create the player input state provider to be used as part of the session update.
		this.playerInputStateProvider = new IPlayerInputStateProvider() {
			@Override
			public IPlayerInputState getState(int participantId) {
				// Get the participant.
				SessionParticipant participant = participants.getParticipant(participantId);
				
				// Return the player input state for the participant if they exist, otherwise return null.
				return participant != null ? participant.getPlayerInputState() : null;
			}			
		};
		
		// Attempt to create a level to use as part of this session.
		this.levelCreationResult = LevelFactory.createLevel(seed, this.sessionEventQueue);
	}
	
	/**
	 * Tick the session.
	 */
	public void tick() {
		// Has the level not been loaded yet?
		if (this.level == null) {
			// Has the level generation finished?
			if (this.levelCreationResult.hasFinished()) {
				// We have just finished creating a level!
				this.level = this.levelCreationResult.getLevel();
				
				// Do stuff we need to do now that the session has a level.
				onLevelCreated();
			} else {
				// We are still waiting for our level to be generated! Don't bother doing a session tick.
				return;
			}
		}
		
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
	
	/**
	 * Handle the eventual completion of a level being generated for this session.
	 */
	private void onLevelCreated() {
		// Add each participant to the level as a player.
		for (SessionParticipant participant : participants.getAll()) {
			this.level.addPlayer(participant);
		}
	}
}