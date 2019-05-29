package com.dumbpug.dungeony.session.level;

/**
 * Represents an in-game level.
 */
public class Level {
	/**
	 * The provider of input for all connected players.
	 */
	private IPlayersInputProvider playersInputProvider;
	
	/**
	 * Creates a new instance of the Level class.
	 * @param playersInputProvider The provider of input for all connected players.
	 */
	public Level(IPlayersInputProvider playersInputProvider) {
		this.playersInputProvider = playersInputProvider;
	}
	
	/**
	 * Tick the level.
	 */
	public void tick() {
		// TODO Tick all of the players, updating thir state based on the provided playersInputProvider.
		// TODO Tick all characters that are not players.
		// TODO Tick all tick-able level entities that are not characters.
	}
}