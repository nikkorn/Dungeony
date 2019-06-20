package com.dumbpug.dungeony.client;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.session.Session;

/*
 * Updates a local session on a new thread at the same rate as a server would update a session.
 */
public class LocalSessionClock implements Runnable {
	/**
	 * The local session.
	 */
	private Session session;

	/**
	 * Creates a new instance of the LocalSessionClock class.
	 * @param session The local session.
	 */
	public LocalSessionClock(Session session) {
		this.session = session;
	}
	
	/**
	 * Start the session clock.
	 */
	public void start() {
		// Create the thread on which to tick the session.
		Thread sessionClockThread = new Thread(this);
		
		// When active, this thread should not prevent the application from terminating
		sessionClockThread.setDaemon(true);
		
		// Start the session clock thread!
		sessionClockThread.start();
	}

	@Override
	public void run() {
		// The last time that we ticked the session.
		long lastTickCall = 0;
		
		// Start the session tick loop. 
		while (true) {
			// Get the current system time.
			long currentTimeMillis = System.currentTimeMillis();
			
			// We only want to tick the session if we have waited long enough to do so.
			if (currentTimeMillis >= (lastTickCall + Constants.SESSION_TICK_RATE)) {
				// Tick the session.
				this.session.tick();
				
				// Get how long it took to tick the session.
				long tickDuration = System.currentTimeMillis() - currentTimeMillis;
				
				// Check whether our session tick too long to execute. (exceeded the session tick rate)
				if (tickDuration >= Constants.SESSION_TICK_RATE) {
					System.out.println("tick exceeded session clock rate and took: " + tickDuration + "ms");				
				}
				
				// Update the last tick time.
				lastTickCall = currentTimeMillis;
			}
		}
	}
}
