package com.dumbpug.dungeony.engine.dialog;

import com.dumbpug.dungeony.engine.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The collection of environment dialogs.
 */
public class Dialogs<TRenderContext> {
    /**
     * The collection of dialogs.
     */
    private ArrayList<Dialog<TRenderContext>> dialogs = new ArrayList<Dialog<TRenderContext>>();
    /**
     * The comparator to use in sorting the dialogs.
     */
    private static Comparator<Dialog> dialogOrderComparator;

    static {
        dialogOrderComparator = new Comparator<Dialog>() {
            @Override
            public int compare(Dialog first, Dialog second) {
                // We cannot sort two dialogs if either of them do not have a target entity.
                if (first.getInteractingEntity() == null || second.getInteractingEntity() == null)
                {
                    return 0;
                }

                float difference = first.getInteractingEntity().getRenderOrder() - second.getInteractingEntity().getRenderOrder();

                if (difference < 0) {
                    return -1;
                } else if (difference > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }

    /**
     * Add a dialog to the collection of environment dialogs.
     * @param dialog The dialog to add.
     */
    public void add(Dialog dialog) {
        if (!this.dialogs.contains(dialog)) {
            this.dialogs.add(dialog);
        }
    }

    /**
     * Remove a dialog from the collection of environment dialogs.
     * @param dialog The dialog to remove.
     */
    public void remove(Dialog dialog) {
        this.dialogs.remove(dialog);
    }

    /**
     * Gets a list of active dialogs for the given entity.
     * @param entity The entity.
     * @return A list of active dialogs for the given entity.
     */
    public ArrayList<Dialog> getDialogsWithInteractingEntity(Entity entity) {
        ArrayList<Dialog> activeDialogs = new ArrayList<Dialog>();

        for (Dialog dialog : this.dialogs) {
            if (dialog.getInteractingEntity() == entity) {
                activeDialogs.add(dialog);
            }
        }

        return activeDialogs;
    }

    /**
     * Render the collection of environment dialogs.
     * @param context The render context
     */
    public void render(TRenderContext context) {
        // Sort the dialogs based on the rendering order of the target entity of each dialog.
        Collections.sort(this.dialogs, Dialogs.dialogOrderComparator);

        // Render each dialog.
        for (Dialog dialog : this.dialogs) {
            // Skip rendering a dialog if it has no interacting entity.
            if (dialog.getInteractingEntity() == null) {
                continue;
            }

            // TODO Work out the dialog x/y origin basted on the target entity position and dialog offset.
            float offsetX = dialog.getInteractingEntity().getOrigin().getX();
            float offsetY = dialog.getInteractingEntity().getOrigin().getY();

            // Render the dialog.
            dialog.render(context, offsetX, offsetY);
        }
    }
}
