package com.dumbpug.dungeonyoverworld.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dumbpug.dungeonyoverworld.DungeonyOverworld;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// Set a mobile-y resolution.
		config.width  = 480;
		config.height = 800;
		new LwjglApplication(new DungeonyOverworld(), config);
	}
}
