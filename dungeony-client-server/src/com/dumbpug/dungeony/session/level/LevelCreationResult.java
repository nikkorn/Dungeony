package com.dumbpug.dungeony.session.level;

/**
 * Represents the result of an asynchronous attempt to generate a level.
 */
public class LevelCreationResult {
	/**
	 * The created level.
	 */
	private Level level = null;
	
	public synchronized Level getLevel() {
		return this.level;
	}
	
	public synchronized void setLevel(Level level) {
		this.level = level;
	}
	
	public synchronized boolean hasFinished() {
		return this.level != null;
	}
}
