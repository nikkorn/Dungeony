package com.dumbpug.dungeony.state.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dumbpug.dungeony.ApplicationModel;
import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.game.hud.Hud;
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
        this.hud = new Hud();

        // TODO Add panels for each player in the game to the HUD.
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
    }

    @Override
    public String getStateName() {
        return "GAME";
    }
}
