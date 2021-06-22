package com.dumbpug.dungeony.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * An animation.
 */
public class Animation {
    /**
     * The animation texture.
     */
    private AnimationDetails details;
    /**
     * The underlying LIBGDX animation.
     */
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> animation;
    /**
     * The animation state time.
     */
    private float stateTime = 0f;

    /**
     * Creates a new instance of the Animation class.
     * @param details The animation details.
     */
    public Animation(AnimationDetails details) {
        this.details   = details;
        this.animation = new com.badlogic.gdx.graphics.g2d.Animation(details.getStep(), details.getFrames());
    }

    /**
     * Gets the current animation frame.
     * @return The current animation frame.
     */
    public TextureRegion getCurrentFrame() {
        stateTime += Gdx.graphics.getDeltaTime();
        return animation.getKeyFrame(stateTime, details.isLoopingAnimation());
    }

    /**
     * Gets whether the animation is finished if not looping.
     * @return Whether the animation is finished if not looping.
     */
    public boolean isFinished(){
        return animation.isAnimationFinished(stateTime);
    }
}