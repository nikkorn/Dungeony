package com.dumbpug.dungeony.server;

import java.io.File;
import org.json.JSONObject;

import com.dumbpug.dungeony.utilities.FileUtility;

/**
 * Represents the server configuration defined by server.config.json
 */
public class Configuration {
	/**
	 * The server port.
	 */
	private int port;
	/**
	 * Whether to write debug output to the console.
	 */
	private boolean debugToConsole;
	
	/**
	 * Create a new instance of the Configuration class.
	 * @param port The server port.
	 * @param debugToConsole Whether to write debug output to the console.
	 */
	private Configuration(int port, boolean debugToConsole) {
		this.port           = port;
		this.debugToConsole = debugToConsole;
	}

	/**
	 * Get the server port.
	 * @return The server port.
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Get whether to write debug output to the console.
	 * @return Whether to write debug output to the console.
	 */
	public boolean isDebuggingToConsole() {
		return this.debugToConsole;
	}
	
	/**
	 * Get server settings defined in the server configuration file.
	 * @return The server configuration.
	 */
	public static Configuration loadFromDisk() {
		// Grab the 'server.config.json' file.
		File serverConfigFile = new File("server.config.json");
		
		// Check that the configuration file even exists.
		if (!serverConfigFile.exists()) {
			throw new RuntimeException("Missing 'server.config.json' file!");
		}
		
		// Convert the raw configuration JSON into a JSONObject instance.
		JSONObject rawConfigJSON = FileUtility.readJSONObjectFromFile(serverConfigFile);
		
		// Parse the configuration options from the JSON.
		int port               = rawConfigJSON.getInt("port");
		boolean debugToConsole = rawConfigJSON.getBoolean("debug_to_console");
		
		// Print the configuration to the console.
		System.out.println("loading server config from 'server.config.json'...");
		System.out.println("port:              " + port);
		
		// Return the configuration instance.
		return new Configuration(port, debugToConsole);
	}
}