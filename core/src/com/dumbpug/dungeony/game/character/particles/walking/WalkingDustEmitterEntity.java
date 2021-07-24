package com.dumbpug.dungeony.game.character.particles.walking;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.engine.particles.EmitterEntity;
import com.dumbpug.dungeony.engine.particles.ParticleEntity;
import com.dumbpug.dungeony.game.character.FacingDirection;

/**
 *
 */
public class WalkingDustEmitterEntity extends EmitterEntity<SpriteBatch> {
    /**
     * The facing direction of the entity that spawned the particle.
     */
    private FacingDirection direction;
    /**
     * The number of seconds since the last particle spawn.
     */
    private float secondsSinceLastSpawn = 1f;

    /**
     * Create a new instance of the WalkingDustEmitter class.
     * @param position The emitter position.
     * @param direction The facing direction of the entity that spawned the particle.
     */
    public WalkingDustEmitterEntity(Position position, FacingDirection direction) {
        super(position);
        this.disable();
        this.direction = direction;
    }

    @Override
    public void onUpdate(boolean isInitialUpdate, float delta) {
        if (secondsSinceLastSpawn >= 1f) {
            spawnParticle();
            secondsSinceLastSpawn = 0;
        } else {
            secondsSinceLastSpawn += delta;
        }
    }

    @Override
    public ParticleEntity<SpriteBatch> generateParticle() {
        return new WalkingDustParticleEntity(this.direction);
    }
}
