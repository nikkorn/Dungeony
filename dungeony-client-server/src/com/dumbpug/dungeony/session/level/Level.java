package com.dumbpug.dungeony.session.level;

import com.dumbpug.dungeony.session.SessionParticipant;
import com.dumbpug.dungeony.session.character.enemy.Enemies;
import com.dumbpug.dungeony.session.character.player.Player;
import com.dumbpug.dungeony.session.character.player.Players;
import com.dumbpug.dungeony.session.events.PlayerDespawnEvent;
import com.dumbpug.dungeony.session.events.PlayerSpawnEvent;
import com.dumbpug.dungeony.session.events.SessionEventQueue;
import com.dumbpug.dungeony.session.level.grid.SpatialGrid;
import com.dumbpug.dungeony.session.level.tile.Tiles;

/**
 * Represents an in-game level.
 */
public class Level {
	/**
	 * The session event queue.
	 */
	private SessionEventQueue sessionEventQueue;
	/**
	 * The collection of all tiles in the level.
	 */
	private Tiles tiles;
	/**
	 * The collection of all enemies in the level.
	 */
	private Enemies enemies;
	/**
	 * The spatial grid used to handle collisions between level entities.
	 */
	private SpatialGrid<ICollidableEntity> spatialGrid;
	/**
	 * The collection of all players in the level.
	 */
	private Players players = new Players();
	/**
	 * The depth of the level.
	 */
	private int depth;
	
	/**
	 * Creates a new instance of the Level class.
	 * @param sessionEventQueue The session event queue.
	 * @param tiles The tiles that the level is composed of.
	 * @param enemies The enemies in the level.
	 * @param spatialGrid The spatial grid used to handle collisions between level entities.
	 * @param depth The depth of the level.
	 */
	public Level(SessionEventQueue sessionEventQueue, Tiles tiles, Enemies enemies, SpatialGrid<ICollidableEntity> spatialGrid, int depth) {
		this.sessionEventQueue = sessionEventQueue;
		this.tiles             = tiles;
		this.enemies           = enemies;
		this.spatialGrid       = spatialGrid;
		this.depth             = depth;
	}
	
	/**
	 * Gets the level tiles.
	 * @return The level tiles.
	 */
	public Tiles getTiles() {
		return this.tiles;
	}
	
	/**
	 * Gets the players in the level.
	 * @return The players in the level.
	 */
	public Players getPlayers() {
		return this.players;
	}
	
	/**
	 * Gets the level depth.
	 * @return The level depth.
	 */
	public int getDepth() {
		return this.depth;
	}
	
	/**
	 * Gets whether the level is active.
	 * @return Whether the level is active.
	 */
	public boolean isActive() {
		// A level is only active if there are participating players in it.
		return this.players.any();
	}
	
	/**
	 * Tick the level.
	 */
	public void tick() {
		// Tick all of the players, processing any movements and actions in the process.
		this.players.tick(this.spatialGrid);

		// Tick all of the enemies.
		this.enemies.tick(this.spatialGrid);

		// Tick all tick-able level tiles.
		this.tiles.tick();
	}

	/**
	 * Adds a player to the level.
	 * @param participant The session participant wishing to join the level as a player.
	 */
	public void addPlayer(SessionParticipant participant) {
		// Get a safe spawn position for the player.
		Position safeSpawnPosition = getSafePlayerSpawnPosition();
		
		// Create a new player for the participant and position them at the safe spawn in the level.
		Player player = new Player(participant, safeSpawnPosition);
		
		// Add a 'PlayerSpawn' event to the session event queue.
		this.sessionEventQueue.add(new PlayerSpawnEvent(participant.getId(), this.depth, safeSpawnPosition.copy()));
		
		// Add the player to the spatial grid used to handle collisions between level entities.
		this.spatialGrid.add(player);
		
		// Add the player to the collection of players in the level.
		this.players.addPlayer(player);
	}

	/**
	 * Removes a player from the level.
	 * @param participant The session participant wishing to leave the level as a player.
	 */
	public void removePlayer(SessionParticipant participant) {
		// Get the player in the level fro the given participant.
		Player player = this.players.getPlayerForParticipant(participant);
		
		// Add a 'PlayerDespawn' event to the session event queue.
		this.sessionEventQueue.add(new PlayerDespawnEvent(participant.getId(), this.depth));
		
		// Remove the player from the spatial grid used to handle collisions between level entities.
		this.spatialGrid.remove(player);
		
		// Remove the player from the collection of players in the level.
		this.players.removePlayer(player);
	}
	
	/**
	 * Gets a safe player spawn position.
	 * @return A safe player spawn position.
	 */
	private Position getSafePlayerSpawnPosition() {
		// TODO Hunt down the player spawn tile entites and return the position of the first
		// that is not colliding with anything. If all of them are colliding with something
		// then we will have to move outwards until we can find the closest tile that is free.
		return new Position(100, 100);
	}
}