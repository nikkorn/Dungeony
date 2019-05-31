package com.dumbpug.dungeony.session.character.player;

import java.util.ArrayList;
import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.session.level.ILevelPositionedEntity;
import com.dumbpug.dungeony.session.level.IPlayerInputState;
import com.dumbpug.dungeony.session.level.IPlayersInputProvider;
import com.dumbpug.dungeony.session.level.grid.SpatialGrid;

/**
 * Represents a colleciton of players in the context of a level.
 */
public class Players {
	/**
	 * The list of players.
	 */
	private ArrayList<Player> players = new ArrayList<Player>();
	
	/**
	 * Update player positions based on player input.
	 * @param playersInputProvider The provider of input for all available players.
	 * @param spatialGrid The spatial grid used to handle collisions between level entites.
	 */
	public void updatePlayerPositions(IPlayersInputProvider playersInputProvider, SpatialGrid<ILevelPositionedEntity> spatialGrid) {
		for (Player player : this.players) {
			// Try to get the input state for the current player.
			IPlayerInputState playerInputState = playersInputProvider.getInputStateForPlayer(player.getPlayerId());
			
			// If there is no input state for the player then there is nothing to do.
			if (playerInputState == null) {
				continue;
			}
			
			// Update the players current angle of view based on the input state.
			player.setAngleOfView(playerInputState.getAngleOfView());
			
			// Create offsets to hold the total accumulated x/y offset for the player.
			float xOffset = 0f, yOffset = 0f;
			
			// UPdate the x/y offset based on whether the player is moving forwards/backwards/left/right.
			if (playerInputState.isMovingForwards()) {
				yOffset += Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM * Math.sin(Math.toRadians(player.getAngleOfView() + 90));
				xOffset += Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM * Math.cos(Math.toRadians(player.getAngleOfView() + 90));
			} 
			if (playerInputState.isMovingBackwards()) {
				yOffset += Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM * Math.sin(Math.toRadians(player.getAngleOfView() + 270));
				xOffset += Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM * Math.cos(Math.toRadians(player.getAngleOfView() + 270));
			} 
			if (playerInputState.isStrafingLeft()) {
				yOffset += Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM * Math.sin(Math.toRadians(player.getAngleOfView() + 360));
				xOffset += Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM * Math.cos(Math.toRadians(player.getAngleOfView() + 360));
			} 
			if (playerInputState.isStrafingRight()) {
				yOffset += Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM * Math.sin(Math.toRadians(player.getAngleOfView() + 180));
				xOffset += Constants.CHARACTER_MOVEMENT_SPEED_MEDIUM * Math.cos(Math.toRadians(player.getAngleOfView() + 180));
			}
			
			// TODO Using the spatial grid, find any entities that are in any grid cells that the player would have moved through.
			// Stop the player offset from pushing them into another entity.
			
			// Update the player x/y position.
			player.getPosition().setX(player.getPosition().getX() + xOffset);
			player.getPosition().setY(player.getPosition().getY() + yOffset);
		}
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
}
