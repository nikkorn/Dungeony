package com.dumbpug.dungeony.server.lobby;

import com.dumbpug.dungeony.Constants;

/**
 * The lobby timer used to count down to the beginning of a session.
 */
public class LobbyTimer {
	/**
	 * The countdown start.
	 */
	private Long start = null;
	/**
	 * The last countdown value.
	 */
	private Integer lastCountdownValue = null;
	
	/**
	 * Get the last countdown value returned via 'getCountdown'.
	 * @return The last countdown value returned via 'getCountdown'.
	 */
	public Integer getLastCountdown() {
		return lastCountdownValue;
	}
	
	/**
	 * Get the time remaining in seconds until the session can begin. 
	 * @param areAllClientsReady Whether all clients are ready to begin the session.
	 * @return The time remaining in seconds until the session can begin. 
	 */
	public Integer getCountdown(boolean areAllClientsReady) {
		// There is no countdown value if not all clients are ready.
		if (!areAllClientsReady) {
			start = null;
			lastCountdownValue = null;
			return null;
		}
		
		// Is this the start of the countdown?
		if (start == null) {
			start = System.currentTimeMillis();
		}
		
		int countdown = Constants.LOBBY_COUNTDOWN_SECONDS - Math.min((int) ((System.currentTimeMillis() - start) / 1000), Constants.LOBBY_COUNTDOWN_SECONDS);
		
		this.lastCountdownValue = countdown;
		
		return countdown;
	}
}
