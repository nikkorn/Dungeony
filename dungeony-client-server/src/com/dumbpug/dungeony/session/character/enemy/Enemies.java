package com.dumbpug.dungeony.session.character.enemy;

import java.util.ArrayList;
import com.dumbpug.dungeony.session.level.ILevelPositionedEntity;
import com.dumbpug.dungeony.session.level.grid.SpatialGrid;

/**
 * Represents a collection of enemies in the context of a level.
 */
public class Enemies {
    /**
     * The list of enemies.
     */
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    /**
     * Tick the enemies.
     * @param spatialGrid The spatial grid used to handle collisions between level entities.
     */
    public void tick(SpatialGrid<ILevelPositionedEntity> spatialGrid) {
        // TODO ...
    }
}
