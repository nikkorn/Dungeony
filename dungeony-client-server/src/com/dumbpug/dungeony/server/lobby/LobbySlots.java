package com.dumbpug.dungeony.server.lobby;

import java.util.ArrayList;
import java.util.Iterator;
import com.dumbpug.dungeony.lobby.LobbySlotState;

/**
 * A list of lobby slots.
 */
public class LobbySlots implements Iterable<LobbySlot> {
	/**
	 * The list of lobby slots.
	 */
	private ArrayList<LobbySlot> slots = new ArrayList<LobbySlot>();
	
	/**
	 * Create a new instance of the LobbySlots class.
	 */
	public LobbySlots() {
		this.slots.add(new LobbySlot(1));
		this.slots.add(new LobbySlot(2));
		this.slots.add(new LobbySlot(3));
		this.slots.add(new LobbySlot(4));
	}
	
	/**
	 * Gets a snapshot of this list.
	 * @return A snapshot of this list.
	 */
	public ArrayList<LobbySlotState> getSnapShot() {
		// Create the snapshot list.
		ArrayList<LobbySlotState> snapshot = new ArrayList<LobbySlotState>();
		
		for (int slotIndex = 1; slotIndex <= 4; slotIndex++) {
			// Get the lobby slot at the current index.
			LobbySlot current = this.slots.get(slotIndex);
			
			// Try to get the player id of the client in the current slot.
			String currentSlotPlayerId = current.getClient() != null ? current.getClient().getPlayerId() : null;
			
			// Add a snapshot of the current slot state to the list of other states.
			snapshot.add(new LobbySlotState(slotIndex, currentSlotPlayerId, current.isReady(), current.getColour()));
		}
		
		// Return the snapshot list.
		return snapshot;
	}
	
	/**
	 * Gets the next free lobby slot, or null if there isn't one.
	 * @return The next free lobby slot, or null if there isn't one.
	 */
	public LobbySlot getNextFreeSlot() {
		for (LobbySlot slot : this.slots) {
			if (slot.getClient() == null) {
				// We found a free slot.
				return slot;
			}
		}
		
		// There was no free slot.
		return null;
	}

	@Override
	public Iterator<LobbySlot> iterator() {
		return this.slots.iterator();
	}
}
