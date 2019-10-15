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
	// The 'in' object will always represent tiles on the side attached inwards (to an area closer to the initial area) to another area and its tiles should ALWAYS be walkable.
	// For example, this example area MUST be attached below an area with a transitional tile in the same X position on the bottom row of tiles.
	// An area MUST have an 'in' object defined, otherwise it simply cannot be attached to an inward area.
	//
	// The 'out' array defines all other transitional tiles that outward areas can attach to.
	// They CANNOT be on the inward edge.
	// It is preferable to have 'out' tiles as non-walkable tiles that are cleared and replaced with walkable tiles whenever an outwards area is attached.
	// Any 'out' tiles can still reference already walkable tiles, but it makes it harder to generate an x/y grid of areas when you have more potential for un-closable edges.
	"transitional": {
		"in": {
			"edge": "TOP",
			"tiles": [
				{
					"x": 1,
					"y": 0
				}
			]
		},
		"out": [
			{
				"x": 2,
				"y": 1,
				"edge": "RIGHT",
				"cleared": { 
					"type": "GRASS",
					"variation": 2 
				}
			}
		]
	},

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