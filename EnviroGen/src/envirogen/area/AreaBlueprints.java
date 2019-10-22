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
	 * The blueprint of the initial area.
	 */
	private AreaBlueprint initialAreaBlueprint;
	
	/**
	 * Creates a new instance of the AreaBlueprints class.
	 * @param blueprints The list of unique area blueprints.
	 */
	public AreaBlueprints(ArrayList<AreaBlueprint> blueprints) {
		this.blueprints = blueprints;
		
		// Check for duplicate area names and throw an exception if one is found.
		checkForAreaDuplicates();
		
		// Verify and set the initial area blueprint.
		setInitialAreaBlueprint();
	}
	
	/**
	 * Gets the blueprint of the initial area.
	 * @return The blueprint of the initial area.
	 */
	public AreaBlueprint getInitialAreaBlueprint() {
		return initialAreaBlueprint;
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
	
	/**
	 * Verify and set the initial area blueprint.
	 */
	private void setInitialAreaBlueprint() {
		ArrayList<AreaBlueprint> initialAreaBlueprints = new ArrayList<AreaBlueprint>();
		
		for (AreaBlueprint blueprint : this.blueprints) {
			if (blueprint.isInitialArea()) {
				initialAreaBlueprints.add(blueprint);
			}
		}
		
		// We should only ever have a single initial area blueprint.
		if (initialAreaBlueprints.size() != 1) {
			throw new RuntimeException("single initial area should be defined but '" + initialAreaBlueprints.size() + "' were found!" );
		}
		
		initialAreaBlueprint = initialAreaBlueprints.get(0);
	}
}
