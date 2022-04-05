package com.dumbpug.dungeony.game.character.dodge;

import com.dumbpug.dungeony.Constants;

/**
 * Enumeration of dodge speed types.
 */
public enum DodgeSpeed {
    DEFAULT,
    FAST,
    SLOW,
    HEAVY;

    /**
     * Gets the speed modifier of the dodge speed type.
     * @return The speed modifier of the dodge speed type.
     */
    public float getModifier() {
        switch(this) {
            case DEFAULT:
                return Constants.CHARACTER_DEFAULT_DODGE_SPEED_MODIFIER;
            case FAST:
                return Constants.CHARACTER_FAST_DODGE_SPEED_MODIFIER;
            case SLOW:
                return Constants.CHARACTER_SLOW_DODGE_SPEED_MODIFIER;
            case HEAVY:
                return Constants.CHARACTER_HEAVY_DODGE_SPEED_MODIFIER;
            default:
                throw new RuntimeException("unknown FacingDirection enum value: " + this);
        }
    }
}
