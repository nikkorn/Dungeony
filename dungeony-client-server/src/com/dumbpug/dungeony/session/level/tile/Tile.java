package com.dumbpug.dungeony.session.level.tile;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.item.ItemType;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.tile.decoration.Decoration;

/**
 * Base class for all tiles.
 */
public abstract class Tile {
	/**
	 * The direction of the tile.
	 */
	private Direction direction;
	/**
	 * The decorations attached to the tile.
	 */
	private ArrayList<Decoration> decorations;
	
	/**
	 * Creates a new instance of the Tile class.
	 * @param direction The direction of the tile.
	 * @param decorations The decorations attached to the tile.
	 */
	public Tile(Direction direction, ArrayList<Decoration> decorations) {
		this.direction   = direction;
		this.decorations = decorations;
	}
	
	/**
	 * Gets the direction of the tile.
	 * @return The direction fo the tile.
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * Gets the tile decorations.
	 * @return The tile decorations.
	 */
	public ArrayList<Decoration> getDecorations() {
		return this.decorations;
	}
	
	/**
	 * Gets the type of the tile.
	 * @return The type of the tile.
	 */
	public abstract TileType getType();
	
	/**
	 * Gets whether the tile is walkable.
	 * @return Whether the tile is walkable.
	 */
	public abstract boolean isWalkable(); 
	
	/**
	 * Called in response to an item being used on the tile.
	 * @param itemType The type of the item being used.
	 * @returns Whether the item was consumed in it use.
	 */
	public abstract boolean useItem(ItemType itemType);
}
