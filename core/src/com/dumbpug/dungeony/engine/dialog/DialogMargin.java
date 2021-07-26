package com.dumbpug.dungeony.engine.dialog;

/**
 * Enumeration of dialog margin types.
 */
public enum DialogMargin {
    NONE,
    SMALL,
    MEDIUM,
    LARGE;

    /**
     * Gets the dialog margin size.
     * @return The dialog margin size.
     */
    public float getSize() {
        switch(this) {
            case NONE:
                return 0f;
            case SMALL:
                return 2f;
            case MEDIUM:
                return 4f;
            case LARGE:
                return 6f;
            default:
                throw new RuntimeException("unknown DialogMargin enum value: " + this);
        }
    }
}
