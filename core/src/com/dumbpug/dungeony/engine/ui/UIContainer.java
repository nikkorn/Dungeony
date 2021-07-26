package com.dumbpug.dungeony.engine.ui;

import java.util.ArrayList;

/**
 * A container element that contains other elements.
 */
public class UIContainer implements IUIElement {
    /**
     * The layout of elements within this container.
     */
    private UIElementLayout layout;
    /**
     * The alignment of elements within this container.
     */
    private UIElementAlignment alignment;
    /**
     * The list of elements within this container.
     */
    private ArrayList<IUIElement> elements = new ArrayList<IUIElement>();

    /**
     * Creates a new instance of the UIContainer class.
     * @param layout
     * @param alignment
     */
    public UIContainer(UIElementLayout layout, UIElementAlignment alignment) {
        this.layout    = layout;
        this.alignment = alignment;
    }

    /**
     * Add the specified element to the container.
     * @param element
     */
    public void addElement(IUIElement element) {
        // We should avoid duplicate elements.
        if (this.elements.contains(element))
            return;

        this.elements.add(element);
    }

    @Override
    public void render() {

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
