package com.dumbpug.dungeony.game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.Entity;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.game.EntityCollisionFlag;
import com.dumbpug.dungeony.game.projectile.Projectile;
import com.dumbpug.levelgeneration.IEntityProperties;

/**
 * An object that can exist in the game.
 */
public abstract class GameObject extends Entity<SpriteBatch> {
    /**
     * Creates a new instance of the GameObject class.
     * @param origin The initial origin of the GameObject.
     * @param properties The entity properties.
     */
    public GameObject(Position origin, IEntityProperties properties) {
        super(origin);
    }

    @Override
    public int getCollisionLayers() {
        return EntityCollisionFlag.OBJECT;
    }

    @Override
    public int getCollisionMask() {
        // Everything should collide with an object by default.
        return EntityCollisionFlag.WALL | EntityCollisionFlag.CHARACTER | EntityCollisionFlag.PICKUP | EntityCollisionFlag.PROJECTILE | EntityCollisionFlag.OBJECT;
    }

    /**
     * Called after the absolute position for this entity has been applied.
     */
    public void onPositioned() {
        // An object does nothing by default when it is positioned.
    }

    /**
     * Called when the game object is hit with a projectile.
     * @param projectile The projectile that hit the object.
     */
    public void onProjectileCollision(Projectile projectile) {
        // An object does nothing by default when hit with a projectile.
    }

    /**
     * Gets the type of the game object.
     * @return The type of the game object.
     */
    public abstract GameObjectType getType();
}
