package com.dumbpug.dungeonyoverworld.state.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeonyoverworld.Constants;
import com.dumbpug.dungeonyoverworld.state.State;

/**
 * The splash state of the application.
 */
public class SplashState extends State {
    /**
     * The time since the state was last entered.
     */
    private long startTime;

    @Override
    public void onEntry(State state) {
        // Reset the splash start time.
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void onExit() {}

    @Override
    public void update() {
        // Have we shown the splash long enough? If so then move to the title state.
        if ((System.currentTimeMillis() - this.startTime) >= Constants.SPLASH_DURATION) {
            this.changeState("TITLE");
        }
    }

    @Override
    public void render(SpriteBatch batch) {}

    @Override
    public String getStateName() {
        return "SPLASH";
    }
}
