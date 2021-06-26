package com.dumbpug.dungeony.engine;

/**
 * A read-only in-game position.
 */
public interface IPosition {
    /**
     * Gets the x position.
     * @return The x position.
     */
    float getX();

    /**
     * Gets the y position.
     * @return The y position.
     */
    float getY();

    /**
     * Get the angle from this position to the target position.
     * @param target The target position.
     * @return The angle from this position to the target position.
     */
    float getAngleTo(IPosition target);

    /**
     * Get the distance from this position to the target position.
     * @param target The target position.
     * @return The distance from this position to the target position.
     */
    float getDistanceTo(IPosition target);
}
