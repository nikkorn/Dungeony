package envirogen.area;

import org.json.JSONObject;
import envirogen.Configuration;

/**
 * Factory for creating AreaBlueprint instances.
 */
public class AreaBlueprintFactory {
	
	/**
	 * Create an area blueprint based on a JSON definition.
	 * @param definition The JSON definition of the area.
	 * @param configuration The application configuration.
	 * @return An area blueprint based on a JSON definition.
	 */
	public static AreaBlueprint create(JSONObject definition, Configuration configuration) {
		// Check that we have the required properties.
		if (!definition.has("name")) {
			throw new RuntimeException("expected 'name' to be defined for area");
		}
		
		// Get the area name.
		String name = definition.getString("name");
		
		// Check that we have the required 'tiles' array and that there are the correct number.
		if (!definition.has("tiles") || definition.getJSONArray("tiles").length() != configuration.getAreaTilesCount()) {
			throw new RuntimeException("expected 'tiles' for area '" + name + "' to be defined and have a length of '" + configuration.getAreaTilesCount() + "'");
		}
		
		// Get the edge of entry for the area if it is defined.
		AreaEdge entryEdge = definition.has("entry") ? 
				AreaEdge.valueOf(definition.getString("entry").toUpperCase()) : 
				AreaEdge.UNDEFINED;
		
		return new AreaBlueprint(name);
	}
}
