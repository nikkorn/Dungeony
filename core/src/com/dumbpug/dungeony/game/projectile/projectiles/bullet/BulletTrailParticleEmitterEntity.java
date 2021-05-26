package com.dumbpug.dungeony.game.projectile.projectiles.bullet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.engine.particles.EmitterEntity;
import com.dumbpug.dungeony.engine.particles.ParticleEntity;

public class BulletTrailParticleEmitterEntity extends EmitterEntity<SpriteBatch> {
    /**
     * The number of seconds since the last particle spawn.
     */
    private float secondsSinceLastSpawn = 0.05f;

    /**
     * Create a new instance of the Emitter class.
     * @param position The emitter position.
     */
    public BulletTrailParticleEmitterEntity(Position position) {
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
    protected ParticleEntity<SpriteBatch> generateParticle() {
        return new BulletTrailParticleEntity();
    }
}
