package com.dumbpug.dungeony.session.item;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.session.level.ILevelPositionedEntity;
import com.dumbpug.dungeony.session.level.Position;

/**
 * An item dropped within a level.
 */
public abstract class ItemDrop implements ILevelPositionedEntity {
	/**
	 * The position of the dropped item.
	 */
	private Position position;
	/**
	 * The type of the dropped item.
	 */
	private ItemType itemType;
	/**
	 * The quanitity of the item.
	 */
	private int quantity;
	
	/**
	 * Creates a new instance of the ItemDrop class.
     * @param itemType The type of the dropped item.
	 * @param position The position of the dropped item.
	 * @param quantity The quanitity of the item.
	 */
	public ItemDrop(ItemType itemType, Position position, int quantity) {
        this.itemType = itemType;
		this.position = position;
		this.quantity = quantity;
    }
    
    /**
     * Gets the type of the dropped item.
     * @return The type of the dropped item.
     */
    public ItemType getItemType() {
        return this.itemType;
    }
    
    /**
	 * Gets the quantity of the item.
	 * @return The quantity of the item.
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Gets the position of the dropped item.
	 * @return The position of the dropped item.
	 */
    @Override
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Gets the width of the character.
	 * @return The width of the character.
	 */
	@Override
	public float getWidth() {
		return Constants.ITEM_DROPPED_SIZE;
	}

	/**
	 * Gets the height of the character.
	 * @return The height of the character.
	 */
	@Override
	public float getHeight() {
		return Constants.ITEM_DROPPED_SIZE;
	}
}