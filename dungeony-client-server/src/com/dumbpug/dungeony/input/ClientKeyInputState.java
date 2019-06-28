package com.dumbpug.dungeony.input;

import com.dumbpug.dungeony.utilities.BitPacker;

/**
 * A snapshot of the key input state of a client.
 */
public class ClientKeyInputState {
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
	 * Creates a new instance of the ClientKeyInputState class.
	 */
	public ClientKeyInputState() {}
	
	/**
	 * Creates a new instance of the ClientKeyInputState class.
	 * @param isPrimaryKeyDown
	 * @param isSecondaryKeyDown
	 * @param isTertiaryKeyDown
	 * @param isUpKeyDown
	 * @param isDownKeyDown
	 * @param isLeftKeyDown
	 * @param isRightKeyDown
	 */
	public ClientKeyInputState(
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
	public void setPrimaryKeyDown(boolean isPrimaryKeyDown) {
		this.isPrimaryKeyDown = isPrimaryKeyDown;
	}
	
	public boolean isSecondaryKeyDown() {
		return isSecondaryKeyDown;
	}
	public void setSecondaryKeyDown(boolean isSecondaryKeyDown) {
		this.isSecondaryKeyDown = isSecondaryKeyDown;
	}
	
	public boolean isTertiaryKeyDown() {
		return isTertiaryKeyDown;
	}
	public void setTertiaryKeyDown(boolean isTertiaryKeyDown) {
		this.isTertiaryKeyDown = isTertiaryKeyDown;
	}
	
	public boolean isUpKeyDown() {
		return isUpKeyDown;
	}
	public void setUpKeyDown(boolean isUpKeyDown) {
		this.isUpKeyDown = isUpKeyDown;
	}
	
	public boolean isDownKeyDown() {
		return isDownKeyDown;
	}
	public void setDownKeyDown(boolean isDownKeyDown) {
		this.isDownKeyDown = isDownKeyDown;
	}
	
	public boolean isLeftKeyDown() {
		return isLeftKeyDown;
	}
	public void setLeftKeyDown(boolean isLeftKeyDown) {
		this.isLeftKeyDown = isLeftKeyDown;
	}
	
	public boolean isRightKeyDown() {
		return isRightKeyDown;
	}
	public void setRightKeyDown(boolean isRightKeyDown) {
		this.isRightKeyDown = isRightKeyDown;
	}
	
	/**
	 * Update the client input state.
	 * @param isPrimaryKeyDown
	 * @param isSecondaryKeyDown
	 * @param isTertiaryKeyDown
	 * @param isUpKeyDown
	 * @param isDownKeyDown
	 * @param isLeftKeyDown
	 * @param isRightKeyDown
	 * @return Whether any key state has changed as part of this update.
	 */
	public boolean update(
		boolean isPrimaryKeyDown, 
		boolean isSecondaryKeyDown, 
		boolean isTertiaryKeyDown,
		boolean isUpKeyDown,
		boolean isDownKeyDown,
		boolean isLeftKeyDown,
		boolean isRightKeyDown
	) {
		// Get the client key input state as a packed integer.
		int oldPackedInputState = this.toPackedInt();
		
		// Update the client input state.
		this.setPrimaryKeyDown(isPrimaryKeyDown);
		this.setSecondaryKeyDown(isSecondaryKeyDown);
		this.setTertiaryKeyDown(isTertiaryKeyDown);
		this.setUpKeyDown(isUpKeyDown);
		this.setDownKeyDown(isDownKeyDown);
		this.setLeftKeyDown(isLeftKeyDown);
		this.setRightKeyDown(isRightKeyDown);
		
		// Return whether the input state has changed.
		return oldPackedInputState != this.toPackedInt();
	}
	
	/**
	 * Convert the input state to an integer value.
	 * @return An integer value representing the input state.
	 */
	public int toPackedInt() {
		int packed = BitPacker.pack(0, this.isPrimaryKeyDown() ? 1 : 0, 0, 1);
		packed     = BitPacker.pack(packed, this.isSecondaryKeyDown() ? 1 : 0, 1, 2);
		packed     = BitPacker.pack(packed, this.isTertiaryKeyDown() ? 1 : 0, 2, 3);
		packed     = BitPacker.pack(packed, this.isUpKeyDown() ? 1 : 0, 3, 4);
		packed     = BitPacker.pack(packed, this.isDownKeyDown() ? 1 : 0, 4, 5);
		packed     = BitPacker.pack(packed, this.isLeftKeyDown() ? 1 : 0, 5, 6);
		packed     = BitPacker.pack(packed, this.isRightKeyDown() ? 1 : 0, 6, 7);
		return packed;
	}
	
	/**
	 * Convert a packed integer value to a ClientKeyInputState instance.
	 * @return A ClientKeyInputState instance.
	 */
	public static ClientKeyInputState fromPackedInt(int packed) {
		boolean isPrimaryKeyDown   = BitPacker.unpack(packed, 0, 1) == 1;
		boolean isSecondaryKeyDown = BitPacker.unpack(packed, 1, 2) == 1;
		boolean isTertiaryKeyDown  = BitPacker.unpack(packed, 2, 3) == 1;
		boolean isUpKeyDown        = BitPacker.unpack(packed, 3, 4) == 1;
		boolean isDownKeyDown      = BitPacker.unpack(packed, 4, 5) == 1;
		boolean isLeftKeyDown      = BitPacker.unpack(packed, 5, 6) == 1;
		boolean isRightKeyDown     = BitPacker.unpack(packed, 6, 7) == 1;
		
		return new ClientKeyInputState(
			isPrimaryKeyDown,
			isSecondaryKeyDown,
			isTertiaryKeyDown,
			isUpKeyDown,
			isDownKeyDown,
			isLeftKeyDown,
			isRightKeyDown
		);
	}
}
