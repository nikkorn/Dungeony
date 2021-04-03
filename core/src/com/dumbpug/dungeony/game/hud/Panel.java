package com.dumbpug.dungeony.game.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A panel to display withing the game HUD.
 */
public abstract class Panel {
    /**
     * The margin between the panel and the HUD.
     */
    private float margin;
    /**
     * Whether the panel is visible.
     */
    private boolean isVisible = true;

    /**
     * Creates a new instance of the Panel class.
     * @param margin The panel margin.
     */
    public Panel(float margin) {
        this.margin   = margin;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Update the panel.
     * @param delta The delta time.
     */
    public abstract void update(float delta);

    /**
     * Render the panel.
     * @param batch The sprite batch.
     * @param x
     * @param y
     */
    public abstract void render(SpriteBatch batch, float x, float y);

    /**
     * Gets the width of the panel.
     * @return The width of the panel.
     */
    public abstract float getWidth();

    /**
     * Gets the height of the panel.
     * @return The height of the panel.
     */
    public abstract float getHeight();
}
