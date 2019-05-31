package com.dumbpug.dungeony;

import com.dumbpug.dungeony.session.level.Position;

/**
 * Holder of game utility methods.
 */
public class Utility {
	
	/**
     * Get the position at angle and distance from an origin.
     * @param origin The origin position.
     * @param angle The angle of movement.
     * @param distance The distance moved.
     */
    public static Position getTargetPosition(Position origin, double angle, double distance) {
        double newX = Math.cos(angle * Math.PI / 180) * distance + origin.getX();
        double newY = Math.sin(angle * Math.PI / 180) * distance + origin.getY();
        return new Position((float)newX, (float)newY);
    }
}
