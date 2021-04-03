package com.dumbpug.dungeony.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.engine.Position;
import java.util.HashMap;
import java.util.Map;

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
     * The margin to use for panels.
     */
    private float margin;
    /**
     * The width/height of the HUD.
     */
    private float width, height;

    /**
     * Creates a new instance of the Hud class.
     * @param width The width of the HUD.
     * @param height The height of the HUD.
     * @paran margin The margin to use for panels.
     */
    public Hud(float width, float height, float margin) {
        this.width  = width;
        this.height = height;
        this.margin = margin;
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

        for (Map.Entry<PanelPosition, Panel> entry : this.panels.entrySet()) {
            PanelPosition position = entry.getKey();
            Panel panel = entry.getValue();

            Position cameraPosition = getCameraPanelPosition(panel, position);

            camera.position.set(cameraPosition.getX(), cameraPosition.getY(),0);
            camera.update();
            batch.setProjectionMatrix(this.camera.combined);

            panel.render(batch, 0, 0);
        }

        batch.end();
    }

    /**
     *
     * @param panel
     * @param position
     * @return
     */
    public Position getCameraPanelPosition(Panel panel, PanelPosition position) {
        float offsetX = 0;
        float offsetY = 0;

        switch (position) {
            case TOP_LEFT:
                offsetX = (this.width / 2) - Constants.HUD_PANEL_MARGIN;
                offsetY = (-((this.height / 2) - panel.getHeight())) + Constants.HUD_PANEL_MARGIN;
                break;
            case TOP:
                break;
            case TOP_RIGHT:
                break;
            case LEFT:
                break;
            case CENTRE:
                break;
            case RIGHT:
                break;
            case BOTTOM_LEFT:
                break;
            case BOTTOM:
                break;
            case BOTTOM_RIGHT:
                break;
            default:
                throw new RuntimeException("unknown panel position " + position);
        }

        return new Position(offsetX, offsetY);
    }
}
