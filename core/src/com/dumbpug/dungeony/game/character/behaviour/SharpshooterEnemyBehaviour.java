package com.dumbpug.dungeony.game.character.behaviour;

import com.dumbpug.dungeony.game.character.GameCharacterState;
import com.dumbpug.dungeony.game.character.npc.NPC;

public class SharpshooterEnemyBehaviour<TNPC extends NPC> extends NPCBehaviour<TNPC>  {
    @Override
    public void onTick(float delta) {
        // There is nothing to do if the enemy is dead.
        if (inState(GameCharacterState.DEAD)) {
            return;
        }

        // Do nothing if the subject is sleeping.
        if (inState(GameCharacterState.SLEEPING)) {
            return;
        }

        // Check that the closest player isn't too close, if so then try to move away from the player to a safe distance (ENEMY_AI_LONG_RANGED_PLAYER_DISTANCE_MINIMUM)

        // If stuck and cannot move away from closest player anymore then just revert to either:
        // - Just firing the long ranged weapon.
        // - Swap to a shorter ranged weapon.

        // Fire at closest visible player at intervals.
    }
}
