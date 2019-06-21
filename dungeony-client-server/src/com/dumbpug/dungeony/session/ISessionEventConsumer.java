package com.dumbpug.dungeony.session;

import com.dumbpug.dungeony.session.events.ISessionEvent;

/**
 * Represents a consumer of queued session events.
 */
public interface ISessionEventConsumer {
	
	/**
	 * Consume the next queued session event.
	 * @param event The next queued session event.
	 */
	public void consume(ISessionEvent event);
}
