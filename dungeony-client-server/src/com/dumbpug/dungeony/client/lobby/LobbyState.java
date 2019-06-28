package com.dumbpug.dungeony.client.lobby;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A client-side representation of server-side lobby state.
 */
public class LobbyState implements Iterable<LobbySlotState> {
	/**
	 * The list of lobby slot states.
	 */
	private ArrayList<LobbySlotState> slots = new ArrayList<LobbySlotState>();
	/**
	 * The number of seconds until the session begins, or null if there is no countdown.
	 */
	private Integer secondsUntilSessionStart = null;
	
	/**
	 * Create a new instance of the LobbyState class.
	 */
	public LobbyState() {
		this.slots.add(new LobbySlotState(1));
		this.slots.add(new LobbySlotState(2));
		this.slots.add(new LobbySlotState(3));
		this.slots.add(new LobbySlotState(4));
	}

	/**
	 * Gets the number of seconds until the session begins, or null if there is no countdown.
	 * @return The number of seconds until the session begins, or null if there is no countdown.
	 */
	public Integer getSecondsUntilSessionStart() {
		return secondsUntilSessionStart;
	}

	/**
	 * Sets the number of seconds until the session begins, or null if there is no countdown.
	 * @param secondsUntilSessionStart The number of seconds until the session begins, or null if there is no countdown.
	 */
	public void setSecondsUntilSessionStart(Integer secondsUntilSessionStart) {
		this.secondsUntilSessionStart = secondsUntilSessionStart;
	}
	
	@Override
	public Iterator<LobbySlotState> iterator() {
		return this.slots.iterator();
	}
}
