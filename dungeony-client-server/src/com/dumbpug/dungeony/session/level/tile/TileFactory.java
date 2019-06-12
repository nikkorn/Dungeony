package com.dumbpug.dungeony.session.level.tile;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.tile.decoration.Decoration;
import com.dumbpug.dungeony.session.level.tile.decoration.DecorationType;
import com.dumbpug.dungeony.session.level.tile.tiles.Door;
import com.dumbpug.dungeony.session.level.tile.tiles.DoorType;
import com.dumbpug.dungeony.session.level.tile.tiles.Empty;
import com.dumbpug.dungeony.session.level.tile.tiles.Wall;
import dungen.tile.Entity;
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
		return new Empty(generated.getX(), generated.getY(), getDirection(generated.getDirection()), decorations);
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
		return new Door(doorType, generated.getX(), generated.getY(), getDirection(generated.getDirection()), decorations);
	}
	
	/**
	 * Create a wall tile based on details of a generated tile.
	 * @param generated The generated tile details.
	 * @param decorations The list of decorations for the tile.
	 * @returns A wall tile based on details of a generated tile.
	 */
	private static Tile createWallTile(ITileDetails generated, ArrayList<Decoration> decorations) {
		// Create and return the wall tile.
		return new Wall(generated.getX(), generated.getY(), getDirection(generated.getDirection()), decorations);
	}
	
	/**
	 * Create the list of decorations for a generated tile.
	 * @param generated The generated tile details.
	 * @returns The list of decorations for a generated tile.
	 */
	private static ArrayList<Decoration> createTileDecorations(ITileDetails generated) {
		// Create an empty list to hold the decorations defined as generated entities.
		ArrayList<Decoration> decorations = new ArrayList<Decoration>();
		
		// Any of the entities attached to our generated tile could be a decoration.
		for (Entity entity : generated.getEntities()) {
			// Is the entity a tile decoration?
			if (DecorationType.hasType(entity.getId())) {
				// Get the type of the decoration.
				DecorationType decorationType = DecorationType.valueOf(entity.getId());
				
				// Create and add the decoration to the list of decorations for the current tile.
				decorations.add(new Decoration(decorationType, getDirection(entity.getFacingDirection())));
			}
		}
		
		// Return the list of tile decorations.
		return decorations;
	}
	
	/**
	 * Get the direction based on the generated direction type.
	 * @param value The generated direction type.
	 * @return The direction based on the generated direction type.
	 */
	private static Direction getDirection(dungen.Direction value) {
		switch (value) {
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
