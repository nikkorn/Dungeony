package com.dumbpug.dungeony.game.character.particles.walking;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.particles.Particle;
import com.dumbpug.dungeony.game.character.FacingDirection;
import com.dumbpug.dungeony.game.rendering.ParticleSprite;
import com.dumbpug.dungeony.game.rendering.Resources;

/**
 * A dust particle that slowly moves away from an entity waling in the opposite direction.
 */
public class WalkingDustParticle extends Particle<SpriteBatch> {
    /**
     * The facing direction of the entity that spawned the particle.
     */
    private FacingDirection direction;
    /**
     * The particle sprite.
     */
    private Sprite sprite;

    /**
     * Creates a new instance of the WalkingDustParticle class.
     * @param direction The facing direction of the entity that spawned the particle.
     */
    public WalkingDustParticle(FacingDirection direction) {
        this.direction = direction;

        switch (direction) {
            case LEFT:
                this.sprite = Resources.getSprite(ParticleSprite.WALKING_DUST_RIGHT);
                break;
            case RIGHT:
                this.sprite = Resources.getSprite(ParticleSprite.WALKING_DUST_LEFT);
                break;
            default:
        }
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
    public void onActivate(float emitterPosX, float emitterPosY) {
        // Set the initial particle position.
        this.setX(emitterPosX);
        this.setY(emitterPosY);
    }

    @Override
    public void onUpdate(InteractiveEnvironment environment, float delta) {
        environment.moveByAngle(this, this.direction.getAngle(), 10f, delta);
    }

    @Override
    public void onRender(SpriteBatch spriteBatch) {
        this.sprite.setPosition(this.getX(), this.getY());
        this.sprite.setSize(this.getLengthX(), this.getLengthZ());
        this.sprite.draw(spriteBatch);
    }

    @Override
    public float getLengthX() {
        return 6f;
    }

    @Override
    public float getLengthY() {
        return 0;
    }

    @Override
    public float getLengthZ() {
        return 5f;
    }
}
