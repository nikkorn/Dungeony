package com.dumbpug.dungeony.session.character.enemy;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.level.ICollidableEntity;
import com.dumbpug.dungeony.session.level.grid.SpatialGrid;

/**
 * Represents a collection of enemies in the context of a level.
 */
public class Enemies {
    /**
     * The list of enemies.
     */
    private ArrayList<Enemy> enemies;
    
    /**
     * Creates a new instance of the Enemies class.
     * @param enemies The initial enemies in the collection of enemies.
     */
    public Enemies(ArrayList<Enemy> enemies) {
    	this.enemies = enemies;
    }

    /**
     * Tick the enemies.
     * @param spatialGrid The spatial grid used to handle collisions between level entities.
     */
    public void tick(SpatialGrid<ICollidableEntity> spatialGrid) {
        // TODO ...
    }
}
