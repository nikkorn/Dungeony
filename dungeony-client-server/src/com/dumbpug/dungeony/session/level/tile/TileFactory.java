package com.dumbpug.dungeony.session.level.tile;

import dungen.tile.ITileDetails;

/**
 * Factory for creating Tile instances.
 */
public class TileFactory {
	
	/**
	 * Create a Tile instance based on details of a generated tile.
	 * @param generated The generated tile details.
	 * @returns A Tile instance based on details of a generated tile.
	 */
	public static Tile createTile(ITileDetails generated) {
		switch (generated.getType()) {
			case EMPTY:
				return createEmptyTile(generated);
			case ENTRANCE:
				return createDoorTile(generated);
			case WALL:
				return createWallTile(generated);
			default:
				throw new RuntimeException("unknown generated time type: " + generated.getType());
		}
	}
	
	/**
	 * Create an empty tile based on details of a generated tile.
	 * @param generated The generated tile details.
	 * @returns An empty tile based on details of a generated tile.
	 */
	private static Tile createEmptyTile(ITileDetails generated) {
		return null;
	}
	
	/**
	 * Create a door tile based on details of a generated tile.
	 * @param generated The generated tile details.
	 * @returns A door tile based on details of a generated tile.
	 */
	private static Tile createDoorTile(ITileDetails generated) {
		return null;
	}
	
	/**
	 * Create a wall tile based on details of a generated tile.
	 * @param generated The generated tile details.
	 * @returns A wall tile based on details of a generated tile.
	 */
	private static Tile createWallTile(ITileDetails generated) {
		return null;
	}
}
