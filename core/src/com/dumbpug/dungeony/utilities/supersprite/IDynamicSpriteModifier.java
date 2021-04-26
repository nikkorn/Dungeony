package com.dumbpug.dungeony.utilities.supersprite;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface IDynamicSpriteModifier {
    /**
     * Update the modifier.
     * @param delta The application delta time.
     */
    void update(float delta);

    /**
     * Apply the modifier to the specified sprite.
     * @param sprite The sprite to apply the modifier to.
     */
    void apply(Sprite sprite);

    /**
     * Gets whether the modifier has completed any sprite modifications.
     * @return Whether the modifier has completed any sprite modifications.
     */
    boolean isComplete();
}
