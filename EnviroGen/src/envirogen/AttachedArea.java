package envirogen;

import envirogen.area.AreaBlueprint;

/**
 * Represents an area that has been attached to an environemnt with a position and depth.
 */
public class AttachedArea {
	/**
	 * The position of the area in the environment.
	 */
	private Position position;
	/**
	 * The blueprint of the area.
	 */
	private AreaBlueprint areaBlueprint;
	/**
	 * The depth of the area.
	 */
	private int depth;
	
	/**
	 * Creates a new instance of the AttachedArea class.
	 * @param position The position of the area in the environment.
	 * @param areaBlueprint The blueprint of the area.
	 * @param depth The depth of the area.
	 */
	public AttachedArea(Position position, AreaBlueprint areaBlueprint, int depth) {
		this.position      = position;
		this.areaBlueprint = areaBlueprint;
		this.depth         = depth;
	}

	/**
	 * Gets the position of the area in the environment.
	 * @return The position of the area in the environment.
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Gets the blueprint of the area.
	 * @return The blueprint of the area.
	 */
	public AreaBlueprint getAreaBlueprint() {
		return areaBlueprint;
	}

	/**
	 * Gets the depth of the area.
	 * @return The depth of the area.
	 */
	public int getDepth() {
		return depth;
	}
}
