package com.dumbpug.dungeony.test;

import java.io.IOException;
import com.dumbpug.dungeony.client.Client;
import com.dumbpug.dungeony.client.ServerJoinRequestRejectedException;

/**
 * A simple client connection test.
 */
public class SimpleClientConnection {
	
	public static void main(String[] args) {
		try {
			Client client = Client.connect("localhost", 23344, "Nikolas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServerJoinRequestRejectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
