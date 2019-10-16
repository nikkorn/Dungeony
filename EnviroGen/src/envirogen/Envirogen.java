package envirogen;

import java.util.Random;
import envirogen.area.AreaBlueprints;
import envirogen.generated.Environment;

/**
 * Generator of environments consisting of x/y layouts of connecting areas.
 */
public class Envirogen {
	
	/**
	 * Generate an environemnt.
	 * @param configFilePath The path to the 'environment.config.json' file.
	 * @param seed The seed to use.
	 * @return An environemnt.
	 */
	public static Environment generate(String configFilePath, long seed) {
		// Create a Configuration instance based on 'environment.config.json'.
		Configuration configuration = Configuration.fromFile(configFilePath);
		
		// Create the RNG to use in generating the environemnt.
		Random random = new Random(seed);
		
		// Create the collection of area blueprints to use in creating an environment.
		AreaBlueprints areaBlueprints = createAreaBlueprints(configFilePath);
		
		// Keep track of the number of times we have attempted to create the environment and failed.
		int generationFailureCount = 0;
		
		// Keep trying to generate the environment until we hit the attempt limit.
		while (generationFailureCount < configuration.getGenerationRetries()) {
			// Attempt to generate an environment.
			Environment result = attemptGeneration(areaBlueprints, configuration, random);
			
			// If the generation attempt was a success (result is not null) then return it now and we are done!
			if (result != null) {
				return result;
			}

			// We failed in this attempt to generate an environment!
			generationFailureCount++;
		}
		
		// We failed to generate an environment withing the allowed number of retry attempts!
		throw new RuntimeException("reached environment generation attempt limit!");
	}
	
	/**
	 * Attempt the generation of an environment.
	 * @param areaBlueprints The area blueprints.
	 * @param configuration The generation configuration.
	 * @param random The RNG to use.
	 * @return The result of the attempt, null if failed.
	 */
	private static Environment attemptGeneration(AreaBlueprints areaBlueprints, Configuration configuration, Random random) {
		
		
		
		return null;
	}
	

	/**
	 * Creates the collection of area blueprints to use in creating an environment.
	 * @param configFilePath
	 * @return The collection of area blueprints to use in creating an environment.
	 */
	private static AreaBlueprints createAreaBlueprints(String configFilePath) {
		
		// TODO
		// 1. Get all '*.area.json' files from the 'environment.config.json' directory down.
		// 2. For each '*.area.json' file create an AreaBlueprint instance.
		
		return null;
	}
}
