package com.dumbpug.dungeony.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 * The complete game overview HUD.
 */
public class Hud {
    /**
     * The panels to display within the HUD.
     */
    private ArrayList<Panel> panels = new ArrayList<Panel>();
    /**
     * The orthographic game camera.
     */
    private OrthographicCamera camera = new OrthographicCamera();
    /**
     * The sprite batch to use in rendering the HUD.
     */
    private SpriteBatch batch = new SpriteBatch();

    private static Sprite testSquare;
    static {
        testSquare = new Sprite(new Texture("images/test_square.png"));
    }

    /**
     * Adds a panel to the HUD.
     * @param panel The panel to add.
     */
    public void AddPanel(Panel panel) {
        if (!this.panels.contains(panel)) {
            this.panels.add(panel);
        }
    }

    /**
     * Update the HUD.
     */
    public void update() { }

    /**
     * Render the HUD.
     */
    public void render() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.setProjectionMatrix(this.camera.combined);

        // Start drawing the level to the sprite batch.
        batch.begin();

        // TODO Render the panels!

        testSquare.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        testSquare.draw(batch);

        batch.end();
    }
}
