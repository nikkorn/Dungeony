package com.dumbpug.dungeony.game.character.ability;

/**
 * Enumeration of character ability types.
 */
public enum CharacterAbility {
    RUBBER_SKIN,          // Projectiles bounce off of the character for a short duration.
    INVISIBILITY,         // Character sprites are translucent and enemies have a harder time detecting.
    TRAVELLER_SHIELD,     // Short duration 360 shield. Replaces Dodge.
    MAGE_SHIELD,          // Medium duration 360 shield. Replaces Dodge.
    ARCHMAGE_SHIELD,      // Long duration 360 shield that applies small damage to intersecting enemies. Replaces Dodge.
    LUCKY_DEVIL,          // Better loot drops.
    THORNY_BACK,          // A dodge roll that applies damage to any enemies that we roll into. Replaces Dodge.
    SENTRY,               // Spawns an auto-turret that fires at enemies for a short amount of time.
    IMP,                  // Spawns a small character that follows the player and holds their bag to allow better movement for the character, will hang around until killed.
    MARKSMAN_IMP,         // Spawns a small character that follows the player and shoots at enemies, will hang around until killed.
    JUMPSTART,            // On death the character loses aggro an can press a button to get back up again with part health.
    MAGIC_BOX,            // Infinite ammo.
    STONE_SKIN,           // Take less damage. Cannot Dodge.
    TOUGH_CUSTOMER,       // Lower cost of items from characters and upgrades.
    UNHEALTHY_INTEREST,   // Small chance of regaining some health after killing an enemy.
    TRAP_MASTER,          // Doesn't trigger traps.
    LIGHT_FOOTED,         // Slightly faster movement speed, less encumbered by carry weight.
    HEAVY_HITTER,         // Slight increase to weapon damage output.
    TINKERER              // Require less resources for upgrades.
}
