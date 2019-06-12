package com.dumbpug.dungeony.session.level.tile;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.tile.decoration.Decoration;
import com.dumbpug.dungeony.session.level.tile.tiles.Door;
import com.dumbpug.dungeony.session.level.tile.tiles.DoorType;
import com.dumbpug.dungeony.session.level.tile.tiles.Empty;
import com.dumbpug.dungeony.session.level.tile.tiles.Wall;
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
		// The type of tile we created depends on the type of the generated tile.
		switch (generated.getType()) {
			case EMPTY:
				return createEmptyTile(generated, createTileDecorations(generated));
			case ENTRANCE:
				return createDoorTile(generated, createTileDecorations(generated));
			case WALL:
				return createWallTile(generated, createTileDecorations(generated));
			default:
				throw new RuntimeException("unknown generated time type: " + generated.getType());
		}
	}
	
	/**
	 * Create an empty tile based on details of a generated tile.
	 * @param generated The generated tile details.
	 * @param decorations The list of decorations for the tile.
	 * @returns An empty tile based on details of a generated tile.
	 */
	private static Tile createEmptyTile(ITileDetails generated, ArrayList<Decoration> decorations) {
		// Create and return the empty tile.
		return new Empty(generated.getX(), generated.getY(), getTileDirection(generated), decorations);
	}
	
	/**
	 * Create a door tile based on details of a generated tile.
	 * @param generated The generated tile details.
	 * @param decorations The list of decorations for the tile.
	 * @returns A door tile based on details of a generated tile.
	 */
	private static Tile createDoorTile(ITileDetails generated, ArrayList<Decoration> decorations) {
		// The type of the door will be a tile attribute.
		DoorType doorType = DoorType.valueOf(generated.getAttributes().getString("door_type"));
		
		// Create and return the door tile.
		return new Door(doorType, generated.getX(), generated.getY(), getTileDirection(generated), decorations);
	}
	
	/**
	 * Create a wall tile based on details of a generated tile.
	 * @param generated The generated tile details.
	 * @param decorations The list of decorations for the tile.
	 * @returns A wall tile based on details of a generated tile.
	 */
	private static Tile createWallTile(ITileDetails generated, ArrayList<Decoration> decorations) {
		// Create and return the wall tile.
		return new Wall(generated.getX(), generated.getY(), getTileDirection(generated), decorations);
	}
	
	/**
	 * Create the list of decorations for a generated tile.
	 * @param generated The generated tile details.
	 * @returns The list of decorations for a generated tile.
	 */
	private static ArrayList<Decoration> createTileDecorations(ITileDetails generated) {
		// TODO Parse the decorations from the generated tile entites.
		return new ArrayList<Decoration>();
	}
	
	/**
	 * Get the direction of a generated tile.
	 * @param generated The generated tile details.
	 * @return The direction of a generated tile.
	 */
	private static Direction getTileDirection(ITileDetails generated) {
		switch (generated.getDirection()) {
			case EAST:
				return Direction.EAST;
			case NORTH:
				return Direction.NORTH;
			case SOUTH:
				return Direction.SOUTH;
			case WEST:
				return Direction.WEST;
			case UNKNOWN:
			default:
				return Direction.NORTH;
		}
	}
}
