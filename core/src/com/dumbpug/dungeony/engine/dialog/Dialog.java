package com.dumbpug.dungeony.engine.dialog;

import com.dumbpug.dungeony.engine.Entity;

/**
 * An environment dialog.
 * @param <TRenderContext> The render context.
 */
public abstract class Dialog<TRenderContext> {
    /**
     * The dialog target entity, the entity that provides the dialog and represents a relative position.
     */
    private Entity targetEntity;
    /**
     * The dialog position, relative to the target entity.
     */
    private DialogPosition position;
    /**
     * The margin between the dialog and the target entity.
     */
    private DialogMargin margin;
    /**
     * The dialog interacting entity.
     */
    private Entity interactingEntity = null;

    /**
     * Creates a new instance of the Dialog class.
     * @param entity The dialog target entity.
     * @param position The dialog position, relative to the target entity.
     * @param margin The margin between the dialog and the target entity.
     */
    public Dialog(Entity entity, DialogPosition position, DialogMargin margin) {
        this.targetEntity = entity;
        this.position     = position;
        this.margin       = margin;
    }

    /**
     * Gets the target entity of the dialog.
     * @return The target entity of the dialog.
     */
    public Entity getTargetEntity() {
        return targetEntity;
    }

    /**
     * Gets the dialog interacting entity.
     * @return The dialog interacting entity.
     */
    public Entity getInteractingEntity() {
        return interactingEntity;
    }

    /**
     * Sets the dialog interacting entity.
     * @param interactingEntity The dialog interacting entity.
     */
    public void setInteractingEntity(Entity interactingEntity) {
        this.interactingEntity = interactingEntity;
    }

    /**
     * Render the dialog.
     * @param context The render context.
     * @param originX The X origin position of the dialog.
     * @param originY The Y origin position of the dialog.
     */
    public abstract void render(TRenderContext context, float originX, float originY);

    /**
     * Gets whether the dialog should be shown.
     * @return Whether the dialog should be shown.
     */
    public abstract boolean isShown();

    /**
     * Called when the dialog is shown.
     */
    public abstract void onShow();

    /**
     * Called when the dialog is hidden.
     */
    public abstract void onHide();

    /**
     * Gets the width of the dialog.
     * @return The width of the dialog.
     */
    public abstract float getWidth();

    /**
     * Gets the height of the dialog.
     * @return The height of the dialog.
     */
    public abstract float getHeight();
}
