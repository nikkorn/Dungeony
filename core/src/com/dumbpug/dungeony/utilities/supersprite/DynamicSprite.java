package com.dumbpug.dungeony.utilities.supersprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A wrapper for a sprite that can be squeezed.
 */
public class DynamicSprite {
    /**
     * The wrapped sprite.
     */
    private Sprite sprite;
    /**
     * The sprite modifiers.
     */
    private SqueezeDynamicSpriteModifier squeeze = null;
    private HoverDynamicSpriteModifier hover     = null;
    private ResizeDynamicSpriteModifier resize   = null;

    /**
     * Creates a new instance of the SqueezySprite class.
     * @param sprite The wrapped sprite.
     */
    public DynamicSprite(Sprite sprite) {
        this.sprite = new Sprite(sprite.getTexture());
    }

    public void setPosition(float x, float y) {
        this.sprite.setPosition(x, y);
    }

    public void setSize(float width, float height) {
        this.sprite.setSize(width, height);
    }

    public void apply(SqueezeDynamicSpriteModifier squeeze) {
        this.squeeze = squeeze;
    }

    public void apply(HoverDynamicSpriteModifier hover) {
        this.hover = hover;
    }

    public void apply(ResizeDynamicSpriteModifier resize) {
        this.resize = resize;
    }

    public void update(float delta) {
        // Process the squeeze modifier.
        if (this.squeeze != null) {
            this.squeeze.update(delta);

            if (this.squeeze.isComplete()) {
                this.squeeze = null;
                sprite.setScale(1, 1);
            }
        }

        // Process the hover modifier.
        if (this.hover != null) {
            this.hover.update(delta);

            if (this.hover.isComplete()) {
                this.hover = null;
            }
        }

        // Process the resize modifier.
        if (this.resize != null) {
            this.resize.update(delta);

            if (this.resize.isComplete()) {
                this.resize = null;
            }
        }
    }

    /**
     * Draw the sprite.
     * @param batch The sprite batch.
     */
    public void draw(SpriteBatch batch) {
        float originalXPosition = this.sprite.getX();
        float originalYPosition = this.sprite.getY();
        float originalWidth     = this.sprite.getWidth();
        float originalHeight    = this.sprite.getHeight();

        // Apply the squeeze modifier.
        if (this.squeeze != null) {
            this.squeeze.apply(this.sprite);
        }

        // Apply the hover modifier.
        if (this.hover != null) {
            this.hover.apply(this.sprite);
        }

        // Apply the resize modifier.
        if (this.resize != null) {
            this.resize.apply(this.sprite);
        }

        this.sprite.draw(batch);

        this.sprite.setPosition(originalXPosition, originalYPosition);
        this.sprite.setSize(originalWidth, originalHeight);
    }
}
