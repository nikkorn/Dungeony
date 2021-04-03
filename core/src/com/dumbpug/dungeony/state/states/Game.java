package com.dumbpug.dungeony.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dumbpug.dungeony.ApplicationModel;
import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.game.hud.Hud;
import com.dumbpug.dungeony.game.hud.PanelPosition;
import com.dumbpug.dungeony.game.hud.SinglePlayerPanel;
import com.dumbpug.dungeony.game.level.Level;
import com.dumbpug.dungeony.game.level.LevelEnvironmentCamera;
import com.dumbpug.dungeony.game.level.LevelFactory;
import com.dumbpug.dungeony.state.State;
import com.dumbpug.levelgeneration.SimpleLevelGenerator;

/**
 * The 'game' state of the application.
 */
public class Game extends State {
    /**
     * The application model used to share data across application state.
     */
    private ApplicationModel applicationModel;
    /**
     * The current level.
     */
    private Level level;
    /**
     * The game HUD to render over the level.
     */
    private Hud hud;
    /**
     * The sprite batch to use in rendering the game state.
     */
    private SpriteBatch batch = new SpriteBatch();
    /**
     * The viewports to use in rendering the game state.
     */
    private Viewport levelViewport;

    /**
     * Creates a new instance of the Game class.
     * @param applicationModel The application model used to share data across application state.
     */
    public Game(ApplicationModel applicationModel) {
        this.applicationModel = applicationModel;

        // Create the viewport to use in rendering the level and the HUD.
        this.levelViewport = new ExtendViewport(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    /**
     * Called on moving to this state.
     * @param state The previously active state.
     */
    @Override
    public void onEntry(State state) {
        // Moving to this state means that we are starting a new game from the initial level so ...

        // ... create a level camera that wraps our game camera and ...
        LevelEnvironmentCamera levelCamera = new LevelEnvironmentCamera(this.levelViewport, Constants.LEVEL_DEFAULT_ZOOM);

        // ... create the initial level ...
        this.level = LevelFactory.createInitialLevel(levelCamera, new SimpleLevelGenerator(), this.applicationModel.getPlayerDetails());

        // ... and let the level take care of some 'onBegin' logic.
        this.level.onBegin();

        // Create the game HUD.
        this.hud = createHud(this.level);
    }

    @Override
    public void onExit() {
        // Let the level take care of some 'onEnd' logic.
        this.level.onEnd();
    }

    @Override
    public void update() {
        // Update the level.
        this.level.update();

        // Update the HUD.
        this.hud.update();

        // TODO Check for whether there is a current level boss and update the boss panel in the hud.
    }

    @Override
    public void render() {
        // Render the collection of level renderables.
        this.level.render(batch);

        // TODO Render any game dialogs.

        // Render the HUD.
        this.hud.render();
    }

    @Override
    public void onResize(int width, int height) {
        this.levelViewport.update(width, height);

        if (this.hud != null)
            this.hud.setSize(Gdx.graphics.getWidth() * ((float)Constants.HUD_VIEWPORT_HEIGHT / Gdx.graphics.getHeight()), Constants.HUD_VIEWPORT_HEIGHT);
    }

    @Override
    public String getStateName() {
        return "GAME";
    }

    /**
     * Creates a game HUD for the given level.
     * @param level The level.
     * @return A game HUD for the given level.
     */
    private static Hud createHud(Level level) {
        Hud hud = new Hud(Gdx.graphics.getWidth() * ((float)Constants.HUD_VIEWPORT_HEIGHT / Gdx.graphics.getHeight()), Constants.HUD_VIEWPORT_HEIGHT);

        switch (level.getPlayers().size()) {
            case 1:
                hud.addPanel(PanelPosition.TOP_LEFT, new SinglePlayerPanel(level.getPlayers().get(0), Constants.HUD_PANEL_MARGIN));
                break;

            case 2:
                // hud.addPanel(PanelPosition.TOP_LEFT, new CoopPlayerPanel(new SinglePlayerPanel(level.getPlayers().get(0), 1));
                // hud.addPanel(PanelPosition.TOP_RIGHT, new CoopPlayerPanel(new SinglePlayerPanel(level.getPlayers().get(1), 2));
                break;

            default:
                throw new RuntimeException("cannot create game hud for player count of " + level.getPlayers().size());
        }

        return hud;
    }
}
