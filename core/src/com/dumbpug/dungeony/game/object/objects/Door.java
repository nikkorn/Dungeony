package com.dumbpug.dungeony.game.object.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.game.lights.SpotLight;
import com.dumbpug.dungeony.game.object.GameObject;
import com.dumbpug.dungeony.game.object.GameObjectType;
import com.dumbpug.dungeony.game.rendering.GameObjectSprite;
import com.dumbpug.dungeony.game.rendering.Resources;
import com.dumbpug.levelgeneration.IEntityProperties;

/**
 * A door used to leave the level.
 */
public class Door extends GameObject {
    /**
     * The door sprite.
     */
    private Sprite sprite;

    /**
     * Creates a new instance of the Door class.
     * @param origin The initial origin of the Door.
     */
    public Door(Position origin, IEntityProperties properties) {
        super(origin, properties);
        sprite = Resources.getSprite(GameObjectSprite.DOOR);
    }

    @Override
    public void onPositioned() { }

    @Override
    public float getLengthX() {
        return 37f;
    }

    @Override
    public float getLengthY() {
        return 15f;
    }

    @Override
    public float getLengthZ() {
        return 38f;
    }

    @Override
    public void onEnvironmentEntry(InteractiveEnvironment environment) {
        environment.addLight(new SpotLight(this, 1f, 0.3f, 0.3f));
    }

    @Override
    public void onEnvironmentExit(InteractiveEnvironment environment) { }

    @Override
    public void update(InteractiveEnvironment environment, float delta) { }

    @Override
    public void render(SpriteBatch spriteBatch) {
        // Draw the door sprite.
        this.sprite.setSize(this.getLengthX(), this.getLengthZ());
        this.sprite.setPosition(this.getX(), this.getY());
        this.sprite.draw(spriteBatch);
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.DOOR;
    }
}
