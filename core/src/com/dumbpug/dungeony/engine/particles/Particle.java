package com.dumbpug.dungeony.engine.particles;

import com.dumbpug.dungeony.engine.Entity;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.Position;

/**
 * A particle entity.
 * @param <TRenderContext> The render context.
 */
public abstract class Particle<TRenderContext> extends Entity<TRenderContext> {
    /**
     * The particle state.
     */
    private ParticleState state = ParticleState.ACTIVE;
    /**
     * The total life of this particle in seconds, defaults to a second.
     */
    private float totalLife = 1f;
    /**
     * The remaining life of this particle in seconds, defaults to a second.
     */
    private float remainingLife = 1f;

    /**
     * Creates a new instance of the Particle class.
     */
    public Particle() {
        super(new Position());
    }

    /**
     * Sets the particle state.
     * @return The particle state.
     */
    public ParticleState getState() {
        return state;
    }

    /**
     * Sets the particle state.
     * @param state The particle state.
     */
    public void setState(ParticleState state) {
        this.state = state;
    }

    /**
     * Get the total life of this particle in seconds.
     * @return The total life of this particle in seconds.
     */
    public float getTotalLife() { return this.totalLife; }

    /**
     * Get the remaining life of this particle in seconds.
     * @return The remaining life of this particle in seconds.
     */
    public float getRemainingLife() { return this.remainingLife; }

    /**
     * Set the life of this particle in seconds.
     * @param life The life of this particle in seconds.
     */
    public void setLife(float life) {
        this.totalLife     = life;
        this.remainingLife = life;
    }

    @Override
    public void onEnvironmentEntry(InteractiveEnvironment environment) { }

    @Override
    public void onEnvironmentExit(InteractiveEnvironment environment) { }

    @Override
    public void update(InteractiveEnvironment environment, float delta) {
        // No update should take place for inactive particles.
        if (this.state == ParticleState.INACTIVE) {
            return;
        }

        this.onUpdate(environment, delta);

        // Reduce the remaining life of the particle by the game delta.
        this.remainingLife -= delta;

        // Has the life of the particle expired?
        if (this.remainingLife <= 0) {
            this.remainingLife = 0;
            this.state         = ParticleState.INACTIVE;
        }
    }

    @Override
    public void render(TRenderContext context) {
        // No render should take place for inactive particles.
        if (this.state == ParticleState.INACTIVE) {
            return;
        }

        this.onRender(context);
    }

    /**
     * Called on the particle entity update if the particle is active.
     * @param environment The game environment.
     * @param delta The game delta.
     */
    public abstract void onUpdate(InteractiveEnvironment environment, float delta);

    /**
     * Called on the particle entity render if the particle is active.
     * @param context The render context.
     */
    public abstract void onRender(TRenderContext context);

    /**
     * Called when the particle is first made active or reset due to is being reused by an emitter after it has become inactive.
     * @param emitterPosX The x position of the emitter.
     * @param emitterPosX The y position of the emitter.
     */
    public abstract void onActivate(float emitterPosX, float emitterPosY);
}
