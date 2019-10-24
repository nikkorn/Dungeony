package envirogen;

import java.util.ArrayList;
import envirogen.area.AreaBlueprint;
import envirogen.area.AreaBlueprints;
import envirogen.generated.Environment;

/**
 * An environment onto which areas can be attached.
 */
public class BuildableEnvironment {
	/*
	 * The configuration.
	 */
	private Configuration configuration;
	/**
	 * The initial area blueprint.
	 */
	private AreaBlueprint initialAreaBlueprint;
	/**
	 * The list of areas that have a position and have been attached to the environemnt.
	 */
	private ArrayList<AttachedArea> positionedAreas = new ArrayList<AttachedArea>();
	
	/**
	 * Creates a new instance of the BuildableEnvironment class.
	 * @param initialAreaBlueprint The blueprint for the initial starting area.
	 * @param configuration The configuration.
	 */
	public BuildableEnvironment(AreaBlueprint initialAreaBlueprint, Configuration configuration) {
		this.configuration = configuration;
		
		// Keep track of our initial area so that we can stop it from being attached again.
		this.initialAreaBlueprint = initialAreaBlueprint;
		
		// Attach the initial area to position 0/0 with a depth of 0.
		positionedAreas.add(new AttachedArea(new Position(0, 0), initialAreaBlueprint.copy(), 0));
	}
	
	/**
	 * Gets whether the enviroment built so far satisfies requirements and can be considered complete.
	 * @return Whether the enviroment built so far satisfies requirements and can be considered complete.
	 */
	public boolean isComplete() {
		// TODO Get whether the environment built so-far satisfied the requirements.
		return false;
	}
	
	/**
	 * Get the immutable generated environment.
	 * @return The immutable generated environment.
	 */
	public Environment toEnvironment() {
		if (!this.isComplete()) {
			throw new RuntimeException("cannot create environment from incomplete buildable environment.");
		}
		
		// TODO Create and return the real deal!
		return new Environment();
	}
	
	/**
	 * Attempt to attach an area blueprint to the environment being built.
	 * @param areaBlueprints The blueprints from which to pick an area to attach.
	 * @return Whether an area was able to be added.
	 */
	public boolean attach(AreaBlueprints areaBlueprints) {
		// TODO Get as list of all area attachment candidates as X.
		// TODO Sort X and prioritise candidates by those which: 
		//    1. Have an anchor that MUST have an area attached to it.
		//    2. Have a higher number of anchors.
		// TODO Get a list of all valid area blueprints as Y.
		// TODO Pick a random rarity and filter Y down to a list of areas that have that rarity.
		// TODO For each item in X iterate over Y until one is successfully applied and return true.
		// TODO If none are applied then move to the next item in X.
		// TODO If we reach the end of X without a successful attachment then return false.
		
		// The area was attached to the environment.
		return true;
	}

}
