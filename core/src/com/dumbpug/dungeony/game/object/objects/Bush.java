package com.dumbpug.dungeony.game.object.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.game.EntityCollisionFlag;
import com.dumbpug.dungeony.game.object.GameObject;
import com.dumbpug.dungeony.game.object.GameObjectType;
import com.dumbpug.dungeony.game.projectile.Projectile;
import com.dumbpug.dungeony.game.rendering.GameObjectSprite;
import com.dumbpug.dungeony.game.rendering.Resources;
import com.dumbpug.levelgeneration.IEntityProperties;

/**
 * A destructable bush.
 */
public class Bush extends GameObject {
    /**
     * The door sprites.
     */
    private Sprite sprite, destroyedSprite;
    /**
     * Whether the bush has been destroyed.
     */
    private boolean isDestroyed;

    /**
     * Creates a new instance of the Bush class.
     * @param origin The initial origin of the Bush.
     */
    public Bush(Position origin, IEntityProperties properties) {
        super(origin, properties);
        sprite          = Resources.getSprite(GameObjectSprite.BUSH);
        destroyedSprite = Resources.getSprite(GameObjectSprite.BUSH_DESTROYED);
    }

    @Override
    public float getLengthX() {
        return 19f;
    }

    @Override
    public float getLengthY() {
        return 14f;
    }

    @Override
    public float getLengthZ() {
        return 16f;
    }

    @Override
    public int getCollisionLayers() {
        if (this.isDestroyed) {
            return EntityCollisionFlag.NOTHING;
        } else {
            return super.getCollisionLayers();
        }
    }

    @Override
    public int getCollisionMask() {
        if (this.isDestroyed) {
            return EntityCollisionFlag.NOTHING;
        } else {
            return super.getCollisionMask();
        }
    }

    @Override
    public void onEnvironmentEntry(InteractiveEnvironment environment) { }

    @Override
    public void onEnvironmentExit(InteractiveEnvironment environment) { }

    @Override
    public void onProjectileCollision(Projectile projectile) {
        // If the bush is not already destroyed then destroy it and generate some leafy particles.
        if (!this.isDestroyed) {
            // TODO Generate leaf particles!

            this.isDestroyed = true;
        }
    }

    @Override
    public void update(InteractiveEnvironment environment, float delta) { }

    @Override
    public void render(SpriteBatch spriteBatch) {
        // The sprite we are drawing depends on whether the bush is destroyed.
        Sprite spriteToDraw = this.isDestroyed ? this.destroyedSprite : this.sprite;

        // Draw the bush sprite.
        spriteToDraw.setSize(this.getLengthX(), this.getLengthZ());
        spriteToDraw.setPosition(this.getX(), this.getY());
        spriteToDraw.draw(spriteBatch);
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.BUSH;
    }
}
