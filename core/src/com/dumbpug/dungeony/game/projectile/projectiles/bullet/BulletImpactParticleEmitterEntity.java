package com.dumbpug.dungeony.game.projectile.projectiles.bullet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.engine.particles.EmitterEntity;
import com.dumbpug.dungeony.engine.particles.ParticleEntity;

/**
 *
 */
public class BulletImpactParticleEmitterEntity extends EmitterEntity<SpriteBatch> {
    /**
     * Create a new instance of the Emitter class.
     * @param position The emitter position.
     */
    public BulletImpactParticleEmitterEntity(Position position) {
        super(position);
    }

    @Override
    protected void onUpdate(boolean isInitialUpdate, float delta) {
        if (isInitialUpdate) {
            spawnParticle();
            spawnParticle();
            spawnParticle();
            spawnParticle();
            spawnParticle();
            spawnParticle();
        }

        // TODO This needs to clear itself up after all of the particles are dead!!!

        // TODO Just have a basic impact animation on top of this that aims away from the thing we are impacting?
    }

    @Override
    protected ParticleEntity<SpriteBatch> generateParticle() {
        return new BulletImpactParticleEntity();
    }
}
