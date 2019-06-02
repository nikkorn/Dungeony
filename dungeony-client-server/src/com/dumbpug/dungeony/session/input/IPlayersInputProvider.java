package com.dumbpug.dungeony.session.input;

/**
 * The provider of input for all connected players.
 */
public interface IPlayersInputProvider {
	
	/**
	 * Gets the input state for the specified player.
	 * @param playerId The id of the player to get the state of.
	 * @return The input state for the specified player.
	 */
	IPlayerInputState getInputStateForPlayer(String playerId);
}
