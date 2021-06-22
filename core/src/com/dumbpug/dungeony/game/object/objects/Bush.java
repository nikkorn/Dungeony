package com.dumbpug.dungeony.game.object.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.Area;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.game.EntityCollisionFlag;
import com.dumbpug.dungeony.game.object.GameObject;
import com.dumbpug.dungeony.game.object.GameObjectType;
import com.dumbpug.dungeony.game.object.particles.LeafEmitterEntity;
import com.dumbpug.dungeony.game.projectile.Projectile;
import com.dumbpug.dungeony.game.rendering.GameObjectSprite;
import com.dumbpug.dungeony.game.rendering.Resources;
import com.dumbpug.dungeony.utilities.dynamicsprite.DynamicSprite;
import com.dumbpug.dungeony.utilities.dynamicsprite.SqueezeDynamicSpriteModifier;
import com.dumbpug.levelgeneration.IEntityProperties;

/**
 * A destructable bush.
 */
public class Bush extends GameObject {
    /**
     * The bush sprites.
     */
    private DynamicSprite sprite;
    private Sprite shadow, destroyedSprite;
    /**
     * Whether the bush has been destroyed.
     */
    private boolean isDestroyed;
    /**
     * The leaf particle emitter.
     */
    private LeafEmitterEntity leafEmitterEntity;

    /**
     * Creates a new instance of the Bush class.
     * @param origin The initial origin of the Bush.
     */
    public Bush(Position origin, IEntityProperties properties) {
        super(origin, properties);
        sprite          = new DynamicSprite(Resources.getSprite(GameObjectSprite.BUSH));
        shadow          = Resources.getSprite(GameObjectSprite.BUSH_SHADOW);
        destroyedSprite = Resources.getSprite(GameObjectSprite.BUSH_DESTROYED);
    }

    @Override
    public float getLengthX() {
        return 17f;
    }

    @Override
    public float getLengthY() {
        return 14f;
    }

    @Override
    public float getLengthZ() {
        return 15f;
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
    public void onPositioned() {
        leafEmitterEntity = new LeafEmitterEntity(this.getOrigin());
    }

    @Override
    public void onEnvironmentEntry(InteractiveEnvironment environment) {
        environment.addEntity(this.leafEmitterEntity);
    }

    @Override
    public void onEnvironmentExit(InteractiveEnvironment environment) {
        environment.removeEntity(this.leafEmitterEntity);
    }

    @Override
    public void onProjectileCollision(Projectile projectile) {
        // If the bush is not already destroyed then destroy it and generate some leafy particles.
        if (!this.isDestroyed) {
            // Generate some leaf particles!
            this.leafEmitterEntity.spitThemOut();

            // this.isDestroyed = true;
        }

        this.sprite.apply(new SqueezeDynamicSpriteModifier(0.2f, 75));
    }

    @Override
    public void update(InteractiveEnvironment environment, float delta) {
        this.sprite.update(delta);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        // What we render depends on whether the bush is destroyed or not.
        if (isDestroyed) {
            // Draw the destroyed bush sprite.
            this.destroyedSprite.setSize(this.getLengthX(), this.getLengthZ());
            this.destroyedSprite.setPosition(this.getX(), this.getY());
            this.destroyedSprite.draw(spriteBatch);
        } else {
            // Draw the shadow under the bush.
            this.shadow.setPosition(this.getX(), this.getY() - (this.shadow.getHeight() / 4f));
            this.shadow.draw(spriteBatch);

            // Draw the bush sprite.
            this.sprite.setSize(this.getLengthX(), this.getLengthZ());
            this.sprite.setPosition(this.getX(), this.getY());
            this.sprite.draw(spriteBatch);
        }
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.BUSH;
    }
}
