package com.dumbpug.dungeony.session.level.tile;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.tile.decoration.DecorationState;

/**
 * The client-side representation of an updatable server-side tile.
 */
public class TileState {
	/**
	 * The tile type.
	 */
	private TileType type;
	/**
	 * The tile-based x/y position.
	 */
	private int x, y;
	/**
	 * The direction of the tile.
	 */
	private Direction direction;
	/**
	 * The decorations attached to the tile.
	 */
	private ArrayList<DecorationState> decorations;
	
	/** 
	 * Creates a new instance of the TileState class.
	 * @param x The x tile-based position.
	 * @param y The y tile-based position.
	 * @param direction The direction of the tile.
	 * @param decorations The decorations attached to the tile.
	 */
	public TileState(TileType type, int x, int y, Direction direction, ArrayList<DecorationState> decorations) {
		this.type        = type;
		this.x           = x;
		this.y           = y;
		this.direction   = direction;
		this.decorations = decorations;
	}
	
	/**
	 * Gets the tile type.
	 * @return The tile type.
	 */
	public TileType getType() {
		return this.type;
	}
	
	/**
     * Gets the tile-based x position.
     * @return The tile-based x position.
     */
    public int getX() {
    	return this.x;
    }
    
    /**
     * Gets the tile-based y position.
     * @return The tile-based y position.
     */
    public int getY() {
    	return this.y;
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
	public ArrayList<DecorationState> getDecorations() {
		return this.decorations;
	}
}
