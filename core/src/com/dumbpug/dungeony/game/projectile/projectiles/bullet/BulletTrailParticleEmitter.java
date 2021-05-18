package com.dumbpug.dungeony.game.projectile.projectiles.bullet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.engine.particles.Emitter;
import com.dumbpug.dungeony.engine.particles.Particle;

public class BulletTrailParticleEmitter extends Emitter<SpriteBatch> {
    /**
     * The number of seconds since the last particle spawn.
     */
    private float secondsSinceLastSpawn = 0.05f;

    /**
     * Create a new instance of the Emitter class.
     * @param position The emitter position.
     */
    public BulletTrailParticleEmitter(Position position) {
        super(position);
    }

    @Override
    protected void onUpdate(boolean isInitialUpdate, float delta) {
        if (secondsSinceLastSpawn >= 0.05f) {
            spawnParticle();
            secondsSinceLastSpawn = 0;
        } else {
            secondsSinceLastSpawn += delta;
        }
    }

    @Override
    protected Particle<SpriteBatch> generateParticle() {
        return new BulletTrailParticle();
    }
}
