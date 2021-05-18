package com.dumbpug.dungeony.game.projectile.projectiles.bullet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.engine.particles.Emitter;
import com.dumbpug.dungeony.engine.particles.Particle;

/**
 *
 */
public class BulletImpactParticleEmitter extends Emitter<SpriteBatch> {
    /**
     * Create a new instance of the Emitter class.
     * @param position The emitter position.
     */
    public BulletImpactParticleEmitter(Position position) {
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
    }

    @Override
    protected Particle<SpriteBatch> generateParticle() {
        return new BulletImpactParticle();
    }
}
