package com.dumbpug.dungeony.engine.ui;

import java.util.ArrayList;

/**
 * Takes a bunch of elements and can set/unset focus for all of them.
 */
public class UIFocusableElementGroup {
    /**
     * The list of elements within this group.
     */
    private ArrayList<IUIElement> elements = new ArrayList<IUIElement>();

    /**
     * Add the specified element to the group.
     * @param element
     */
    public void addElement(IUIElement element) {
        // We should avoid duplicate elements.
        if (this.elements.contains(element))
            return;

        this.elements.add(element);
    }
}
