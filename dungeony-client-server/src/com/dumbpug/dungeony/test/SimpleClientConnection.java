package com.dumbpug.dungeony.test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import com.dumbpug.dungeony.client.Client;
import com.dumbpug.dungeony.client.ClientFactory;
import com.dumbpug.dungeony.client.ServerJoinRequestRejectedException;
import com.dumbpug.dungeony.input.ClientKeyInputState;

/**
 * A simple client connection test.
 */
public class SimpleClientConnection {
	
	public static void main(String[] args) {
		try {
			Client client = ClientFactory.create("localhost", 23344, "Nikolas");
			
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setSize(100, 100);
			
			// Add a way to track user keybaord input.
			final ClientKeyInputState clientKeyInputState = new ClientKeyInputState();
			final ArrayList<Character> typedCharacters = new ArrayList<Character>();
			frame.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					typedCharacters.add(e.getKeyChar());
				}

				@Override
				public void keyPressed(KeyEvent e) {
					processKeyEvent(e.getKeyChar(), true);
				}

				@Override
				public void keyReleased(KeyEvent e) {
					processKeyEvent(e.getKeyChar(), false);
				}
				
				private void processKeyEvent(char key, boolean isPressed) {
					switch (key) {
						case 'w':
							clientKeyInputState.setUpKeyDown(isPressed);
							break;
						case 'a':
							clientKeyInputState.setLeftKeyDown(isPressed);
							break;
						case 's':
							clientKeyInputState.setDownKeyDown(isPressed);
							break;
						case 'd':
							clientKeyInputState.setRightKeyDown(isPressed);
							break;
					}
				}
		    });
			
			// Simulate a client loop.
			while (true) {
				// Sleep for 16 millis to get close to actual client tick rate.
				try {
					Thread.sleep(16);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				// Refresh the client and process any messages sent by the server.
				client.refresh();
				
				// Determine whether the client is currently taking part in an active session on the server.
				if (client.isInSession()) {
					// Update the clients key input state.
					client.updateKeyInputState(false, false, false, clientKeyInputState.isUpKeyDown(), clientKeyInputState.isDownKeyDown(), clientKeyInputState.isLeftKeyDown(), clientKeyInputState.isRightKeyDown());
					
					// ...
					
					// TODO Get a fresh snapshot of the SESSION state and draw it somewhere.
				} else {
					// TODO client.setSlotColour(Colour.RED);
					
					// TODO client.setReady(true);
					
					// TODO Get a fresh snapshot of the LOBBY state and draw it somewhere.
				}
				
				// Clear the recorder user keys.
				typedCharacters.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServerJoinRequestRejectedException e) {
			e.printStackTrace();
		}
	}
}
