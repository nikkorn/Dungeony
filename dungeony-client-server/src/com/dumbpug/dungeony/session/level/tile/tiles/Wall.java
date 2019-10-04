package com.dumbpug.dungeony.session.level.tile.tiles;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.item.ItemType;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.tile.Tile;
import com.dumbpug.dungeony.session.level.tile.TileCategory;
import com.dumbpug.dungeony.session.level.tile.TileType;
import com.dumbpug.dungeony.session.level.tile.decoration.Decoration;

/**
 * Represents a basic wall tile.
 */
public class Wall extends Tile {

	/**
	 * Creates a new instance of the Wall class.
	 * @param x The x tile-based position.
	 * @param y The y tile-based position.
	 * @param direction The direction of the tile.
	 * @param decorations The decorations attached to the tile.
	 */
	public Wall(int x, int y, Direction direction, ArrayList<Decoration> decorations) {
		super(x, y, direction, decorations);
	}

	@Override
	public TileCategory getCategory() {
		return TileCategory.WALL;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

	@Override
	public boolean onInteraction(ItemType itemType) {
		return false;
	}

	@Override
	public void onTick() {}
	
	@Override
	public TileType getType() {
		// TODO Figure this out based on other state!
		return TileType.STONE_WALL;
	}
}
