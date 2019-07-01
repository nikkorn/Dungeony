package com.dumbpug.dungeony.networking.messaging.messages;

import java.util.ArrayList;
import com.dumbpug.dungeony.lobby.LobbySlotState;
import com.dumbpug.dungeony.networking.messaging.IMessage;

/**
 * A message sent to every client whenever the lobby state is updated.
 */
public class LobbyStateUpdate implements IMessage {
	/**
	 * The list of lobby slot states.
	 */
	private ArrayList<LobbySlotState> lobbySlotStates;
	
	/**
	 * Creates a new instance of the LobbyStateUpdate class.
	 * @param lobbySlotStates The list of lobby slot states.
	 */
	public LobbyStateUpdate(ArrayList<LobbySlotState> lobbySlotStates) {
		this.lobbySlotStates = lobbySlotStates;
	}
	
	/**
	 * Gets the list of lobby slot states.
	 * @return The list of lobby slot states.
	 */
	public ArrayList<LobbySlotState> getLobbySlotStates() {
		return lobbySlotStates;
	}

	@Override
	public int getTypeId() {
		return MessageIdentifier.LOBBY_STATE_UPDATE;
	}
}
