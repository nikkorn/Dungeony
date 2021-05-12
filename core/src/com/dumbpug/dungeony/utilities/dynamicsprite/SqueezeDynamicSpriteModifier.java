package com.dumbpug.dungeony.utilities.dynamicsprite;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * A squeeze modifier that can be applied to a sprite.
 */
public class SqueezeDynamicSpriteModifier implements IDynamicSpriteModifier {
    private float amount;
    private long duration;
    private long modifierStartTime = -1l;
    private float squeezeValue = 0;

    /**
     * Creates a new instance of the SqueezeDynamicSpriteModifier class.
     * @param amount A value between 0 and 1, representing the amount to squeeze the sprite.
     * @param duration The duration of the squeeze in millis.
     */
    public SqueezeDynamicSpriteModifier(float amount, long duration) {
        this.amount = amount;
        this.duration = duration;
    }

    @Override
    public void update(float delta) {
        // Is this the first update?
        if (modifierStartTime == -1l) {
            modifierStartTime = System.currentTimeMillis();
        }

        float currentTime  = System.currentTimeMillis() - modifierStartTime;
        float halfDuration = duration / 2;

        // Are we going in or out lads?
        if (currentTime < halfDuration) {
            squeezeValue = easeIn(currentTime, 0, amount,  halfDuration);
        } else {
            squeezeValue = easeIn(currentTime - halfDuration, amount, -amount,  halfDuration);
        }

        // https://www.kirupa.com/html5/animating_with_easing_functions_in_javascript.htm
        // http://www.gizma.com/easing/#quint1
        // http://robertpenner.com/easing/
    }

    @Override
    public void apply(Sprite sprite) {
        sprite.setScale(1 - squeezeValue, 1 + squeezeValue);
    }

    @Override
    public boolean isComplete() {
        return System.currentTimeMillis() >= (modifierStartTime + duration);
    }

    public static float  easeIn(float t, float b , float c, float d) {
        return c - easeOut (d-t, 0, c, d) + b;
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

    public static float  easeInOut(float t, float b , float c, float d) {
        if (t < d/2) return easeIn (t*2, 0, c, d) * .5f + b;
        else return easeOut (t*2-d, 0, c, d) * .5f + c*.5f + b;
    }
}