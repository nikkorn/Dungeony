package envirogen.area;

/*
 * A representation of a generatable area with mutable tiles.
 */
public class AreaBlueprint {
	/**
	 * The name of the area.
	 */
	private String name;
	
	/**
	 * Create a new instance of the AreaBlueprint class.
	 * @param name The name of the area.
	 */
	public AreaBlueprint(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of the area.
	 * @return The name of the area.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the edge of this area that can be attached to an already attached inward area.
	 * @return The edge of this area that can be attached to an already attached inward area.
	 */
	public AreaEdge getAttachingEdge() {
		// TODO This is determined by which area edge our 'in' is on, or AreaEdge.UNDEFINED.
		return AreaEdge.LEFT;
	}
	
	/**
	 * Gets whether this area requires another area to be attached on the specified edge.
	 * @param edge The edge.
	 * @return Whether this area requires another area to be attached on the specified edge.
	 */
	public boolean needsAttachmentOnEdge(AreaEdge edge) {
		// TODO An edge is blocked as long as there are no transitional tiles along it that haven't been cleared to be walkable.
		// A blocked edge is fine and doesnt need to be attached to.
		return true;
	}
	
	/**
	 * Return a copy of the area blueprint.
	 * @return A copy of the area blueprint.
	 */
	public AreaBlueprint copy() {
		// TODO Create a deep copy!!!
		return this;
	}
}
