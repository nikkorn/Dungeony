package com.dumbpug.dungeony.game.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A panel to display withing the game HUD.
 */
public abstract class Panel {
    /**
     * The panel position.
     */
    private PanelPosition position;
    /**
     * Whether the panel is visible.
     */
    private boolean isVisible = true;

    /**
     * Creates a new instance of the Panel class.
     * @param position The panel position.
     */
    public Panel(PanelPosition position) {
        this.position = position;
    }

    public PanelPosition getPosition() {
        return position;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

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
