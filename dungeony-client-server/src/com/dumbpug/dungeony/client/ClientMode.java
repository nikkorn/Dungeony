package com.dumbpug.dungeony.client;

/**
 * Enumeration of client modes.
 */
public enum ClientMode {
	/**
	 * The client will not be depending on a server instance.
	 */
	LOCAL,
	
	/**
	 * The client will be hosting a server instance.
	 */
	HOSTING,
	
	/**
	 * The client will be connecting to a remote server instance.
	 */
	REMOTELY_CONNECTING
}
