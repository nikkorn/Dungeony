package envirogen.area;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A collection of unique area blueprints.
 */
public class AreaBlueprints {
	/**
	 * The list of area blueprints.
	 */
	private ArrayList<AreaBlueprint> blueprints;
	
	/**
	 * Creates a new instance of the AreaBlueprints class.
	 * @param blueprints The list of unique area blueprints.
	 */
	public AreaBlueprints(ArrayList<AreaBlueprint> blueprints) {
		this.blueprints = blueprints;
		
		// Check for duplicate area names and throw an exception if one is found.
		checkForAreaDuplicates();
	}
	
	/**
	 * Check for duplicate area names and throw an exception if one is found.
	 */
	private void checkForAreaDuplicates() {
		Set<String> areaNames = new HashSet<String>();
		
		for (AreaBlueprint blueprint : this.blueprints) {
			if (areaNames.contains(blueprint.getName())) {
				throw new RuntimeException("duplicate of area '" + blueprint.getName() + "' found!" );
			}
			
			areaNames.add(blueprint.getName());
		}
	}
}
