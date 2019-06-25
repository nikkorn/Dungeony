package com.dumbpug.dungeony.networking.messaging.messages;

/**
 * The unique message identifiers.
 */
public class MessageIdentifier {

	/**
	 * Client -> Server
	 */
	public static final int CLIENT_INPUT_STATE_CHANGED         = 1;

	/**
	 * Server -> Client
	 */
	public static final int JOIN_SUCCESS                       = 100;
	public static final int JOIN_FAIL                          = 101;
	public static final int PLAYER_SPAWNED                     = 102;
	public static final int PLAYER_POSITION_UPDATE             = 103;
}
