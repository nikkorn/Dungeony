package com.dumbpug.dungeonyoverworld.state.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeonyoverworld.state.State;

/**
 * The title state of the application.
 */
public class TitleState extends State {

    @Override
    public void onEntry(State state) {}

    @Override
    public void onExit() {}

    @Override
    public void update() {
        // Go straight to the game state for now.
        this.changeState("GAME");
    }

    @Override
    public void render(SpriteBatch batch) {}

    @Override
    public String getStateName() {
        return "TITLE";
    }
}
