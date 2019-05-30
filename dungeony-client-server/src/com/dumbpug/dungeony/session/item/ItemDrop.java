package com.dumbpug.dungeony.session.item;

import com.dumbpug.dungeony.session.level.Position;

/**
 * An item dropped within a level.
 */
public abstract class ItemDrop {
	/**
	 * The position of the dropped item.
	 */
	private Position position;
	/**
	 * The type of the dropped item.
	 */
	private ItemType itemType;
	
	/**
	 * Creates a new instance of the ItemDrop class.
     * @param itemType The type of the dropped item.
	 * @param position The position of the dropped item.
	 */
	public ItemDrop(ItemType itemType, Position position) {
        this.itemType = itemType;
		this.position = position;
    }
    
    /**
     * Gets the type of the dropped item.
     * @return The type of the dropped item.
     */
    public ItemType getItemType() {
        return this.itemType;
    }
	
	/**
	 * Gets the position of the dropped item.
	 * @return The position of the dropped item.
	 */
	public Position getPosition() {
		return this.position;
	}
}