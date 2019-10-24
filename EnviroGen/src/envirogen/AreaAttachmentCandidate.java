package envirogen;

import java.util.ArrayList;
import envirogen.area.AreaBlueprint;

/**
 * Represents an empty environment position at which an area can be attached to an already attached area.
 */
public class AreaAttachmentCandidate {
	/**
	 * The position at which the area would be attached to the environment.
	 */
	private Position position;
	/**
	 * The anchors representing existing areas surrounding the position which must be attachable to a valid candidate.
	 */
	private ArrayList<AreaAttachmentAnchor> anchors;
	
	/**
	 * Creates a new instance of the AreaAttachmentCandidate class.
	 * @param position The position at which the area would be attached to the environment.
	 * @param anchors The anchors representing existing areas surrounding the position which must be attachable to a valid candidate.
	 */
	public AreaAttachmentCandidate(Position position, ArrayList<AreaAttachmentAnchor> anchors) {
		this.position = position;
		this.anchors  = anchors;
	}
	
	/**
	 * Attempts to apply an area blueprint to the candidate position.
	 * A candidate can only be placed if it can be attached to every existing attached area surrounding it. 
	 * @param areaBlueprint The area blueprint.
	 * @return The attached area is the application was a success, or null if it wasn't.
	 */
	public AttachedArea applyAreaBlueprint(AreaBlueprint areaBlueprint) {
		
		
		// We were not able to attach the specified area blueprint at the candidate position.
		return null;
	}
}
