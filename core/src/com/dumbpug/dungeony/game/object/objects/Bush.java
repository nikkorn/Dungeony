package com.dumbpug.dungeony.game.object.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.game.object.GameObject;
import com.dumbpug.dungeony.game.object.GameObjectType;
import com.dumbpug.dungeony.game.rendering.GameObjectSprite;
import com.dumbpug.dungeony.game.rendering.Resources;
import com.dumbpug.levelgeneration.IEntityProperties;

/**
 * A destructable bush.
 */
public class Bush extends GameObject {
    /**
     * The door sprites.
     */
    private Sprite sprite, destroyedSprite;

    /**
     * Creates a new instance of the Bush class.
     * @param origin The initial origin of the Bush.
     */
    public Bush(Position origin, IEntityProperties properties) {
        super(origin, properties);
        sprite          = Resources.getSprite(GameObjectSprite.BUSH);
        destroyedSprite = Resources.getSprite(GameObjectSprite.BUSH_DESTROYED);
    }

    @Override
    public void onPositioned() { }

    @Override
    public float getLengthX() {
        return 19f;
    }

    @Override
    public float getLengthY() {
        return 14f;
    }

    @Override
    public float getLengthZ() {
        return 16f;
    }

    @Override
    public void onEnvironmentEntry(InteractiveEnvironment environment) { }

    @Override
    public void onEnvironmentExit(InteractiveEnvironment environment) { }

    @Override
    public void update(InteractiveEnvironment environment, float delta) { }

    @Override
    public void render(SpriteBatch spriteBatch) {
        // Draw the bush sprite.
        this.sprite.setSize(this.getLengthX(), this.getLengthZ());
        this.sprite.setPosition(this.getX(), this.getY());
        this.sprite.draw(spriteBatch);
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.BUSH;
    }
}
