package com.dumbpug.dungeony.game.object.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dumbpug.dungeony.engine.IPosition;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.particles.ParticleEntity;
import com.dumbpug.dungeony.game.EntityCollisionFlag;
import com.dumbpug.dungeony.game.rendering.ParticleAnimation;
import com.dumbpug.dungeony.game.rendering.Resources;
import com.dumbpug.dungeony.rendering.Animation;
import java.util.Random;

public class LeafParticleEntity extends ParticleEntity<SpriteBatch> {
    /**
     * The RNG to use in for this particle.
     */
    private Random rng;
    /**
     * The angle to move the particle.
     */
    private float angleOfMovement;
    /**
     * The distance to move the particle.
     */
    private float distanceOfMovement;
    /**
     * The particle animation.
     */
    private Animation animation;

    /**
     *
     */
    public LeafParticleEntity() {
        this.rng = new Random();

        // Pick a random impact sprite for this particle.
        this.animation = Resources.getAnimation(ParticleAnimation.BUSH_FALLING_LEAF);

        // Pick a random angle of movement.
        this.angleOfMovement = this.rng.nextFloat() * 360f;

        // Pick a random distance of movement.
        this.distanceOfMovement =  1f + (this.rng.nextFloat() * 500f);

        this.setLife(1000f);
    }

    @Override
    public void onUpdate(InteractiveEnvironment environment, float delta) {
        if (this.animation.isFinished())
            return;

        environment.moveByAngle(this, this.angleOfMovement, this.distanceOfMovement, delta);
    }

    @Override
    public void onRender(SpriteBatch spriteBatch) {
        // Get the current animation frame for the animation.
        TextureRegion currentFrame = this.animation.getCurrentFrame();

        // Draw the current animation frame.
        spriteBatch.draw(currentFrame, this.getX(), this.getY(), this.getLengthX(), this.getLengthZ());
    }

    @Override
    public void onActivate(IPosition emitterOrigin) {
        // Set the initial particle position.
        this.setOrigin(emitterOrigin);

        // Pick a new random angle of movement.
        this.angleOfMovement = new Random().nextFloat() * 360f;

        // Pick a new random distance of movement.
        this.distanceOfMovement =  5f + (this.rng.nextFloat() * 5f);
    }

    @Override
    public float getLengthZ() {
        return 10;
    }

    @Override
    public int getCollisionLayers() {
        return EntityCollisionFlag.NOTHING;
    }

    @Override
    public int getCollisionMask() {
        return EntityCollisionFlag.NOTHING;
    }

    @Override
    public float getLengthX() {
        return 5;
    }

    @Override
    public float getLengthY() {
        return 0;
    }
}
