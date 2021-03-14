package com.dumbpug.dungeony.game.character.behaviour;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.engine.utilities.GameMath;
import com.dumbpug.dungeony.game.character.GameCharacterState;
import com.dumbpug.dungeony.game.character.npc.NPC;
import com.dumbpug.dungeony.game.character.player.Player;

/**
 * Very basic enemy NPC behaviour.
 * The enemy will attempt to get close the closest enemy player and stop withing range, firing intermittently.
 * @param <TNPC> The type of NPC.
 */
public class BasicEnemyBehaviour<TNPC extends NPC> extends NPCBehaviour<TNPC> {
    /**
     * The current target player.
     */
    private Player targetPlayer = null;
    /**
     * The current target position.
     */
    private Position targetPosition = null;
    /**
     * The time at which the current target player was targeted.
     */
    private long lastPlayerTargetTime = 0l;

    /**
     * Tick the NPC behaviour.
     */
    public void onTick() {
        // There is nothing to do if the enemy is dead.
        if (inState(GameCharacterState.DEAD)) {
            return;
        }

        // Do nothing if the subject is sleeping.
        if (inState(GameCharacterState.SLEEPING)) {
            return;
        }

        // Try to find a target player entity.
        findTargetPlayer();

        // Reset the enemy angle of view.
        setAngleOfView(null);

        // Check whether we have a target player entity.
        if (this.targetPlayer != null) {
            // Can the subject actually see the closest player?
            if (canSee(this.targetPlayer)) {
                // Aim at the player.
                setAngleOfView(angleTo(this.targetPlayer));

                // Attack the player using our weapon if we can.
                if (canAttack(this.targetPlayer)) {
                    attack();
                }
            }

            // If we are too far away from the player then we should try to get closer.
            // If we are too close then maybe we want to move away.
            // If we already have a target position then we don't really want to do anything.
            // TODO Make this be based on weapon type.
            if (this.targetPosition != null) {
                // We are currently moving towards a target position so there is no reason to set another one for now.
            } else if (distanceTo(this.targetPlayer) > Constants.ENEMY_AI_RANGED_PLAYER_DISTANCE_MAXIMUM) {
                // Get the angle to follow towards the target player.
                float angleToFollow = angleTo(this.targetPlayer);

                // Tweak the angle so that the enemy isn't going in an exact straight line to the player.
                angleToFollow += (this.rng.nextFloat() * Constants.ENEMY_AI_PLAYER_TRACKING_ANGLE_RANGE) - (Constants.ENEMY_AI_PLAYER_TRACKING_ANGLE_RANGE / 2f);

                // Work out the distance that the subject would have to move in order to reach the minimum distance allowed. This doesn't take the angle deviation into account.
                float targetDistance = distanceTo(this.targetPlayer) - Constants.ENEMY_AI_RANGED_PLAYER_DISTANCE_MINIMUM;

                // Set the target position where the target is somewhere closer to the player, depending on angle deviation.
                this.targetPosition = GameMath.getPositionForAngle(subject.getOrigin().getX(), subject.getOrigin().getY(), angleToFollow, targetDistance);
            } else if (distanceTo(this.targetPlayer) < Constants.ENEMY_AI_RANGED_PLAYER_DISTANCE_MINIMUM) {
                // TODO Actually move away for the player?
                // This should not always happen as we want the player to have a change to get in close with melee.
            }
        }

        // We fall back to the IDLE state.
        setState(GameCharacterState.IDLE);

        // Walk towards a target position if one is defined.
        if (this.targetPosition != null) {
            // TODO Check if we are close enough to the target and remove.
            // TODO Eventually this should be worked out based on the subject movement speed and delta otherwise we might get some weird bugs.
            if (subject.distanceTo(this.targetPosition) < Constants.LEVEL_TILE_SIZE * 0.4f) {
                this.targetPosition = null;
                return;
            }

            // Get the position of the subject before we attempt to walk in the direction of the target position.
            float initialXPosition = subject.getX();
            float initialYPosition = subject.getY();

            // Attempt to walk towards the target position.
            walkTowards(this.targetPosition);

            // If we tried to walk towards the target position but we didn't actually move then we can assume we are stuck and should give up on reaching the target.
            // TODO This should include some small number check as we also don't want to be slowly dragging across walls.
            if (subject.getX() == initialXPosition && subject.getY() == initialYPosition) {
                this.targetPosition = null;
            }
        }
    }

    /**
     * Find a target player.
     */
    private void findTargetPlayer() {
        // Check whether we already have a target enemy.
        if (this.targetPlayer != null) {
            // If the target player is dead then we need to look for another target.
            if (this.targetPlayer.getHealth().isHealthDepleted()) {
                // Find the next closest visible player target if there is one.
                this.targetPlayer = getClosestVisiblePlayerEntity();

                // Reset the target time value as we don't want to do this every update.
                this.lastPlayerTargetTime = System.currentTimeMillis();

                return;
            }

            // After a certain amount of time the enemy should reassess whether the current target player is still the right one to go after.
            if (System.currentTimeMillis() >= (lastPlayerTargetTime + Constants.ENEMY_AI_PLAYER_TARGET_DURATION)) {
                // If the enemy can no longer see the current target then it should look for another target
                if (!canSee(this.targetPlayer)) {
                    // Find the closest visible player target, which could very well be the current target, or null if none are available.
                    this.targetPlayer = getClosestVisiblePlayerEntity();
                }

                // Reset the target time value as we don't want to do this every update.
                this.lastPlayerTargetTime = System.currentTimeMillis();
            }
        } else {
            // Find the closest visible player target if there is one.
            this.targetPlayer = getClosestVisiblePlayerEntity();

            if (this.targetPlayer != null) {
                // Reset the target time value as we don't want to do this every update.
                this.lastPlayerTargetTime = System.currentTimeMillis();
            }
        }
    }
}
