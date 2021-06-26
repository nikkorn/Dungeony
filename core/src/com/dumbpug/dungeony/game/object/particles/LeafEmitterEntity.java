package com.dumbpug.dungeony.game.object.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.IPosition;
import com.dumbpug.dungeony.engine.particles.EmitterEntity;
import com.dumbpug.dungeony.engine.particles.ParticleEntity;

public class LeafEmitterEntity extends EmitterEntity<SpriteBatch> {
    /**
     * Create a new instance of the LeafEmitterEntity class.
     * @param position The emitter position.
     */
    public LeafEmitterEntity(IPosition position) {
        super(position);
    }

    public void spitThemOut() {
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
        spawnParticle();
    }

    @Override
    protected void onUpdate(boolean isInitialUpdate, float delta) { }

    @Override
    protected ParticleEntity<SpriteBatch> generateParticle() {
        return new LeafParticleEntity();
    }
}
