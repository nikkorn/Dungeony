// The following is an example definition for a 9*9 area.

const definition = {
	// The optional 'max' property defines the maximum number of times that ths area can be generated.
	"max": 4,

	// The optional 'min' property defines the minimum number of times that ths area can be generated.
	"min": 1,

	// The optional 'chance' property defines a random chance that the are will be included at all, regardless of min or max.
	"chance": 0.5,

	// The optional 'depth' object defined an optional min/max depth at which the area can be generated from the initial area.
	"depth": {
		"min": 4,
		"max": 6
	},
	
	// The 'transitional' object defines the tiles around the edge of the area that can touch transitional tiles in an adjoining area.
	//
	// It is preferable to have tiles as non-walkable tiles that are cleared and replaced with walkable tiles whenever an outwards area is attached.
	// Any tiles can still reference already walkable tiles, but it makes it harder to generate an x/y grid of areas when you have more potential for un-closable edges.
	"transitional": {
		"top": [
			{
				// Position is 'x' when TOP/BOTTOM.
				"position": 1
			}
		],
		"bottom": [],
		"left": [],
		"right": [
			{
				// Position is 'y' when RIGHT/LEFT.
				"position": 1,
				"cleared": { 
					"type": "GRASS",
					"variation": 2 
				}
			}
		]
	},
	
	// We can optionally define a whole IN edge if we require an area to be entered from a particular direction, otherwise it can be attached on any side as long as transitional tiles match up.
	"entry": "TOP",

	// The 'tiles' array represent ALL tiles within an area, regardless of whether they are transitional.
	// Each has a unique x/y position within the area as well as a required type and optional variation (defaults to 0).
	// An entity can be optionally defined for a tile, this can be a chest, enemy, marker etc.
	"tiles": [
		{
			"x": 0,
			"y": 0,
			"type": "TREE",
			"variation": 2
		},
		{
			"x": 1,
			"y": 0,
			"type": "GRASS",
			"variation": 2
		},
		{
			"x": 2,
			"y": 0,
			"type": "TREE",
			"variation": 2
		},
		{
			"x": 0,
			"y": 1,
			"type": "TREE",
			"variation": 2
		},
		{
			"x": 1,
			"y": 1,
			"type": "GRASS",
			"variation": 2,
			"entity": { 
				"id": "CHEST"
			}
		},
		{
			"x": 2,
			"y": 1,
			"type": "TREE",
			"variation": 2
		},
		{
			"x": 0,
			"y": 2,
			"type": "TREE",
			"variation": 2
		},
		{
			"x": 1,
			"y": 2,
			"type": "TREE",
			"variation": 2
		},
		{
			"x": 2,
			"y": 2,
			"type": "TREE",
			"variation": 2
		}
	]
};