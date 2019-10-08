package com.dumbpug.dungeonyoverworld;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeonyoverworld.state.StateManager;
import com.dumbpug.dungeonyoverworld.state.states.GameState;
import com.dumbpug.dungeonyoverworld.state.states.SettingsState;
import com.dumbpug.dungeonyoverworld.state.states.SplashState;
import com.dumbpug.dungeonyoverworld.state.states.TitleState;

public class DungeonyOverworld extends ApplicationAdapter {
	/**
	 * The state manager.
	 */
	private StateManager stateManager;
	/**
	 * The sprite batch to use throughout the application.
	 */
	private SpriteBatch batch;
	
	@Override
	public void create () {
		// Create the state manager and add the application states.
		stateManager = new StateManager();
		stateManager.addState(new SplashState());
		stateManager.addState(new TitleState());
		stateManager.addState(new SettingsState());
		stateManager.addState(new GameState());

		// Set the initial application state.
		stateManager.setCurrentState("SPLASH");

		// Create the application sprite batch.
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		// Update the current application state.
		this.stateManager.update();

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0.219f, 0.219f, 0.239f, 1);

		// Render the current application state.
		batch.begin();
		this.stateManager.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
