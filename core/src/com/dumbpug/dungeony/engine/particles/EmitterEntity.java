package com.dumbpug.dungeony.engine.particles;

import com.dumbpug.dungeony.engine.Entity;
import com.dumbpug.dungeony.engine.IPosition;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.game.EntityCollisionFlag;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A particle emitter entity.
 * @param <TRenderContext> The render context.
 */
public abstract class EmitterEntity<TRenderContext> extends Entity<TRenderContext> {
    /**
     * A reference to the environment that the emitter is currently in.
     */
    private InteractiveEnvironment environment;
    /**
     * The list of all emitter particles.
     */
    private ArrayList<ParticleEntity<TRenderContext>> particleEntities = new ArrayList<ParticleEntity<TRenderContext>>();
    /**
     * The list of inactive emitter particles.
     */
    private ArrayList<ParticleEntity<TRenderContext>> inactiveParticleEntities = new ArrayList<ParticleEntity<TRenderContext>>();
    /**
     * Whether the emitter has had its initial update.
     */
    private boolean hasHadInitialUpdate = false;
    /**
     * Whether the emitter is enabled.
     */
    private boolean isEnabled = true;
    /**
     * The maximum number of particles permitted.
     */
    private int maxParticleCount = 100;
    /**
     * Whether to destroy inactive particles or reuse them.
     */
    private boolean destroyInactiveParticles = true;

    /**
     * Create a new instance of the Emitter class.
     * @param position The emitter position.
     */
    public EmitterEntity(IPosition position) {
        super(position);
    }

    /**
     * Sets the maximum number of active particles permitted.
     * @param maxParticleCount The maximum number of active particles permitted.
     */
    public void setMaxParticleCount(int maxParticleCount) {
        this.maxParticleCount = maxParticleCount;
    }

    /**
     * Sets whether to destroy inactive particles or reuse them.
     * @param destroyInactiveParticles Whether to destroy inactive particles or reuse them.
     */
    public void setDestroyInactiveParticles(boolean destroyInactiveParticles) {
        this.destroyInactiveParticles = destroyInactiveParticles;
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
    public void onEnvironmentEntry(InteractiveEnvironment environment) {
        // Keep a reference to the environment that the emitter is placed in.
        this.environment = environment;
    }

    @Override
    public void onEnvironmentExit(InteractiveEnvironment environment) {
        // Remove the reference to the environment that the emitter was placed in.
        this.environment = null;
    }

    @Override
    public void update(InteractiveEnvironment environment, float delta) {
        // If the emitter is active then invoke the custom emitter update.
        if (this.isEnabled) {
            onUpdate(!this.hasHadInitialUpdate, delta);

            this.hasHadInitialUpdate = true;
        }

        Iterator<ParticleEntity<TRenderContext>> iterator = this.particleEntities.iterator();

        // Iterate over and remove any inactive particles from the environment and this collection if we are not reusing inactive particles.
        while (iterator.hasNext()) {
            ParticleEntity particleEntity = iterator.next();

            if (particleEntity.getState() == ParticleState.ACTIVE) {
                continue;
            }

            if (this.destroyInactiveParticles) {
                environment.removeEntity(particleEntity);
                iterator.remove();
            } else {
                this.inactiveParticleEntities.add(particleEntity);
            }
        }
    }

    @Override
    public final void render(TRenderContext renderContext) {
        // We never need to render the emitter, we just care about the particles which will be drawn as part of the environment render.
    }

    @Override
    public float getLengthZ() {
        return 0;
    }

    @Override
    public float getLengthX() {
        return 0;
    }

    @Override
    public float getLengthY() {
        return 0;
    }

    /**
     * Enable the emitter.
     */
    public void enable() {
        if (!this.isEnabled) {
            this.isEnabled           = true;
            this.hasHadInitialUpdate = false;
        }
    }

    /**
     * Disable the emitter.
     */
    public void disable() {
        if (this.isEnabled) {
            this.isEnabled = false;
        }
    }

    /**
     * Attempts to spawn a particle and add it to the game environment.
     * Inactive particles will be reused if they exist.
     */
    protected void spawnParticle() {
        if (this.environment == null) {
            throw new RuntimeException("cannot add emitter particle when emitter is not part of an environment.");
        }

        // We should not be spawning any new particles if we already have the max number of active particles allowed.
        if ((this.particleEntities.size() - this.inactiveParticleEntities.size()) >= this.maxParticleCount) {
            return;
        }

        // Check whether we have any inactive particles we can reuse rather than having to create a new one.
        if (!this.inactiveParticleEntities.isEmpty()) {
            // Grab an existing inactive particle.
            ParticleEntity nextInactiveParticleEntity = this.inactiveParticleEntities.get(0);

            // Remove the particle from the set of inactive particles
            this.inactiveParticleEntities.remove(nextInactiveParticleEntity);

            nextInactiveParticleEntity.setState(ParticleState.ACTIVE);

            // Set the life of the particle to be its original total life.
            nextInactiveParticleEntity.setLife(nextInactiveParticleEntity.getTotalLife());

            nextInactiveParticleEntity.onActivate(this.getOrigin());
        } else {
            // Generate a brand new particle.
            ParticleEntity particleEntity = generateParticle();

            // Add it to our collection of particles.
            particleEntities.add(particleEntity);

            // Add the particle to the environment.
            this.environment.addEntity(particleEntity);

            particleEntity.onActivate(this.getOrigin());
        }
    }

    protected abstract void onUpdate(boolean isInitialUpdate, float delta);

    protected abstract ParticleEntity<TRenderContext> generateParticle();
}
