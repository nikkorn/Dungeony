package com.dumbpug.dungeony.session.level.grid;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a cell in a spatial grid.
 */
public class Cell<TGridPositionedEntity extends IGridPositionedEntity> {
	/**
	 * The cell key.
	 */
	private String key;
	/**
	 * The list of positioned entities in the cell.
	 */
	private ArrayList<TGridPositionedEntity> gridPositionedEntityList = new ArrayList<TGridPositionedEntity>();
	
	/**
	 * Create a new instance of the Cell class.
	 * @param key The cell key.
	 */
	public Cell(String key) {
		this.key = key;
	}
	
	/**
	 * Get the cell key.
	 * @return The cell key.
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Add all of the entities that intersect the cell to the specified set.
	 * @param existing The set of existing entities.
	 */
	public void collect(HashSet<TGridPositionedEntity> existing) {
		existing.addAll(this.gridPositionedEntityList);
	}
	
	/**
	 * Add the specified entity to this cell.
	 * @param aabb The entity to add to this cell. 
	 */
	public void addEntity(TGridPositionedEntity entity) {
		this.gridPositionedEntityList.add(entity);
	}
	
	/**
	 * Remove the specified entity from this cell.
	 * @param entity The entity to remove from this cell. 
	 */
	public void removeEntity(TGridPositionedEntity entity) {
		this.gridPositionedEntityList.remove(entity);
	}
}
