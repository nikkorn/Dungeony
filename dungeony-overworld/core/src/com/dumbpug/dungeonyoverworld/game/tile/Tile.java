package com.dumbpug.dungeonyoverworld.game.tile;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.dungeonyoverworld.game.area.AreaLink;
import com.dumbpug.dungeonyoverworld.game.container.Container;

public abstract class Tile {
    /**
     * Gets the tile type.
     * @return The tile type.
     */
    public abstract TileType getType();

    /**
     * Gets the tile name.
     * @return The tile name
     */
    public abstract String getName();

    /**
     * Gets the tile description.
     * @return The tile description
     */
    public abstract String getDescription();

    /**
     * Gets the tile sprite.
     * @return The tile sprite.
     */
    public abstract Sprite getSprite();

    /**
     * Gets the tile container.
     * @return The tile container, or null if no container exists.
     */
    public abstract Container getContainer();

    /**
     * Gets the tile area link.
     * @return The tile area link, or null if no link exists.
     */
    public abstract AreaLink getAreaLink();
}
