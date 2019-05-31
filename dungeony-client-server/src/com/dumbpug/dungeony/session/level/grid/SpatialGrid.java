package com.dumbpug.dungeony.session.level.grid;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Represents a spatial grid in which entities can be grouped into cells to facilitate broad phase collisions.
 */
public class SpatialGrid<TGridPositionedEntity extends IGridPositionedEntity> {
	/**
	 * The cell size.
	 */
	private float cellSize;
	/**
	 * The map of grid cells to the list of entities which have their origin residing in the cell.
	 */
	private HashMap<String, Cell<TGridPositionedEntity>> grid = new HashMap<String, Cell<TGridPositionedEntity>>();
	/**
	 * The list of entities to lay out in the grid.
	 */
	private HashMap<TGridPositionedEntity, SpatiallyPoisitionedEntity<TGridPositionedEntity>> entityToSpatialEntityMap = new HashMap<TGridPositionedEntity, SpatiallyPoisitionedEntity<TGridPositionedEntity>>();
	
	/**
	 * Create a new instance of the SpatialGrid class.
	 * @param cellSize The size of cell to use in the spatial grid.
	 */
	public SpatialGrid(float cellSize) {
		this.cellSize = cellSize;
	}
	
	/**
	 * Add an entity to the grid.
	 * @param entity The entity to add.
	 */
	public void add(TGridPositionedEntity entity) {
		// If this entity has already been added then there is nothing to do.
		if (this.entityToSpatialEntityMap.containsKey(entity)) {
			return;
		}
		
		// Add the new entity to the map ofentitys to their spatially positioned representations.
		this.entityToSpatialEntityMap.put(entity, new SpatiallyPoisitionedEntity<TGridPositionedEntity>(entity));
		
		// Update the positioned entity so that it is placed into its relevant cells.
		this.update(entity);
	}
	
	/**
	 * Remove an entity from the grid.
	 * @param entity The entity to remove.
	 */
	public void remove(TGridPositionedEntity entity) {
		// If this entity does not exist in the grid then there is nothing to do.
		if (!entityToSpatialEntityMap.containsKey(entity)) {
			return;
		}
		
		// Get the spatially positioned entity for theentity.
		SpatiallyPoisitionedEntity<TGridPositionedEntity> spatiallyPositionedentity = this.entityToSpatialEntityMap.get(entity);
		
		// Remove the positioned entity from ever cell that it is in via spatiallyPositionedentity.getCellKeys().
		for (Cell<TGridPositionedEntity> cell : spatiallyPositionedentity.getCells()) {
			cell.removeEntity(entity);
		}
		
		// Remove the entity from the entity to Spatial entity mapping.
		this.entityToSpatialEntityMap.remove(entity);
	}
	
	/**
	 * Update an entity in the grid.
	 * @param entity The entity to update.
	 */
	public void update(TGridPositionedEntity entity) {
		// Get the spatially positioned entity for theentity.
		SpatiallyPoisitionedEntity<TGridPositionedEntity> spatiallyPositionedentity = this.entityToSpatialEntityMap.get(entity);
		
		// Remove any references that any cells have to theentity.
		for (Cell<TGridPositionedEntity> cell : spatiallyPositionedentity.getCells()) {
			cell.removeEntity(entity);
		}
		
		// Clear any existing cells that the positioned entity thinks that it is in.
		spatiallyPositionedentity.clearCells();
		
		// Get the cell bounds of the entity.
		int xStartCell = this.getCellPosition(entity.getPosition().getX());
		int xEndCell   = this.getCellPosition(entity.getPosition().getX() + entity.getWidth());
		int yStartCell = this.getCellPosition(entity.getPosition().getY());
		int yEndCell   = this.getCellPosition(entity.getPosition().getY() + entity.getHeight());
		
		// Find all cells that the entity intersects and record the entity against each one.
		for (int cellX = xStartCell; cellX <= xEndCell; cellX++) {
			for (int cellY = yStartCell; cellY <= yEndCell; cellY++) {
				// Get the cell at the current position.
				Cell<TGridPositionedEntity> cell = this.getCell(cellX, cellY);
				
				// Add the entity to the cell.
				cell.addEntity(entity);
				
				// Add the cell key to the list of keys representing cells that the entity intersects for quick lookup.
				spatiallyPositionedentity.addCell(cell);
			}
		}
	}
	
	/**
	 * Get a set ofentitys that reside in the current or adjacent cells to the specified one excluding it.
	 * @param entity The entity for which to find a set ofentitys that reside in the current or adjacent cells to it.
	 * @return A set ofentitys that reside in the current or adjacent cells to the specified one excluding it.
	 */
	public HashSet<TGridPositionedEntity> getCollisionCandidates(TGridPositionedEntity entity) {
		// Get the spatially positioned entity for theentity.
		SpatiallyPoisitionedEntity<TGridPositionedEntity> spatiallyPositionedentity = this.entityToSpatialEntityMap.get(entity);
		
		// Create an empty set in which to add all neighbouring entities.
		HashSet<TGridPositionedEntity> neighbouringentitys = new HashSet<TGridPositionedEntity>();
		
		// For each cell key that the entity overlaps, add every entity that overlaps that cell into the neighbouring set.
		for (Cell<TGridPositionedEntity> cell : spatiallyPositionedentity.getCells()) {
			// Add every entity that overlaps the cell to our set.
			cell.collect(neighbouringentitys);
		}
		
		// Remove the specified entity from the set.
		neighbouringentitys.remove(entity);
		
		// Return the list of neighbouring entities.
		return neighbouringentitys;
	}
	
	/**
	 * Get the existing cell at the specified position.
	 * @param x The x position of the cell.
	 * @param y The y position of the cell.
	 * @return The existing cell at the specified position.
	 */
	private Cell<TGridPositionedEntity> getCell(int x, int y) {
		// Create the cell key.
		String cellKey = x + "_" + y;
		
		// Return the cell.
		return this.getCell(cellKey);
	}
	
	/**
	 * Get the existing cell with the specified key, or create one if it does not already exist.
	 * @param key The cell key.
	 * @return The existing cell with the specified key, or create one if it does not already exist.
	 */
	private Cell<TGridPositionedEntity> getCell(String key) {
		// Try to get the cell using the key.
		Cell<TGridPositionedEntity> cell = this.grid.get(key);
		
		// If the cell does not exist then we must create one and add it to our grid.
		if (cell == null) {
			// Create a new cell...
			cell = new Cell<TGridPositionedEntity>(key);
			
			// ... and add it to the grid.
			this.grid.put(key, cell);
		}
		
		// Return the cell.
		return cell;
	}
	
	/**
	 * Get the cell position based on an absolute world position. 
	 * @param value The absolute world position.
	 * @return The cell position based on an absolute world position. 
	 */
	private int getCellPosition(double value) {
		return (int) Math.floor(value / this.cellSize);
	}
}
