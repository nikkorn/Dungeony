package com.dumbpug.dungeony.engine;

/**
 * The configuration required to create an Environment instance.
 */
public class EnvironmentConfiguration {
    /**
     * The cell size to use for the spatial grid used to process entity collisions.
     */
    public float gridCellSize = 64f;

    /**
     * Whether lighting is enabled for the environment.
     */
    public boolean isLightingEnabled = true;

    /**
     * The light level for the environment if lighting is enabled.
     * A value between 0 (dark) and 1 (light).
     */
    public float lightLevel = 0.4f;
}
