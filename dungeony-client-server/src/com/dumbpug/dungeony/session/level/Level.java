package com.dumbpug.dungeony.session.level;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.character.enemy.Enemies;
import com.dumbpug.dungeony.session.character.player.Player;
import com.dumbpug.dungeony.session.character.player.Players;
import com.dumbpug.dungeony.session.item.ItemDrop;
import com.dumbpug.dungeony.session.level.grid.SpatialGrid;
import com.dumbpug.dungeony.session.level.tile.Tiles;

/**
 * Represents an in-game level.
 */
public class Level {
	/**
	 * The spatial grid used to handle collisions between level entities.
	 */
	private SpatialGrid<ILevelPositionedEntity> spatialGrid;
	/**
	 * The collection of all tiles in the level.
	 */
	private Tiles tiles = new Tiles();
	/**
	 * The collection of all players in the level.
	 */
	private Players players = new Players();
	/**
	 * The collection of all enemies in the level.
	 */
	private Enemies enemies = new Enemies();
	/**
	 * The list of dropped items in the level.
	 */
	private ArrayList<ItemDrop> itemDrops = new ArrayList<ItemDrop>();
	
	/**
	 * Creates a new instance of the Level class.
	 * @param tiles The tiles that the level is composed of.
	 * @param spatialGrid The spatial grid used to handle collisions between level entites.
	 */
	public Level(Tiles tiles, SpatialGrid<ILevelPositionedEntity> spatialGrid) {
		this.tiles       = tiles;
		this.spatialGrid = spatialGrid;
	}
	
	/**
	 * Tick the level.
	 * @param playersInputProvider The provider of input for all available players.
	 */
	public void tick(IPlayersInputProvider playersInputProvider) {
		// Tick all of the players, processing any movements and actions in the process.
		this.players.tick(playersInputProvider, this.spatialGrid);

		// Tick all of the enemies.
		this.enemies.tick(this.spatialGrid);

		// TODO Tick all tick-able level objects.

		// Tick all tick-able level tiles.
		this.tiles.tick();
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