package com.dumbpug.dungeony.game.projectile.projectiles.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.particles.Particle;
import com.dumbpug.dungeony.engine.utilities.lotto.Lotto;
import com.dumbpug.dungeony.game.rendering.ParticleSprite;
import com.dumbpug.dungeony.game.rendering.Resources;
import java.util.Random;

/**
 * A particle left as part of a bullet trail.
 */
public class BulletImpactParticle extends Particle<SpriteBatch> {
    /**
     * The RNG to use in for this particle.
     */
    private Random rng;
    /**
     * The lotto used to pick a random particle sprite.
     */
    private static Lotto<ParticleSprite> spriteLotto;
    /**
     * The particle sprite.
     */
    private Sprite sprite;
    /**
     * The angle of movement.
     */
    private float angleOfMovement;
    /**
     * The distance of movement.
     */
    private float distanceOfMovement;

    static {
        spriteLotto = new Lotto<ParticleSprite>();
        spriteLotto.add(ParticleSprite.IMPACT_0);
        spriteLotto.add(ParticleSprite.IMPACT_1);
        spriteLotto.add(ParticleSprite.IMPACT_2);
        spriteLotto.add(ParticleSprite.IMPACT_3);
        spriteLotto.add(ParticleSprite.IMPACT_4);
        spriteLotto.add(ParticleSprite.IMPACT_5);
    }

    /**
     * Creates a new instance of the BulletTrailParticle class.
     */
    public BulletImpactParticle() {
        this.rng = new Random();

        // Pick a random impact sprite for this particle.
        this.sprite = Resources.getSprite(spriteLotto.draw());

        // Pick a random angle of movement.
        this.angleOfMovement = this.rng.nextFloat() * 360f;

        // Pick a random distance of movement.
        this.distanceOfMovement =  10f + (this.rng.nextFloat() * 10f);

        this.setLife(0.75f);
    }

    @Override
    public void onUpdate(InteractiveEnvironment environment, float delta) {
        environment.moveByAngle(this, this.angleOfMovement, this.distanceOfMovement, delta);
    }

    @Override
    public void onRender(SpriteBatch spriteBatch) {
        float lifeRatio = this.getRemainingLife() / this.getTotalLife();
        this.sprite.setPosition(this.getX(), this.getY());
        this.sprite.setSize(this.getLengthX(), this.getLengthZ());
        this.sprite.setColor(1,1,1, lifeRatio);
        this.sprite.setScale(0.5f + lifeRatio);
        this.sprite.draw(spriteBatch);
    }

    @Override
    public void onActivate(float emitterPosX, float emitterPosY) {
        // Set the initial particle position.
        this.setX(emitterPosX);
        this.setY(emitterPosY);

        // Pick a new random angle of movement.
        this.angleOfMovement = new Random().nextFloat() * 360f;

        // Pick a new random distance of movement.
        this.distanceOfMovement =  10f + (this.rng.nextFloat() * 10f);
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
