package com.dumbpug.dungeony.game.object;

/**
 * Represents object health.
 */
public class GameObjectHeath {
    /**
     * The current amount of health that an object has, out of all available health slots.
     */
    private int healthPoints;
    /**
     * The base health slots that the object has.
     */
    private int healthSlots;

    /**
     * Creates a new instance of the GameObjectHeath class.
     * @param healthSlots The number of standard health slots.
     */
    public GameObjectHeath(int healthSlots) {
        this.healthSlots  = healthSlots;
        this.healthPoints = healthSlots;
    }

    /**
     * Gets the base health slots that the object has.
     * @return The base health slots that the object has.
     */
    public int getHealthSlots() {
        return healthSlots;
    }

    /**
     * Sets the base health slots that the object has.
     * @param healthSlots The base health slots that the object has.
     */
    public void setHealthSlots(int healthSlots) {
        this.healthSlots = healthSlots;
    }

    /**
     * Gets the current amount of health that a object has, out of all available health slots.
     * @return The current amount of health that a object has, out of all available health slots.
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Sets the current amount of health that a object has, out of all available health slots.
     * @param healthPoints The current amount of health that a object has, out of all available health slots.
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;

        // Prevent health points from going below 0.
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
    }

    /**
     * Reduces the current amount of health that a object has, out of all available health slots.
     * @param points The amount of points to reduce the total amount of health points by.
     */
    public void reduceHealthPoints(int points) {
        setHealthPoints(getHealthPoints() - points);
    }

    /**
     * Gets whether the health is depleted.
     * @return Whether the health is depleted.
     */
    public boolean isHealthDepleted() {
        return healthPoints == 0;
    }
}
