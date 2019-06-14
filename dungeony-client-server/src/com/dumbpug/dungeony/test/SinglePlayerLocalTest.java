package com.dumbpug.dungeony.test;

import com.dumbpug.dungeony.client.LocalSession;
import com.dumbpug.dungeony.client.SessionFactory;

/**
 * Simple single-player local test.
 */
public class SinglePlayerLocalTest {
	
	public static void main(String[] args) {
		// Create a local session instance that does not wrap a server.
		LocalSession session = SessionFactory.createLocalSession("Nikolas");
	}
}
