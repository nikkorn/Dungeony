package com.dumbpug.dungeony.session.level.grid;

import java.util.ArrayList;

/**
 * A spatially positioned entity.
 */
public class SpatiallyPoisitionedEntity<TGridPositionedEntity extends IGridPositionedEntity> {
	/**
	 * The entity.
	 */
	private TGridPositionedEntity entity;
	/**
	 * The list of cells that the entity overlaps.
	 */
	private ArrayList<Cell<TGridPositionedEntity>> cells = new ArrayList<Cell<TGridPositionedEntity>>();
	
	/**
	 * Create a new instance of the SpatiallyPoisitionedEntity class.
	 * @param entity The entity.
	 */
	public SpatiallyPoisitionedEntity(TGridPositionedEntity entity) {
		this.entity = entity;
	}
	
	/**
	 * Get the list of cells that the entity overlaps.
	 * @return The list of cells that the entity overlaps.
	 */
	public ArrayList<Cell<TGridPositionedEntity>> getCells() {
		return this.cells;
	}
	
	/**
	 * Add a cell to the list of cells that the entity overlaps.
	 * @param cell The cell to add.
	 */
	public void addCell(Cell<TGridPositionedEntity> cell) {
		this.cells.add(cell);
	}
	
	/**
	 * Clear the list of cells that the entity overlaps.
	 */
	public void clearCells() {
		this.cells.clear();
	}
	
	/**
	 * Get the entity.
	 * @return The entity.
	 */
	public TGridPositionedEntity getEntity() {
		return this.entity;
	}
}
