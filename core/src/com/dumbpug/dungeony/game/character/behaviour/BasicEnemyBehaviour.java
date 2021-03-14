package com.dumbpug.dungeony.game.character.behaviour;

import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.engine.utilities.GameMath;
import com.dumbpug.dungeony.engine.utilities.lotto.Lotto;
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
     * The amount of cooldown left before the subject can pick another targte position.
     */
    private long movementCooldown = 0l;
    /**
     * The time at which the current target player was targeted.
     */
    private long lastPlayerTargetTime = 0l;

    /**
     * Called just before 'tick'.
     */
    public void onBeforeTick(float delta) {
        updateMovementCooldown(delta);
    }

    /**
     * Tick the NPC behaviour.
     */
    public void onTick(float delta) {
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

            // We should try to pick a target position to walk to if we don't have one already or we are waiting for a movement cooldown.
            if (this.targetPosition == null && this.movementCooldown == 0l) {
                float angleToFollow  = 0f;
                float targetDistance = 0f;

                // Check whether the subject is actually too close to the player and should move away.
                // TODO This should eventually take the weapon type into account. E.g. we can get too close if we are using melee.
                if (distanceTo(this.targetPlayer) <= Constants.ENEMY_AI_RANGED_PLAYER_DISTANCE_MINIMUM) {
                    // If we want the subject to move away from the target player then we should take the angle from the player to the subject.
                    angleToFollow = this.targetPlayer.angleTo(subject);

                    // The subject should move a safe distance away.
                    targetDistance = Constants.ENEMY_AI_RANGED_PLAYER_DISTANCE_MINIMUM;
                } else {
                    // If we want the subject to move towards the player then we should take the angle from the subject to the player.
                    angleToFollow = angleTo(this.targetPlayer);

                    // Tweak the angle so that the enemy isn't going in an exact straight line to the player.
                    angleToFollow += (this.rng.nextFloat() * Constants.ENEMY_AI_PLAYER_TRACKING_ANGLE_RANGE) - (Constants.ENEMY_AI_PLAYER_TRACKING_ANGLE_RANGE / 2f);

                    // Work out the distance that the subject would have to move in order to reach the minimum distance allowed. This doesn't take the angle deviation into account.
                    targetDistance = distanceTo(this.targetPlayer) - Constants.ENEMY_AI_RANGED_PLAYER_DISTANCE_MINIMUM;
                }

                // Set the target position where the target is somewhere closer to the player, depending on angle deviation.
                this.targetPosition = GameMath.getPositionForAngle(subject.getOrigin().getX(), subject.getOrigin().getY(), angleToFollow, targetDistance);
            }
        }

        // We fall back to the IDLE state.
        setState(GameCharacterState.IDLE);

        // Walk towards a target position if one is defined.
        if (this.targetPosition != null) {
            // TODO Check if we are close enough to the target and remove.
            // TODO Eventually this should be worked out based on the subject movement speed and delta otherwise we might get some weird bugs.
            if (subject.distanceTo(this.targetPosition) < Constants.LEVEL_TILE_SIZE * 0.4f) {
                this.targetPosition   = null;
                this.movementCooldown = getMovementCooldown();
                return;
            }

            // Get the position of the subject before we attempt to walk in the direction of the target position.
            float initialXPosition = subject.getX();
            float initialYPosition = subject.getY();

            // Attempt to walk towards the target position.
            walkTowards(this.targetPosition);

            boolean isXMovementNegligible = Math.abs(subject.getX() - initialXPosition) < (Constants.ENEMY_AI_PLAYER_TRACKING_NEGLIGIBLE_MOVEMENT_PS * delta);
            boolean isYMovementNegligible = Math.abs(subject.getY() - initialYPosition) < (Constants.ENEMY_AI_PLAYER_TRACKING_NEGLIGIBLE_MOVEMENT_PS * delta);

            // If we tried to walk towards the target position but we didn't actually move, or our movement was negligible, then we can assume we are stuck and should give up on reaching the target.
            if (isXMovementNegligible && isYMovementNegligible) {
                this.targetPosition   = null;
                this.movementCooldown = getMovementCooldown();
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

    /**
     * Update the movement cooldown.
     */
    private void updateMovementCooldown(float delta) {
        this.movementCooldown = Math.max(this.movementCooldown - (long)(delta * 1000), 0l);
    }

    /**
     * Gets a random movement cooldown.
     * @return A random movement cooldown.
     */
    private long getMovementCooldown() {
        return new Lotto<Long>(this.rng)
                .add(new Long(2000), 10)
                .add(new Long(2000), 5)
                .add(new Long(2000), 3)
                .add(new Long(2000), 2)
                .draw();
    }
}
