package com.dumbpug.dungeony.engine.ui;

import com.dumbpug.dungeony.engine.Position;
import java.util.ArrayList;

/**
 * A container element that contains other elements.
 */
public final class UIContainer<TRenderContext> implements IUIElement<TRenderContext> {
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
     * The current dimensions for the element.
     */
    private UIElementDimensions dimensions = null;

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
        // Firstly, if there are no elements in this container then it will have a width/height of 0/0.
        if (this.elements.isEmpty()) {
            this.dimensions = new UIElementDimensions(0,0);
            return;
        }

        // Secondly, we should give every child element a chance to do a layout.
        for (IUIElement element : this.elements) {
            element.layout();
        }

        float width = 0;
        float height = 0;

        // Lastly, we need to get the width/height of this container based on the dimensions of each child element and the container layout.
        if (this.layout == UIElementLayout.HORIZONTAL) {
            for (IUIElement element : this.elements) {
                // The sum of the widths of all elements will define the width of this container.
                width += element.getWidth();

                // The greatest height of any element will define the height of this container.
                if (element.getHeight() > height) {
                    height = element.getHeight();
                }
            }
        } else {
            for (IUIElement element : this.elements) {
                // The sum of the heights of all elements will define the height of this container.
                height += element.getHeight();

                // The greatest width of any element will define the width of this container.
                if (element.getWidth() > width) {
                    width = element.getWidth();
                }
            }
        }

        this.dimensions = new UIElementDimensions(width, height);
    }

    @Override
    public float getWidth() {
        if (this.dimensions == null) {
            throw new RuntimeException("Need to call 'layout' on this container before width can be determined");
        }
        return this.dimensions.getWidth();
    }

    @Override
    public float getHeight() {
        if (this.dimensions == null) {
            throw new RuntimeException("Need to call 'layout' on this container before height can be determined");
        }
        return this.dimensions.getHeight();
    }
}
