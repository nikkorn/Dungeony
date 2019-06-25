package com.dumbpug.dungeony.networking.messaging.messages;

import com.dumbpug.dungeony.networking.messaging.IMessage;

/**
 * A message sent to the server whenever the input state of a player changes.
 */
public class ClientInputStateChanged implements IMessage {
	/**
	 * The key-down states.
	 */
	private boolean isPrimaryKeyDown   = false;
	private boolean isSecondaryKeyDown = false;
	private boolean isTertiaryKeyDown  = false;
	private boolean isUpKeyDown        = false;
	private boolean isDownKeyDown      = false;
	private boolean isLeftKeyDown      = false;
	private boolean isRightKeyDown     = false;
	
	/**
	 * Creates a new instance of the ClientInputStateChanged class.
	 * @param isPrimaryKeyDown
	 * @param isSecondaryKeyDown
	 * @param isTertiaryKeyDown
	 * @param isUpKeyDown
	 * @param isDownKeyDown
	 * @param isLeftKeyDown
	 * @param isRightKeyDown
	 */
	public ClientInputStateChanged(
		boolean isPrimaryKeyDown, 
		boolean isSecondaryKeyDown, 
		boolean isTertiaryKeyDown,
		boolean isUpKeyDown,
		boolean isDownKeyDown,
		boolean isLeftKeyDown,
		boolean isRightKeyDown
	) {
		this.isPrimaryKeyDown   = isPrimaryKeyDown;
		this.isSecondaryKeyDown = isSecondaryKeyDown;
		this.isTertiaryKeyDown  = isTertiaryKeyDown;
		this.isUpKeyDown        = isUpKeyDown;
		this.isDownKeyDown      = isDownKeyDown;
		this.isLeftKeyDown      = isLeftKeyDown;
		this.isRightKeyDown     = isRightKeyDown;
	}

	public boolean isPrimaryKeyDown() {
		return isPrimaryKeyDown;
	}

	public boolean isSecondaryKeyDown() {
		return isSecondaryKeyDown;
	}

	public boolean isTertiaryKeyDown() {
		return isTertiaryKeyDown;
	}

	public boolean isUpKeyDown() {
		return isUpKeyDown;
	}

	public boolean isDownKeyDown() {
		return isDownKeyDown;
	}

	public boolean isLeftKeyDown() {
		return isLeftKeyDown;
	}

	public boolean isRightKeyDown() {
		return isRightKeyDown;
	}
	
	@Override
	public int getTypeId() {
		return MessageIdentifier.CLIENT_INPUT_STATE_CHANGED;
	}
}
