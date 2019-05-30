package com.dumbpug.dungeony.session.level;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.character.player.Player;
import com.dumbpug.dungeony.session.character.enemy.Enemy;
import com.dumbpug.dungeony.session.item.ItemDrop;

/**
 * Represents an in-game level.
 */
public class Level {
	/**
	 * The provider of input for all available players.
	 */
	private IPlayersInputProvider playersInputProvider;
	/**
	 * The list of all tiles in the level.
	 */
	private ArrayList<Tile> tiles;
	/**
	 * The list of all players in the level.
	 */
	private ArrayList<Player> players = new ArrayList<Player>();
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
	 * @param playersInputProvider The provider of input for all connected players.
	 */
	public Level(ArrayList<Tile> tiles, IPlayersInputProvider playersInputProvider) {
		this.tiles                = tiles;
		this.playersInputProvider = playersInputProvider;
	}
	
	/**
	 * Tick the level.
	 */
	public void tick() {
		// TODO Tick all of the players, updating their state based on the provided playersInputProvider.
		// TODO Tick all characters that are not players.
		// TODO Tick all tick-able level objects.
		// TODO Tick all tick-able level tiles.
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