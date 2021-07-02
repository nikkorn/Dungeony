package com.dumbpug.dungeony.game.dialogs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.dialog.Dialog;
import com.dumbpug.dungeony.engine.dialog.DialogMargin;
import com.dumbpug.dungeony.engine.dialog.DialogPosition;
import com.dumbpug.dungeony.game.object.objects.Vendor;

/**
 * A vendor dialog, displaying items for sale.
 */
public class VendorDialog extends Dialog<SpriteBatch> {
    /**
     * Creates a new instance of the VendorDialog class.
     * @param vendor The vendor entity.
     */
    public VendorDialog(Vendor vendor) {
        super(vendor, DialogPosition.ABOVE, DialogMargin.MEDIUM);
    }

    /**
     * Render the dialog.
     * @param context The render context.
     * @param originX The X origin position of the dialog.
     * @param originY The Y origin position of the dialog.
     */
    @Override
    public void render(SpriteBatch context, float originX, float originY) {
        // TODO
    }


    /**
     * Gets the width of the dialog.
     * @return The width of the dialog.
     */
    @Override
    public float getWidth() {
        return 10f;
    }

    /**
     * Gets the height of the dialog.
     * @return The height of the dialog.
     */
    @Override
    public float getHeight() {
        return 10f;
    }
}
