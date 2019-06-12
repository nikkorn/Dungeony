package com.dumbpug.dungeony.session.level.tile.tiles;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.item.ItemType;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.tile.Tile;
import com.dumbpug.dungeony.session.level.tile.TileType;
import com.dumbpug.dungeony.session.level.tile.decoration.Decoration;

/**
 * Represents a base door tile.
 */
public class Door extends Tile {
	/**
	 * The door type.
	 */
	private DoorType doorType;
	/**
	 * Whether the door is open.
	 */
	protected boolean isOpen = false;

	/**
	 * Creates a new instance of the Door class.
	 * @param doorType
	 * @param x
	 * @param y
	 * @param direction
	 * @param decorations
	 */
	public Door(DoorType doorType, int x, int y, Direction direction, ArrayList<Decoration> decorations) {
		super(x, y, direction, decorations);
		this.doorType = doorType;
	}
	
	/**
	 * Gets the type of the door.
	 * @return The type of the door.
	 */
	public DoorType getDoorType() { return this.doorType; }

	@Override
	public TileType getType() { return TileType.DOOR; }

	@Override
	public boolean isWalkable() {
		// The door tile will be walkable as long as the door is open.
		return this.isOpen;
	}

	@Override
	public boolean onInteraction(ItemType itemType) {
		return false;
	}

	@Override
	public void onTick() {}
}
