package envirogen.area;

import org.json.JSONObject;

/**
 * Factory for creating AreaBlueprint instances.
 */
public class AreaBlueprintFactory {
	
	/**
	 * Create an area blueprint based on a JSON definition.
	 * @param definition The JSON definition of the area.
	 * @return An area blueprint based on a JSON definition.
	 */
	public static AreaBlueprint create(JSONObject definition) {
		// TODO
		return new AreaBlueprint("SOME COOL AREA NAME");
	}
}
