package com.dumbpug.dungeony.game.character.particles.walking;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.engine.particles.Emitter;
import com.dumbpug.dungeony.engine.particles.Particle;
import com.dumbpug.dungeony.game.character.FacingDirection;

/**
 *
 */
public class WalkingDustEmitter extends Emitter<SpriteBatch> {
    /**
     * The facing direction of the entity that spawned the particle.
     */
    private FacingDirection direction;
    /**
     * The number of seconds since the last particle spawn.
     */
    private float secondsSinceLastSpawn = 0.02f;

    /**
     * Create a new instance of the WalkingDustEmitter class.
     * @param position The emitter position.
     * @param direction The facing direction of the entity that spawned the particle.
     */
    public WalkingDustEmitter(Position position, FacingDirection direction) {
        super(position);
        this.direction = direction;
    }

    @Override
    public void onUpdate(boolean isInitialUpdate, float delta) {
        if (secondsSinceLastSpawn >= 0.02f) {
            spawnParticle();
            secondsSinceLastSpawn = 0;
        } else {
            secondsSinceLastSpawn += delta;
        }
    }

    @Override
    public Particle<SpriteBatch> generateParticle() {
        return new WalkingDustParticle(this.direction);
    }
}
