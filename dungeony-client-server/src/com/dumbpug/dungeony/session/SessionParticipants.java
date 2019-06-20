package com.dumbpug.dungeony.session;

import java.util.ArrayList;

/**
 * Represents a collection of session participants.
 */
public class SessionParticipants {
	/**
	 * The list of participants.
	 */
	private ArrayList<SessionParticipant> participants;
	
	/**
	 * Creates a new instance of the SessionParticipants class.
	 * @param participants The list of participants.
	 */
	public SessionParticipants(ArrayList<SessionParticipant> participants) {
		this.participants = participants;
	}
	
	/**
	 * Gets the participant with the given client id.
	 * @param id The client id.
	 * @return The participant with the given client id.
	 */
	public SessionParticipant getParticipant(int id) {
		for (SessionParticipant participant : this.participants) {
			if (participant.getId() == id) {
				return participant;
			}
		}
		throw new RuntimeException("could not find participant with id of '" + id + "'");
	}
	
	/**
	 * Gets the list of all session participants.
	 * @return The list of all session participants.
	 */
	public ArrayList<SessionParticipant> getAll() {
		return this.participants;
	}
}
