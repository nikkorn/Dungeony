package com.dumbpug.dungeony.client.level;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.level.tile.Tiles;

/**
 * A client-side representation of server-side level state.
 */
public class LevelState {
	/**
	 * The level depth.
	 */
	private int depth;
	/**
	 * The level tiles.
	 */
	private Tiles tiles;
	/**
	 * The list of players in the level.
	 */
	private ArrayList<PlayerState> players = new ArrayList<PlayerState>();
	
	/**
	 * Gets the list of players in the level.
	 * @return The list of players in the level.
	 */
	public ArrayList<PlayerState> getPlayers() {
		return players;
	}

	/**
	 * Gets the level tiles.
	 * @return The level tiles.
	 */
	public Tiles getTiles() {
		return tiles;
	}

	/**
	 * Gets the level depth.
	 * @return The level depth.
	 */
	public int getDepth() {
		return depth;
	}
}
