package com.dumbpug.dungeony.game.projectile.projectiles.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.particles.ParticleEntity;
import com.dumbpug.dungeony.game.rendering.ParticleSprite;
import com.dumbpug.dungeony.game.rendering.Resources;

/**
 * A particle left as part of a bullet trail.
 */
public class BulletTrailParticleEntity extends ParticleEntity<SpriteBatch> {
    /**
     * The particle sprite.
     */
    private Sprite sprite;

    /**
     * Creates a new instance of the BulletTrailParticle class.
     */
    public BulletTrailParticleEntity() {
        this.sprite = Resources.getSprite(ParticleSprite.BULLET_TRAIL);
        this.setLife(0.2f);
    }

    @Override
    public void onUpdate(InteractiveEnvironment environment, float delta) { }

    @Override
    public void onRender(SpriteBatch spriteBatch) {
        float lifeRatio = this.getRemainingLife() / this.getTotalLife();
        this.sprite.setPosition(this.getX(), this.getY());
        this.sprite.setSize(this.getLengthX(), this.getLengthZ());
        this.sprite.setScale(lifeRatio);
        this.sprite.setColor(1,1,1, lifeRatio);
        this.sprite.draw(spriteBatch);
    }

    @Override
    public void onActivate(float emitterPosX, float emitterPosY) {
        // Set the initial particle position.
        this.setX(emitterPosX);
        this.setY(emitterPosY);
    }

    @Override
    public int getCollisionLayers() {
        return 0;
    }

    @Override
    public int getCollisionMask() {
        return 0;
    }

    @Override
    public float getLengthX() {
        return Constants.PROJECTILE_SIZE_MEDIUM;
    }

    @Override
    public float getLengthY() {
        return 0;
    }

    @Override
    public float getLengthZ() {
        return Constants.PROJECTILE_SIZE_MEDIUM;
    }
}
