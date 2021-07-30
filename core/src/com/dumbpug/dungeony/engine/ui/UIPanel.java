package com.dumbpug.dungeony.engine.ui;

import com.dumbpug.dungeony.engine.Position;

public class UIPanel<TRenderContext> implements IUIElement<TRenderContext> {
    /**
     * Render the element.
     * @param context The render context.
     * @param origin The origin of the element.
     */
    @Override
    public void render(TRenderContext context, Position origin) {

    }

    @Override
    public void layout() {

    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }
}
