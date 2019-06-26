package com.dumbpug.dungeony.test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import com.dumbpug.dungeony.client.Client;
import com.dumbpug.dungeony.client.ServerJoinRequestRejectedException;
import com.dumbpug.dungeony.input.ClientKeyInputState;

/**
 * A simple client connection test.
 */
public class SimpleClientConnection {
	
	public static void main(String[] args) {
		try {
			Client client = Client.connect("localhost", 23344, "Nikolas");
			
			final ClientKeyInputState clientKeyInputState = new ClientKeyInputState();
			
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setSize(10, 10);
			frame.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {}

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
				
				// Update the clients key input state.
				client.updateKeyInputState(false, false, false, clientKeyInputState.isUpKeyDown(), clientKeyInputState.isDownKeyDown(), clientKeyInputState.isLeftKeyDown(), clientKeyInputState.isRightKeyDown());
				
				// TODO Get a fresh snapshot of the server state and draw it somewhere.
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServerJoinRequestRejectedException e) {
			e.printStackTrace();
		}
	}
}
