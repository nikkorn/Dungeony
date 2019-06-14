package com.dumbpug.dungeony;

/**
 * Any game constants.
 */
public class Constants {
	
	// *********************************************************************
	// NETWORKING
	// *********************************************************************
	public static int DEFAULT_PORT                           = 23456;
	
	// *********************************************************************
	// LEVEL
	// *********************************************************************
	public static int LEVEL_SPATIAL_GRID_CELL_SIZE           = 100;
	public static int LEVEL_TILE_SIZE                        = 30;
	
	// *********************************************************************
	// ITEM
	// *********************************************************************
	public static int ITEM_DROPPED_SIZE                      = 15;
	
	// *********************************************************************
	// CHARACTER
	// *********************************************************************
	public static int CHARACTER_SIZE_SMALL                   = 10;
	public static int CHARACTER_SIZE_MEDIUM                  = 15;
	public static int CHARACTER_SIZE_LARGE                   = 20;
	public static int CHARACTER_SIZE_XLARGE                  = 25;
	
	public static float CHARACTER_MOVEMENT_SPEED_STATIC      = 0f;
	public static float CHARACTER_MOVEMENT_SPEED_VERY_SLOW   = 0.5f;
	public static float CHARACTER_MOVEMENT_SPEED_SLOW        = 0.75f;
	public static float CHARACTER_MOVEMENT_SPEED_MEDIUM      = 1f;
	public static float CHARACTER_MOVEMENT_SPEED_FAST        = 1.25f;
	public static float CHARACTER_MOVEMENT_SPEED_VERY_FAST   = 1.5f;
	
	public static float CHARACTER_DODGE_SPEED_MULTIPLIER     = 1.5f;
	public static float CHARACTER_FROZEN_SPEED_MULTIPLIER    = 0.5f;
}