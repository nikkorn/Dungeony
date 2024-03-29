package com.dumbpug.dungeony.engine;

import com.dumbpug.dungeony.engine.utilities.GameMath;

/**
 * An in-game position.
 */
public class Position implements IPosition {
    /**
     * The x/y values of the position.
     */
    private float x, y;

    /**
     * Creates a new instance of the Position class with the specified x/y values.
     * @param x The initial x position.
     * @param y The initial y position.
     */
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new instance of the Position class with 0/0 as the initial x/y values.
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Creates a new instance of the Position class with values matching the specified position.
     * @param position The position to copy.
     */
    public Position(IPosition position) {
        this(position.getX(), position.getY());
    }

    /**
     * Gets the x position.
     * @return The x position.
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x position.
     * @param x The x position.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Gets the y position.
     * @return The y position.
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y position.
     * @param y The y position.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Sets the x and y position.
     * @param x The x position.
     * @param y The y position.
     */
    public void set(float x, float y) {
        setX(x);
        setY(y);
    }

    /**
     * Get the angle from this position to the target position.
     * @param target The target position.
     * @return The angle from this position to the target position.
     */
    public float getAngleTo(IPosition target) {
        return GameMath.getAngle(this.getX(), this.getY(), target.getX(), target.getY());
    }

    /**
     * Get the distance from this position to the target position.
     * @param target The target position.
     * @return The distance from this position to the target position.
     */
    public float getDistanceTo(IPosition target) {
        return GameMath.getLength(this.getX(), this.getY(), target.getX(), target.getY());
    }

    /**
     * Gets a new position based on this position and a given x/y offset.
     * @param x The x offset.
     * @param y The y offset.
     * @return A new position based on this position and a given x/y offset.
     */
    public IPosition getOffset(float x, float y) {
        return new Position(this.getX() + x, this.getY() + y);
    }
}
