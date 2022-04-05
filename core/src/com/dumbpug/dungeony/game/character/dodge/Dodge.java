package com.dumbpug.dungeony.game.character.dodge;

/**
 * Represents a single character dodge action.
 */
public class Dodge {
    /**
     * The dodge speed.
     */
    private DodgeSpeed speed;
    /**
     * The dodge angle.
     */
    private float angle;
    /**
     * The dodge duration.
     */
    private long duration;
    /**
     * The time at which the dodge was started in millis.
     */
    private long startTime;

    /**
     * Creates a new instance of the Dodge class.
     * @param speed The dodge speed.
     * @param angle The angle of the dodge.
     * @param duration The duration of the dodge.
     */
    public Dodge(DodgeSpeed speed, float angle, long duration) {
        this.speed     = speed;
        this.angle     = angle;
        this.duration  = duration;
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Gets the speed of the dodge.
     * @return The speed of the dodge.
     */
    public DodgeSpeed getSpeed() {
        return this.speed;
    }

    /**
     * Gets the angle of the dodge.
     * @return The angle of the dodge.
     */
    public float getAngle() {
        return this.angle;
    }

    /**
     * Gets the duration of the dodge.
     * @return The duration of the dodge.
     */
    public long getDuration() {
        return this.duration;
    }

    /**
     * Gets whether the dodge is finished.
     * @return Whether the dodge is finished.
     */
    public boolean isFinished() {
        return System.currentTimeMillis() >= this.startTime + this.duration;
    }
}
