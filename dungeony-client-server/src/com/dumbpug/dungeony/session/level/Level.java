package com.dumbpug.dungeony.session.level;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.character.player.Player;
import com.dumbpug.dungeony.session.character.player.Players;
import com.dumbpug.dungeony.session.character.enemy.Enemy;
import com.dumbpug.dungeony.session.item.ItemDrop;
import com.dumbpug.dungeony.session.level.grid.SpatialGrid;
import com.dumbpug.dungeony.session.level.tile.Tile;

/**
 * Represents an in-game level.
 */
public class Level {
	/**
	 * The spatial grid used to handle collisions between level entites.
	 */
	private SpatialGrid<ILevelPositionedEntity> spatialGrid;
	/**
	 * The list of all tiles in the level.
	 */
	private ArrayList<Tile> tiles;
	/**
	 * The collection of all players in the level.
	 */
	private Players players = new Players();
	/**
	 * The list of all enemies in the level.
	 */
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	/**
	 * The list of dropped items in the level.
	 */
	private ArrayList<ItemDrop> itemDrops = new ArrayList<ItemDrop>();
	
	/**
	 * Creates a new instance of the Level class.
	 * @param tiles The tiles that the level is composed of.
	 * @param spatialGrid The spatial grid used to handle collisions between level entites.
	 */
	public Level(ArrayList<Tile> tiles, SpatialGrid<ILevelPositionedEntity> spatialGrid) {
		this.tiles       = tiles;
		this.spatialGrid = spatialGrid;
	}
	
	/**
	 * Tick the level.
	 * @param playersInputProvider The provider of input for all available players.
	 */
	public void tick(IPlayersInputProvider playersInputProvider) {
		// Update the positions and angles of view for the players.
		this.players.updatePlayerPositions(playersInputProvider, this.spatialGrid);
		
		// TODO Tick all of the players, processing any actions in the process.
		// TODO Tick all characters that are not players.
		// TODO Tick all tick-able level objects.
		// TODO Tick all tick-able level tiles.
	}

	/**
	 * Adds a player to the level.
	 * @param player The player to add to the level.
	 */
	public void addPlayer(Player player) {
		this.spatialGrid.add(player);
		this.players.addPlayer(player);
	}

	/**
	 * Removes a player from the level.
	 * @param player The player to remove from the level.
	 */
	public void removePlayer(Player player) {
		this.spatialGrid.remove(player);
		this.players.removePlayer(player);
	}
}