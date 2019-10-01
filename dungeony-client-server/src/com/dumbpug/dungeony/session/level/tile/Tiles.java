package com.dumbpug.dungeony.session.level.tile;

import java.util.ArrayList;

/**
 * Represents a collection of tiles in the context of a level.
 */
public class Tiles {
    /**
     * The list of tiles.
     */
    private ArrayList<Tile> tiles;
    
    /**
     * Creates a new instance of the Tiles class.
     * @param tiles The tiles in the collection of tiles.
     */
    public Tiles(ArrayList<Tile> tiles) {
    	this.tiles = tiles;
    }

    /**
     * Tick any tick-able tiles.
     */
    public void tick() {
        // Tick every tile!
    	for (Tile tile : this.tiles) {
    		tile.onTick();
    	}
    }
}
