package com.dumbpug.dungeony.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;

/**
 * The complete game overview HUD.
 */
public class Hud {
    /**
     * The panels to display within the HUD.
     */
    private HashMap<PanelPosition, Panel> panels = new HashMap<PanelPosition, Panel>();
    /**
     * The orthographic game camera.
     */
    private OrthographicCamera camera = new OrthographicCamera();
    /**
     * The sprite batch to use in rendering the HUD.
     */
    private SpriteBatch batch = new SpriteBatch();
    /**
     * The width/height of the HUD.
     */
    private float width, height;


    private static Sprite testSquare;
    static {
        testSquare = new Sprite(new Texture("images/test_square.png"));
    }

    /**
     * Creates a new instance of the Hud class.
     * @param width The width of the HUD.
     * @param height The height of the HUD.
     */
    public Hud(float width, float height) {
        this.width  = width;
        this.height = height;
    }

    /**
     * Set the size of the HUD.
     * @param width The width.
     * @param height The height.
     */
    public void setSize(float width, float height) {
        this.width  = width;
        this.height = height;
    }

    /**
     * Adds a panel to the HUD.
     * @param position The position of the panel.
     * @param panel The panel to add.
     */
    public void addPanel(PanelPosition position, Panel panel) {
        this.panels.put(position, panel);
    }

    /**
     * Update the HUD.
     */
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        for (Panel panel : this.panels.values()) {
            panel.update(delta);
        }
    }

    /**
     * Render the HUD.
     */
    public void render() {
        camera.setToOrtho(false, this.width, this.height);

        batch.setProjectionMatrix(this.camera.combined);

        // Start drawing the level to the sprite batch.
        batch.begin();

        testSquare.setSize(this.width, this.height);
        testSquare.draw(batch);

        // Render the panels!
        for (Panel panel : this.panels.values()) {
            // TODO: Move the camera position to match the panel position/size/margin/hud zoom.
            panel.render(batch, 0, 0);
        }

        batch.end();
    }
}
