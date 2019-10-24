package envirogen;

import envirogen.area.AreaEdge;

/**
 * Represents an existing attached area to which another area could potentially attach to on a specific edge.
 */
public class AreaAttachmentAnchor {
	/**
	 * The existing attached area to which a candidate musyt attach to.
	 */
	private AttachedArea attachedArea;
	/**
	 * The edge of the attached area to which another area can be attached.
	 */
	private AreaEdge attachmentEdge;
}
