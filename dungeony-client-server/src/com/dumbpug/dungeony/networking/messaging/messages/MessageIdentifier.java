package com.dumbpug.dungeony.networking.messaging.messages;

/**
 * The unique message identifiers.
 */
public class MessageIdentifier {

	/**
	 * Client -> Server
	 */
	public static final int CLIENT_KEY_INPUT_STATE_CHANGED     = 1;
	public static final int LOBBY_SET_SLOT_READY               = 2;
	public static final int LOBBY_SET_SLOT_COLOUR              = 3;

	/**
	 * Server -> Client
	 */
	public static final int JOIN_SUCCESS                       = 100;
	public static final int JOIN_FAIL                          = 101;
	public static final int LOBBY_STATE_UPDATE                 = 102;
	public static final int PLAYER_SPAWNED                     = 103;
	public static final int PLAYER_POSITION_UPDATE             = 104;
}
