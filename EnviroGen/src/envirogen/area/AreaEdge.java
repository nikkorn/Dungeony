package envirogen.area;

/**
 * Enumeration of area edge types.
 */
public enum AreaEdge {
	UNDEFINED,
	TOP,
	BOTTOM,
	LEFT,
	RIGHT;
	
	/**
	 * Gets the adjoining edge for this edge.
	 * @return The adjoining edge for this edge.
	 */
	public AreaEdge getAdjoiningEdge() {
		switch(this) {
			case TOP:
				return AreaEdge.BOTTOM;
			case BOTTOM:
				return AreaEdge.TOP;
			case LEFT:
				return AreaEdge.RIGHT;
			case RIGHT:
				return AreaEdge.LEFT;
			default:
				return AreaEdge.UNDEFINED;
		}
	}
}
