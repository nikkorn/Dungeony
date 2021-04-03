package com.dumbpug.dungeony.game.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.game.character.player.Player;

/**
 * A HUD specific to a single player.
 */
public class SinglePlayerPanel extends Panel {
    /**
     * The player.
     */
    private Player player;
    /**
     * The panel background.
     */
    private Sprite background;

    /**
     * Creates a new instance of the SinglePlayerPanel class.
     * @param player The player.
     */
    public SinglePlayerPanel(Player player) {
        this.player     = player;
        this.background = new Sprite(new Texture("images/hud/player/SINGLE_PLAYER_PANEL.png"));
    }

    @Override
    public void update(float delta) { }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        this.background.setSize(getWidth(), getHeight());
        this.background.draw(batch);
    }

    @Override
    public float getWidth() {
        return 108f;
    }

    @Override
    public float getHeight() {
        return 27f;
    }
}
