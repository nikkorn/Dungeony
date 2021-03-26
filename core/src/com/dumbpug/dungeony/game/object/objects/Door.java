package com.dumbpug.dungeony.game.object.objects;

import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.game.lights.SpotLight;
import com.dumbpug.dungeony.game.object.GameObject;
import com.dumbpug.dungeony.game.object.GameObjectType;
import com.dumbpug.levelgeneration.IEntityProperties;

/**
 * A door used to leave the level.
 */
public class Door extends GameObject {
    /**
     * Creates a new instance of the Door class.
     * @param origin The initial origin of the Door.
     */
    public Door(Position origin, IEntityProperties properties) {
        super(origin, properties);
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
    public GameObjectType getType() {
        return GameObjectType.DOOR;
    }
}
