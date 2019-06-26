package com.dumbpug.dungeony.server.lobby;

import java.util.ArrayList;
import java.util.Iterator;
import com.dumbpug.dungeony.lobby.LobbySlotState;
import com.dumbpug.dungeony.networking.messaging.messages.JoinSuccess;
import com.dumbpug.dungeony.server.networking.ConnectedClient;
import com.dumbpug.dungeony.server.networking.ConnectedClientStatus;

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
	
	/**
	 * Process any disconnections for slots that have clients.
	 * @return Whether any client disconnections were processed.
	 */
	public boolean resetDisconnectedSlots() {
		// Create a flag to set if any slots are reset.
		boolean wasSlotReset = false;
		
		for (LobbySlot slot : this.slots) {
			// Try to get the client in this slot.
			ConnectedClient clientInSlot = slot.getClient();
			
			// Is there a client in the slot and are they now disconnected?
			if (clientInSlot != null && !clientInSlot.isConnected()) {
				// Reset the current slot so thta it can be allocated to any future clients.
				slot.reset();
				
				// We have just reset a slot.
				wasSlotReset = true;
			}
		}
		
		// Return whether any slots were reset.
		return wasSlotReset;
	}
	
	/**
	 * Process any join requests for any waiting clients.
	 * @return Whether a client has successfully joined the lobby.
	 */
	public boolean processJoinRequests() {
		// Create a flag to set if a client has successfully joined the lobby.
		boolean hasClientJoined = false;
					
		for (LobbySlot slot : this.slots) {
			// Try to get the client in this slot.
			ConnectedClient clientInSlot = slot.getClient();
			
			// Is there a client in the slot and are they waiting to join the lobby?
			if (clientInSlot != null && clientInSlot.getStatus() == ConnectedClientStatus.WAITING_TO_JOIN) {
				// Send a lobby join success message to the client.
				clientInSlot.sendMessage(new JoinSuccess());
				
				// Update the client status to reflect the fact that they have actually joined the lobby.
				clientInSlot.setStatus(ConnectedClientStatus.CONNECTED);
				
				// We will need to send a lobby state update to all clients.
				hasClientJoined = true;
			}
		}
		
		return hasClientJoined;
	}

	@Override
	public Iterator<LobbySlot> iterator() {
		return this.slots.iterator();
	}
}
