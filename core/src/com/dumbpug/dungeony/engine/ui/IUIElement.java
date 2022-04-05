package com.dumbpug.dungeony.engine.ui;

import com.dumbpug.dungeony.engine.Position;

public interface IUIElement<TRenderContext> {
    /**
     * Render the element.
     * @param context The render context.
     * @param origin The origin of the element.
     */
    void render(TRenderContext context, Position origin);

    void layout();

    /**
     * Gets the width of the element.
     * @return The width of the element.
     */
    float getWidth();

    /**
     * Gets the height of the element.
     * @return The height of the element.
     */
    float getHeight();
}
