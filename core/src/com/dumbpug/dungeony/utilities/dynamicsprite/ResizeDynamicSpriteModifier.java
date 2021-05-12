package com.dumbpug.dungeony.utilities.dynamicsprite;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * A resize modifier that can be applied to a sprite.
 */
public class ResizeDynamicSpriteModifier implements IDynamicSpriteModifier {
    private float scale;
    private long duration;
    private long modifierStartTime = -1l;
    private float resizeValue = 0;

    /**
     * Creates a new instance of the SqueezeDynamicSpriteModifier class.
     * @param scale
     * @param duration The duration of the squeeze in millis.
     */
    public ResizeDynamicSpriteModifier(float scale, long duration) {
        this.scale    = scale;
        this.duration = duration;
    }

    @Override
    public void update(float delta) {
        // Is this the first update?
        if (modifierStartTime == -1l) {
            modifierStartTime = System.currentTimeMillis();
        }

        float currentTime  = System.currentTimeMillis() - modifierStartTime;

        if (currentTime >= duration)
            return;

        resizeValue = easeOut(currentTime, 0, scale,  duration);
    }

    @Override
    public void apply(Sprite sprite) {
        sprite.setScale(1 + resizeValue,  1 + resizeValue);
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    public static float  easeOut(float t, float b , float c, float d) {
        if ((t/=d) < (1/2.75f)) {
            return c*(7.5625f*t*t) + b;
        } else if (t < (2/2.75f)) {
            return c*(7.5625f*(t-=(1.5f/2.75f))*t + .75f) + b;
        } else if (t < (2.5/2.75)) {
            return c*(7.5625f*(t-=(2.25f/2.75f))*t + .9375f) + b;
        } else {
            return c*(7.5625f*(t-=(2.625f/2.75f))*t + .984375f) + b;
        }
    }
}