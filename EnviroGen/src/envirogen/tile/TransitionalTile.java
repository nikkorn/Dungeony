package envirogen.tile;

import envirogen.area.AreaEdge;

public class TransitionalTile {
	/**
	 * The edge of the area at which the tile is positioned.
	 */
	private AreaEdge edge;
	/**
	 * The position along the edge at which the tile is placed.
	 * Position is 'x' when the edge is TOP/BOTTOM.
	 * Position is 'y' when the edge is RIGHT/LEFT.
	 */
	private int position;
	/**
	 * The tile to replace an existing tile with if the transitional tile is used in linking a new area.
	 * If this is NULL then the transitional tile does not need to be cleared.
	 */
	private Tile clearedTile;
	
	/**
	 * Creates a new instance of the TransitionalTile class.
	 * @param edge The edge of the area at which the tile is positioned.
	 * @param position The position along the edge at which the tile is placed.
	 * @param clearedTile The tile to replace an existing tile with if the transitional tile is used in linking a new area.
	 */
	public TransitionalTile(AreaEdge edge, int position, Tile clearedTile) {
		this.edge        = edge;
		this.position    = position;
		this.clearedTile = clearedTile;
	}
	
	/**
	 * Gets the edge of the area at which the tile is positioned.
	 * @return The edge of the area at which the tile is positioned.
	 */
	public AreaEdge getEdge() {
		return this.edge;
	}
	
	/**
	 * Gets the position along the edge at which the tile is placed.
	 * Position is 'x' when the edge is TOP/BOTTOM.
	 * Position is 'y' when the edge is RIGHT/LEFT.
	 * @return The position along the edge at which the tile is placed.
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * Gets the tile to replace an existing tile with if the transitional tile is used in linking a new area.
	 * If this is NULL then the transitional tile does not need to be cleared.
	 * @return The tile to replace an existing tile with if the transitional tile is used in linking a new area.
	 */
	public Tile getClearedTile() {
		return this.clearedTile;
	}
}
