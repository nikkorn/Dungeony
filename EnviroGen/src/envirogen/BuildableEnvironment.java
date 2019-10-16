package envirogen;

import java.util.ArrayList;
import envirogen.area.AreaBlueprint;

/**
 * An environment onto which areas can be attached.
 */
public class BuildableEnvironment {
	/**
	 * The name of the initial area.
	 */
	private String initialAreaName;
	/**
	 * The list of areas that have a position and have been attached to the environemnt.
	 */
	private ArrayList<AttachedArea> positionedAreas = new ArrayList<AttachedArea>();
	
	/**
	 * Creates a new instance of the BuildableEnvironment class.
	 * @param initialAreaBlueprint The blueprint for the initial starting area.
	 */
	public BuildableEnvironment(AreaBlueprint initialAreaBlueprint) {
		// Keep track of the name of our initial area so that we can stop it from being attached again.
		this.initialAreaName = initialAreaBlueprint.getName();
		
		// Attach the initial area to position 0/0 with a depth of 0.
		positionedAreas.add(new AttachedArea(new Position(0, 0), initialAreaBlueprint.copy(), 0));
	}
	
	/**
	 * Attempt to attach the given are blueprint to the environemtn being built.
	 * @param areaBlueprint The blueprint of the area to add.
	 * @return Whether the area was actually added.
	 */
	public boolean attach(AreaBlueprint areaBlueprint) {
		// The area we are attempting to attach cannot be our initial area.
		if (areaBlueprint.getName().equals(this.initialAreaName)) {
			throw new RuntimeException("Cannot attach initial area to buildable environment."); 
		}
		
		// ...
		
		// The area was attached to the environment.
		return true;
	}

}
