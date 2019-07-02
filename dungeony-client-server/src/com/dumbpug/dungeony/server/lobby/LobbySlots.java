package com.dumbpug.dungeony.server.lobby;

import java.util.ArrayList;
import java.util.Iterator;
import com.dumbpug.dungeony.lobby.Colour;
import com.dumbpug.dungeony.lobby.LobbySlotState;
import com.dumbpug.dungeony.networking.messaging.IMessage;
import com.dumbpug.dungeony.networking.messaging.MessageQueue;
import com.dumbpug.dungeony.networking.messaging.messages.JoinSuccess;
import com.dumbpug.dungeony.networking.messaging.messages.LobbySetSlotColour;
import com.dumbpug.dungeony.networking.messaging.messages.LobbySetSlotReady;
import com.dumbpug.dungeony.networking.messaging.messages.MessageIdentifier;
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
	 * Gets the slot with the givent number.
	 * @param number The slot number.
	 * @return The slot with the givent number.
	 */
	public LobbySlot getSlot(int number) {
		return this.slots.get(number - 1);
	}
	
	/**
	 * Gets a snapshot of this list.
	 * @return A snapshot of this list.
	 */
	public ArrayList<LobbySlotState> getSnapShot() {
		// Create the snapshot list.
		ArrayList<LobbySlotState> snapshot = new ArrayList<LobbySlotState>();
		
		for (int slotNumber = 1; slotNumber <= 4; slotNumber++) {
			// Get the lobby slot at the current index.
			LobbySlot current = this.slots.get(slotNumber - 1);
			
			// Try to get the player id of the client in the current slot.
			String currentSlotPlayerId = current.getClient() != null ? current.getClient().getPlayerId() : null;
			
			// Add a snapshot of the current slot state to the list of other states.
			snapshot.add(new LobbySlotState(slotNumber, currentSlotPlayerId, current.isReady(), current.getColour()));
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
	 * Gets whether all populated slots are in the 'ready' state.
	 * @return Whether all populated slots are in the 'ready' state.
	 */
	public boolean areAllReady() {
		for (LobbySlot slot : this.slots) {
			if (slot.getClient() != null && !slot.isReady()) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Gets whether all slots are not populated.
	 * @return Whether all slots are not populated.
	 */
	public boolean areAllEmpty() {
		for (LobbySlot slot : this.slots) {
			if (slot.getClient() != null) {
				return false;
			} 
		}
		
		return true;
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
	
	/**
	 * Process any queued messages from clients that relate to modifying the state of the lobby.
	 * @return Whether the lobby state was modified.
	 */
	public boolean processLobbyTargetedMessages() {
		boolean hasLobbyStateChanged = false;
		
		for (LobbySlot slot : this.slots) {
			// Try to get the client in this slot.
			ConnectedClient clientInSlot = slot.getClient();
			
			// If there is no client in this slot then just move on to the next one.
			if (clientInSlot == null) {
				continue;
			}
			
			// Get the queue of messages set from the current client.
			MessageQueue messageQueue = clientInSlot.getReceivedMessageQueue();
			
			// Process every message in the queue.
			while (messageQueue.hasNext()) {
				// Get the next message in the queue.
				IMessage message = messageQueue.next();
				
				switch (message.getTypeId()) {
					case MessageIdentifier.LOBBY_SET_SLOT_READY:
						slot.setReady(((LobbySetSlotReady)message).isReady());
						System.out.println("client '" + slot.getClient().getPlayerId() + "' is " + (slot.isReady() ? "" : "not") + " ready!");
						hasLobbyStateChanged = true;
						break;
						
					case MessageIdentifier.LOBBY_SET_SLOT_COLOUR:
						// Get the colour that the client wishes to assign to their slot.
						Colour colour = ((LobbySetSlotColour)message).getColour();
						
						// Apply the requested colour to the slot if it is available.
						if (isSlotColourAvailable(colour)) {
							slot.setColour(colour);
							System.out.println("client '" + slot.getClient().getPlayerId() + "' is now colour '" + colour + "'");
							hasLobbyStateChanged = true;
						}
						break;
						
					default:
						// The current message has nothing to do with the lobby, just throw it away.
				}
			}
		}
		
		return hasLobbyStateChanged;
	}
	
	/**
	 * Gets whether the specified colour is available for use.
	 * @param colour The colour to check.
	 * @return Whether the specified colour is available for use.
	 */
	private boolean isSlotColourAvailable(Colour colour) {
		for (LobbySlot slot : this.slots) {
			if (slot.getColour() == colour) {
				// There is a slot using this colour already.
				return false;
			}
		}
		
		// No slot is using the specified colour.
		return true;
	}

	@Override
	public Iterator<LobbySlot> iterator() {
		return this.slots.iterator();
	}
}
