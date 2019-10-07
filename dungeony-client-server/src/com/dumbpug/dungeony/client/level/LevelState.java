package com.dumbpug.dungeony.client.level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import com.dumbpug.dungeony.session.level.tile.TileState;

/**
 * A client-side representation of server-side level state.
 */
public class LevelState {
	/**
	 * The map of level tile positions to level tile state.
	 */
	private HashMap<String, TileState> tilesMap = new HashMap<String, TileState>();
	/**
	 * The list of players in the level.
	 */
	private ArrayList<PlayerState> players = new ArrayList<PlayerState>();
	
	/**
	 * Creates a new instance of the LevelState class.
	 * @param tiles The tiles that the level is composed of.
	 */
	public LevelState(ArrayList<TileState> tiles) {
		for (TileState tile : tiles) {
			this.tilesMap.put(LevelState.getTilePositionKey(tile.getX(), tile.getY()), tile);
		}
	}
	
	/**
	 * Gets the list of players in the level.
	 * @return The list of players in the level.
	 */
	public ArrayList<PlayerState> getPlayers() {
		return players;
	}
	
	/**
	 * Gets the level tile at the given x/y position.
	 * @return The level tile at the given x/y position.
	 */
	public TileState getTile(int x, int y) {
		return this.tilesMap.get(LevelState.getTilePositionKey(x, y));
	}

	/**
	 * Gets the level tiles.
	 * @return The level tiles.
	 */
	public Collection<TileState> getTiles() {
		return this.tilesMap.values();
	}
	
	/**
	 * Gets a unique stringy key based on an x/y position.
	 * @param x The x position.
	 * @param y The y position.
	 * @return A unique stringy key based on an x/y position.
	 */
	private static String getTilePositionKey(int x, int y) {
		return x + "-" + y;
	}
}
