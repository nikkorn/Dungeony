package envirogen;

import java.io.File;

/**
 * Configuration to use in generating an environment.
 */
public class Configuration {
	/**
	 * The maximum number of generation retries allowed.
	 */
	private int generationRetries;

	/**
	 * Creates a new instance of the Configuration class.
	 * @param generationRetries The maximum number of generation retries allowed.
	 */
	private Configuration(int generationRetries) {
		this.generationRetries = generationRetries;
	}
	
	/**
	 * Create a Configuration instance based on a configuration file.
	 * @param path The configuration file.
	 * @return A Configuration instance based on a configuration file.
	 */
	public static Configuration fromFile(File configurationFile) {
		// TODO Do this right.
		return new Configuration(500);
	}

	/**
	 * Gets the maximum number of generation retries allowed.
	 * @return The maximum number of generation retries allowed.
	 */
	public int getGenerationRetries() {
		return generationRetries;
	}
}
