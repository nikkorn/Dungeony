package com.dumbpug.dungeony.session.events;

/**
 * Represents a session event raised during a session update/tick.
 */
public interface ISessionEvent {
	
	/**
	 * Gets the session event type.
	 * @return The session event type.
	 */
	public SessionEvent getEvent();
}
