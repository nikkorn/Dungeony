package com.dumbpug.dungeony.game.character.behaviour;

import com.dumbpug.dungeony.Constants;
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

            // Walk towards the player if we aren't already too close, otherwise just be idle.
            // TODO Make this be based on weapon type.
            if (distanceTo(this.targetPlayer) > Constants.ENEMY_AI_RANGED_PLAYER_DISTANCE_MINIMUM) {
                walkTowards(this.targetPlayer);
            } else {
                setState(GameCharacterState.IDLE);
            }

            return;
        }

        // We fall back to the IDLE state.
        setState(GameCharacterState.IDLE);
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
