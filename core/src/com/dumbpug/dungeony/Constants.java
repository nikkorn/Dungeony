package com.dumbpug.dungeony;

/**
 * Application constants.
 */
public class Constants {
    //==============================================================================================
    // APPLICATION
    //==============================================================================================
    public static final boolean APPLICATION_PRINT_FPS                            = true;

    //==============================================================================================
    // FONT
    //==============================================================================================
    public static final String FONT_TYPE_MAIN                                    = "nokiafc22.ttf";
    public static final int FONT_SIZE_STANDARD                                   = 8;
    public static final int FONT_SIZE_LARGE                                      = 16;

    //==============================================================================================
    // DISPLAY
    //==============================================================================================
    public static final int WINDOW_HEIGHT 	                                     = 1000;
    public static final int WINDOW_WIDTH	                                     = 1200;

    //==============================================================================================
    // AUDIO
    //==============================================================================================
    public static final float AUDIO_SOUND_EFFECT_VOLUME	                         = 0.8f;
    public static final float AUDIO_MUSIC_VOLUME	                             = 0.1f;

    //==============================================================================================
    // INPUT
    //==============================================================================================
    public static final float INPUT_CONTROLLER_AXIS_DEADZONE 	                 = 0.1f;

    //==============================================================================================
    // SPLASH
    //==============================================================================================
    public static final long SPLASH_DURATION 	                                 = 500l;

    //==============================================================================================
    // LEVEL
    //==============================================================================================
    public static final float LEVEL_TILE_SIZE                                    = 32f;
    public static final float LEVEL_GRID_CELL_SIZE                               = LEVEL_TILE_SIZE * 4f;
    public static final float LEVEL_DEFAULT_ZOOM                                 = 0.25f; // 0.18f;
    public static final float LEVEL_CAMERA_SHAKE_POWER                           = 1;

    //==============================================================================================
    // HUD
    //==============================================================================================
    public static final int HUD_VIEWPORT_HEIGHT                                  = 520;
    public static final float HUD_PANEL_MARGIN                                   = 3f;

    //==============================================================================================
    // CHARACTER
    //==============================================================================================
    public static final int CHARACTER_DEFAULT_HEALTH_SLOTS                       = 5;
    public static final int CHARACTER_INVENTORY_SLOTS                            = 8;
    public static final int CHARACTER_INVENTORY_MAX_STACK_SIZE                   = 40;
    public static final long CHARACTER_DAMAGE_OVERLAY_DURATION_MS                = 100l;

    //==============================================================================================
    // NPC
    //==============================================================================================
    public static final float NPC_DEFAULT_MAX_VISIBILITY                         = 100f;
    public static final float NPC_SHORT_MAX_VISIBILITY                           = NPC_DEFAULT_MAX_VISIBILITY * 0.5f;
    public static final float NPC_FAR_MAX_VISIBILITY                             = NPC_DEFAULT_MAX_VISIBILITY * 2f;

    //==============================================================================================
    // ENEMY
    //==============================================================================================
    // The cooldown between an enemy selecting a player to target and selecting another.
    public static final long ENEMY_AI_PLAYER_FOCUS_SWITCH_COOLDOWN               = 1000l;

    // The amount of time that needs to pass after an enemy selects a player target before rechecking for nearest player or giving up following.
    public static final long ENEMY_AI_PLAYER_TARGET_DURATION                     = 2000l;

    // The minimum distance that a melee enemy should try to get to a player.
    public static final float ENEMY_AI_MELEE_PLAYER_DISTANCE_MINIMUM             = LEVEL_TILE_SIZE * 0.2f;

    // The minimum distance that a ranged enemy should try to get to a player.
    public static final float ENEMY_AI_RANGED_PLAYER_DISTANCE_MINIMUM            = LEVEL_TILE_SIZE * 1.2f;
    public static final float ENEMY_AI_RANGED_PLAYER_DISTANCE_MAXIMUM            = LEVEL_TILE_SIZE * 3f;

    // The minimum distance that a long ranged enemy should try to get to a player.
    public static final float ENEMY_AI_LONG_RANGED_PLAYER_DISTANCE_MINIMUM       = LEVEL_TILE_SIZE * 4f;

    // The angle range deviation that can be applied when setting a target position.
    public static final float ENEMY_AI_PLAYER_TRACKING_ANGLE_RANGE               = 80f;

    // The amount of movement made by an enemy that we can deem to be negligible.
    public static final float ENEMY_AI_PLAYER_TRACKING_NEGLIGIBLE_MOVEMENT_PS    = LEVEL_TILE_SIZE * 0.3f;

    //==============================================================================================
    // PLAYER
    //==============================================================================================
    public static final float PLAYER_MOVEMENT_PS                                 = LEVEL_TILE_SIZE * 2.6f;
    public static final float PLAYER_SIZE                                        = LEVEL_TILE_SIZE * 0.5f;

    //==============================================================================================
    // PICKUPS
    //==============================================================================================
    public static final float PICKUP_SIZE = 32f;

    //==============================================================================================
    // PROJECTILES
    //==============================================================================================
    public static final float PROJECTILE_VERY_SLOW_MOVEMENT_PS                    = LEVEL_TILE_SIZE * 1.4f;
    public static final float PROJECTILE_SLOW_MOVEMENT_PS                         = LEVEL_TILE_SIZE * 2f;
    public static final float PROJECTILE_DEFAULT_MOVEMENT_PS                      = LEVEL_TILE_SIZE * 3f;
    public static final float PROJECTILE_FAST_MOVEMENT_PS                         = LEVEL_TILE_SIZE * 3.6f;
    public static final float PROJECTILE_VERY_FAST_MOVEMENT_PS                    = LEVEL_TILE_SIZE * 4.6f;
    public static final float PROJECTILE_SIZE_SMALL                               = 4f;
    public static final float PROJECTILE_SIZE_MEDIUM                              = 6f;
    public static final float PROJECTILE_SIZE_LARGE                               = 10f;

    //==============================================================================================
    // WEAPONS
    //==============================================================================================
    public static final long WEAPON_MUZZLE_FLASH_DURATION_MS                      = 150l;
    public static final long WEAPON_RANGE_PISTOL                                  = 1000l;
    public static final long WEAPON_RANGE_SMALL_RIFLE                             = 1000l;
    public static final long WEAPON_RANGE_RIFLE                                   = 1500l;
    public static final long WEAPON_RANGE_SHOTGUN                                 = 300l;
    public static final long WEAPON_RANGE_SNIPER                                  = 5000l;
    public static final long WEAPON_RANGE_MEELE                                   = 50l;
}
