package com.dumbpug.dungeony.session.character.player;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.level.ICollidableEntity;
import com.dumbpug.dungeony.session.input.IPlayerInputState;
import com.dumbpug.dungeony.session.input.IPlayerInputStateProvider;
import com.dumbpug.dungeony.session.level.grid.SpatialGrid;

/**
 * Represents a collection of players in the context of a level.
 */
public class Players {
	/**
	 * The list of players.
	 */
	private ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * Tick the players.
	 * @param spatialGrid The spatial grid used to handle collisions between level entities.
	 * @param playerInputStateProvider The provider of input state for participants.
	 */
	public void tick(SpatialGrid<ICollidableEntity> spatialGrid, IPlayerInputStateProvider playerInputStateProvider) {
		// Update the positions and angles of view for the players.
		this.updatePlayerPositions(spatialGrid, playerInputStateProvider);

		// Process any player requests to carry out an action.
		this.processPlayerActions();
	}
	
	/**
	 * Get the player for the given participant.
	 * @param participantId The participant id.
	 * @return The player for the given participant.
	 */
	public Player getPlayerForParticipant(int participantId) {
		for (Player player : this.players) {
			if (player.getParticipantId() == participantId) {
				return player;
			}
		}
		
		throw new RuntimeException("no player instance found for participant");
	}
	
	/**
	 * Adds a player to the level, giving them a safe initial spawn position.
	 * @param player The player to add to the level.
	 */
	public void addPlayer(Player player) {
		this.players.add(player);
	}

	/**
	 * Removes a player from the level.
	 * @param player The player to remove from the level.
	 */
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	
	/**
	 * Gets whether there are any players.
	 * @return Whether there are any players.
	 */
	public boolean any() {
		return this.players.size() > 0;
	}

	/**
	 * Update player positions based on player input.
	 * @param playersInputProvider The provider of input for all available players.
	 * @param spatialGrid The spatial grid used to handle collisions between level entites.
	 * @param playerInputStateProvider The provider of input state for participants.
	 */
	private void updatePlayerPositions(SpatialGrid<ICollidableEntity> spatialGrid, IPlayerInputStateProvider playerInputStateProvider) {
		for (Player player : this.players) {
			// Attempt to get the player input state for the current player.
			IPlayerInputState playerInputState = playerInputStateProvider.getState(player.getParticipantId());
			
			// There is nothing to do if there was no input state available fro the player.
			if (playerInputState == null) {
				continue;
			}

			// Update the players current angle of view based on the input state.
			player.setAngleOfView(playerInputState.getAngleOfView());

			// Create offsets to hold the total accumulated x/y offset for the player.
			float xOffset = 0f, yOffset = 0f;

			// Update the x/y offset based on whether the player is moving forwards/backwards/left/right.
			if (playerInputState.isMovingForwards()) {
				yOffset += player.getMovementSpeed() * Math.sin(Math.toRadians(player.getAngleOfView() + 90));
				xOffset += player.getMovementSpeed() * Math.cos(Math.toRadians(player.getAngleOfView() + 90));
			}
			if (playerInputState.isMovingBackwards()) {
				yOffset += player.getMovementSpeed() * Math.sin(Math.toRadians(player.getAngleOfView() + 270));
				xOffset += player.getMovementSpeed() * Math.cos(Math.toRadians(player.getAngleOfView() + 270));
			}
			if (playerInputState.isStrafingLeft()) {
				yOffset += player.getMovementSpeed() * Math.sin(Math.toRadians(player.getAngleOfView() + 360));
				xOffset += player.getMovementSpeed() * Math.cos(Math.toRadians(player.getAngleOfView() + 360));
			}
			if (playerInputState.isStrafingRight()) {
				yOffset += player.getMovementSpeed() * Math.sin(Math.toRadians(player.getAngleOfView() + 180));
				xOffset += player.getMovementSpeed() * Math.cos(Math.toRadians(player.getAngleOfView() + 180));
			}

			// Attempt to move the player.
			player.move(xOffset, yOffset, spatialGrid);
		}
	}

	/**
	 * Process any player actions.
	 * @param playersInputProvider The provider of input for all available players.
	 */
	private void processPlayerActions() {
		// TODO ...
	}
}
