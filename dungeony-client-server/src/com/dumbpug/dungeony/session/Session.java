package com.dumbpug.dungeony.session;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.level.Level;
import com.dumbpug.dungeony.session.level.LevelFactory;

/**
 * Represents a game session.
 */
public class Session {
	/**
	 * The session participants.
	 */
	private ArrayList<ISessionParticipant> participants;
	/**
	 * The active levels in the session. 
	 */
	private ArrayList<Level> levels = new ArrayList<Level>();
	/**
	 * The session seed.
	 */
	private long seed;
	
	/**
	 * Creates a new instance of the Session class.
	 * @param participant The session participants.
	 * @param seed The seed to use throughout the session.
	 */
	public Session(ArrayList<ISessionParticipant> participants, long seed) {
		this.participants = participants;
		this.seed         = seed;
	}
	
	/**
	 * Initialise the session.
	 */
	public void initialise() {
		// Create the initial level.
		Level initialLevel = LevelFactory.createLevel(this.seed, 0);
		
		// Add the initial level to the list of active levels.
		levels.add(initialLevel);
		
		// Add each participant to the initial level.
		for (ISessionParticipant participant : participants) {
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
	}
}