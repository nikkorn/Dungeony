package com.dumbpug.dungeony.server;

import com.dumbpug.dungeony.session.ISessionParticipant;
import com.dumbpug.dungeony.session.input.IPlayerInputState;

/**
 * A local client not connected over the network.
 */
public class LocalClient implements ISessionParticipant {

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPlayerInputState getPlayerInputState() {
		// TODO Auto-generated method stub
		return null;
	}
}
