package com.dumbpug.dungeony.session.level;

/**
 * The provider of input for all connected players.
 */
public interface IPlayersInputProvider {
	
	/**
	 * Gets the input state for the specified player.
	 * @param playerId The id of the player to get the state of.
	 * @return The input state for the specified player.
	 */
	public IPlayerInputState getInputStateForPlayer(String playerId);
}
