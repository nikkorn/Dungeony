package com.dumbpug.dungeony.session.level.tile;

import java.util.ArrayList;
import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.session.item.ItemType;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.ICollidableEntity;
import com.dumbpug.dungeony.session.level.Position;
import com.dumbpug.dungeony.session.level.CollidableEntityType;
import com.dumbpug.dungeony.session.level.tile.decoration.Decoration;

/**
 * Base class for all tiles.
 */
public abstract class Tile implements ICollidableEntity {
	/**
	 * The tile-based x/y position.
	 */
	private int x, y;
	/**
	 * The absolute position of the tile.
	 */
	private Position position;
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
	 * @param x The x tile-based position.
	 * @param y The y tile-based position.
	 * @param direction The direction of the tile.
	 * @param decorations The decorations attached to the tile.
	 */
	public Tile(int x, int y, Direction direction, ArrayList<Decoration> decorations) {
		this.x           = x;
		this.y           = y;
		this.position    = new Position(x * Constants.LEVEL_TILE_SIZE, y * Constants.LEVEL_TILE_SIZE);
		this.direction   = direction;
		this.decorations = decorations;
	}
	
	/**
	 * Gets the position of the tile.
	 * @return The position of the tile.
	 */
    @Override
	public Position getPosition() {
		return this.position;
	}
    
    /**
     * Gets the tile-based x position.
     * @return The tile-based x position.
     */
    public int getTileX() {
    	return this.x;
    }
    
    /**
     * Gets the tile-based y position.
     * @return The tile-based y position.
     */
    public int getTileY() {
    	return this.y;
    }
    
    /**
	 * Gets the width of the tile.
	 * @return The width of the tile.
	 */
    @Override
	public float getWidth() {
		return Constants.LEVEL_TILE_SIZE;
	}

    /**
	 * Gets the height of the tile.
	 * @return The height of the tile.
	 */
	@Override
	public float getHeight() {
		return Constants.LEVEL_TILE_SIZE;
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
	 * Gets the type of the level positioned entity.
	 * @return The type of the level positioned entity.
	 */
	@Override
	public CollidableEntityType getCollidableEntityType() {
		return CollidableEntityType.TILE;
	}
	
	/**
	 * Gets whether this collidable entity will collide (not be able to pass through) another entity.
	 * @param entity The other entity.
	 * @return Whether this collidable entity will collides (not be able to pass through) another entity.
	 */
	@Override
	public boolean collidesWith(ICollidableEntity entity) {
		// Entities will collide with a tile if the tile is not walkable.
		return !this.isWalkable();
	}
	
	/**
	 * Gets the category of the tile.
	 * @return The category of the tile.
	 */
	public abstract TileCategory getCategory();
	
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
	 * Called in response to the tile being interacted with.
	 * @param itemType The type of the item being used as part of the interaction.
	 * @returns Whether the item was consumed in the interaction.
	 */
	public abstract boolean onInteraction(ItemType itemType);
	
	/**
	 * Tick the tile.
	 */
	public abstract void onTick();
}
