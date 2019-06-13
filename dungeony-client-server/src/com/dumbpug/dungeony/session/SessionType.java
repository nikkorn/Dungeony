package com.dumbpug.dungeony.session;

/**
 * Enumeration of session types.
 */
public enum SessionType {
	/**
	 * The session is taking place on the client machine without using networking protocols.
	 */
	LOCAL,
	
	/**
	 * The session is taking place in a networked environment using TCP/UDP protocols.
	 */
	NETWORKED
}
