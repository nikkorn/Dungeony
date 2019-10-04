package com.dumbpug.dungeony.session.level.tile.tiles;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.item.ItemType;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.tile.Tile;
import com.dumbpug.dungeony.session.level.tile.TileCategory;
import com.dumbpug.dungeony.session.level.tile.TileType;
import com.dumbpug.dungeony.session.level.tile.decoration.Decoration;

/**
 * Represents an empty tile.
 */
public class Empty extends Tile {

	/**
	 * Creates a new instance of the Empty class.
	 * @param x The x tile-based position.
	 * @param y The y tile-based position.
	 * @param direction The direction of the tile.
	 * @param decorations The decorations attached to the tile.
	 */
	public Empty(int x, int y, Direction direction, ArrayList<Decoration> decorations) {
		super(x, y, direction, decorations);
	}

	@Override
	public TileCategory getCategory() {
		return TileCategory.EMPTY;
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public boolean onInteraction(ItemType itemType) {
		return false;
	}

	@Override
	public void onTick() {}
	
	@Override
	public TileType getType() {
		// TODO Figure out if we ever want tile types that represent something other than just an empty tile.
		// This could be something like a hole, lava, water etc.
		return TileType.EMPTY;
	}
}
