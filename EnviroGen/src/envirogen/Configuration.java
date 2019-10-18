package envirogen;

import java.io.File;
import org.json.JSONObject;

/**
 * Configuration to use in generating an environment.
 */
public class Configuration {
	/**
	 * The version.
	 */
	private String version;
	/**
	 * The initial area name.
	 */
	private String initialAreaName;
	/**
	 * The number of tiles high each area is.
	 */
	private int areaTilesHigh;
	/**
	 * The number of tiles wide each area is.
	 */
	private int areaTilesWide;
	/**
	 * The minimum number of areas to generate.
	 */
	private int areaMinCount;
	/**
	 * The maximum number of areas to generate.
	 */
	private int areaMaxCount;
	/**
	 * The maximum number of generation retries allowed.
	 */
	private int generationRetries;

	/**
	 * Creates a new instance of the Configuration class.
	 * @param version The version.
	 * @param initialAreaName The initial area name.
	 * @param areaTilesHigh The number of tiles high each area is.
	 * @param areaTilesWide The number of tiles wide each area is.
	 * @param areaMinCount The minimum number of areas to generate.
	 * @param areaMaxCount The maximum number of areas to generate.
	 * @param generationRetries The maximum number of generation retries allowed.
	 */
	private Configuration(
			String version, 
			String initialAreaName, 
			int areaTilesHigh,
			int areaTilesWide,
			int areaMinCount,
			int areaMaxCount,
			int generationRetries
	) {
		this.version           = version;
		this.initialAreaName   = initialAreaName;
		this.areaTilesHigh     = areaTilesHigh;
		this.areaTilesWide     = areaTilesWide;
		this.areaMinCount      = areaMinCount;
		this.areaMaxCount      = areaMaxCount;
		this.generationRetries = generationRetries;
	}
	
	/**
	 * Create a Configuration instance based on a configuration file.
	 * @param path The configuration file.
	 * @return A Configuration instance based on a configuration file.
	 */
	public static Configuration fromFile(File configurationFile) {
		// Ready the configuration file config from disk into a JSON object.
		JSONObject configJsonObject = new JSONObject(Utility.getFileContents(configurationFile));
		
		if (!configJsonObject.has("version")) {
			throw new RuntimeException("expected 'version' to be defined");
		}
		
		if (!configJsonObject.has("initial_area_name")) {
			throw new RuntimeException("expected 'initial_area_name' to be defined");
		}
		
		if (!configJsonObject.has("area_tiles_wide")) {
			throw new RuntimeException("expected 'area_tiles_wide' to be defined");
		}
		
		if (!configJsonObject.has("area_tiles_high")) {
			throw new RuntimeException("expected 'area_tiles_high' to be defined");
		}
		
		if (!configJsonObject.has("area_min_count")) {
			throw new RuntimeException("expected 'area_min_count' to be defined");
		}
		
		if (!configJsonObject.has("area_max_count")) {
			throw new RuntimeException("expected 'area_max_count' to be defined");
		}
		
		if (!configJsonObject.has("generation_retries")) {
			throw new RuntimeException("expected 'generation_retries' to be defined");
		}
		
		return new Configuration(
			configJsonObject.getString("version"),
			configJsonObject.getString("initial_area_name"),
			configJsonObject.getInt("area_tiles_wide"),
			configJsonObject.getInt("area_tiles_high"),
			configJsonObject.getInt("area_min_count"),
			configJsonObject.getInt("area_max_count"),
			configJsonObject.getInt("generation_retries")
		);
	}
	
	public String getVersion() {
		return version;
	}

	public String getInitialAreaName() {
		return initialAreaName;
	}

	public int getAreaTilesHigh() {
		return areaTilesHigh;
	}

	public int getAreaTilesWide() {
		return areaTilesWide;
	}
	
	public int getAreaTilesCount() {
		return areaTilesWide * areaTilesHigh;
	}

	public int getAreaMinCount() {
		return areaMinCount;
	}

	public int getAreaMaxCount() {
		return areaMaxCount;
	}

	/**
	 * Gets the maximum number of generation retries allowed.
	 * @return The maximum number of generation retries allowed.
	 */
	public int getGenerationRetries() {
		return generationRetries;
	}
}
