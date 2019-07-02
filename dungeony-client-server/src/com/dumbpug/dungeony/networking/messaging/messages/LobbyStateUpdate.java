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
	 * The session start countdown.
	 */
	private Integer countdown;
	
	/**
	 * Creates a new instance of the LobbyStateUpdate class.
	 * @param lobbySlotStates The list of lobby slot states.
	 * @param countdown The session start countdown.
	 */
	public LobbyStateUpdate(ArrayList<LobbySlotState> lobbySlotStates, Integer countdown) {
		this.lobbySlotStates = lobbySlotStates;
		this.countdown       = countdown;
	}
	
	/**
	 * Gets the list of lobby slot states.
	 * @return The list of lobby slot states.
	 */
	public ArrayList<LobbySlotState> getLobbySlotStates() {
		return lobbySlotStates;
	}
	
	/**
	 * Gets the session start countdown.
	 * @return The session start countdown.
	 */
	public Integer getCountdown() {
		return countdown;
	}

	@Override
	public int getTypeId() {
		return MessageIdentifier.LOBBY_STATE_UPDATE;
	}
}
