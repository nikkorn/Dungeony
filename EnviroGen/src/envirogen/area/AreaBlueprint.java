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
	 * Whether this is the initial area.
	 */
	private boolean isInitialArea;
	/**
	 * The edge of valid entry for the area.
	 * If not 'UNDEFINED' then this edge is the only edge at which this are can be attache dto an inward area.
	 */
	private AreaEdge edgeOfEntry;
	
	/**
	 * Create a new instance of the AreaBlueprint class.
	 * @param name The name of the area.
	 * @param isInitialArea Whether this area is the initial one.
	 * @param edgeOfEntry The edge of valid entry for the area.
	 */
	public AreaBlueprint(String name, boolean isInitialArea, AreaEdge edgeOfEntry) {
		this.name          = name;
		this.isInitialArea = isInitialArea;
		this.edgeOfEntry   = edgeOfEntry;
	}
	
	/**
	 * Gets the name of the area.
	 * @return The name of the area.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets whether this is the initial area.
	 * @return Whether this is the initial area.
	 */
	public boolean isInitialArea() {
		return isInitialArea;
	}
	
	/**
	 * Gets the edge of this area that can be attached to an already attached inward area.
	 * @return The edge of this area that can be attached to an already attached inward area, or 'UNDEFINED' is any side is fine.
	 */
	public AreaEdge getEdgeOfEntry() {
		return edgeOfEntry;
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
