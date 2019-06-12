package com.dumbpug.dungeony.session.character.enemy;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.session.level.Direction;
import com.dumbpug.dungeony.session.level.Position;

/**
 * Factory for creating Enemy instances.
 */
public class EnemyFactory {
	
	/**
	 * Create an Enemy instance.
	 * @param enemyType The enemy type.
	 * @param tileX The x position of the tile at which the x origin of the enemy should be.
	 * @param tileY The y position of the tile at which the y origin of the enemy should be.
	 * @param direction The initial facing direction of the enemy.
	 * @return An Enemy instance.
	 */
	public static Enemy createEnemy(EnemyType enemyType, int tileX, int tileY, Direction facingDirection) {
		Enemy enemy = null;
		
		// Create the correct enemy based on the given enemy type.
		switch (enemyType) {
			case SKELETON:
				enemy = new Skeleton(new Position());
				break;
			default:
				throw new RuntimeException("unknown enemy type: " + enemyType);
		}
		
		// Update the position of the enemy so that the origin of the enemy sits at the centre of the tile defined by tileX and tileY.
		float tileOriginX = (Constants.LEVEL_TILE_SIZE * tileX) + (Constants.LEVEL_TILE_SIZE / 2f);
		float tileOriginY = (Constants.LEVEL_TILE_SIZE * tileY) + (Constants.LEVEL_TILE_SIZE / 2f);
		enemy.getPosition().setX(tileOriginX - (enemy.getSize() / 2f));
		enemy.getPosition().setY(tileOriginY - (enemy.getSize() / 2f));
		
		// Set the angle of view for the enemy.
		enemy.setAngleOfView(facingDirection.toAngle());
		
		// Return the create enemy.
		return enemy;
	}
}
