package com.dumbpug.dungeony.game.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dumbpug.dungeony.Constants;
import com.dumbpug.dungeony.engine.Entity;
import com.dumbpug.dungeony.engine.InteractiveEnvironment;
import com.dumbpug.dungeony.engine.Position;
import com.dumbpug.dungeony.game.EntityCollisionFlag;
import com.dumbpug.dungeony.game.character.dodge.Dodge;
import com.dumbpug.dungeony.game.character.dodge.DodgeSpeed;
import com.dumbpug.dungeony.game.character.particles.walking.WalkingDustEmitterEntity;
import com.dumbpug.dungeony.game.inventory.Inventory;
import com.dumbpug.dungeony.rendering.Animation;
import com.dumbpug.dungeony.game.weapon.Weapon;
import com.dumbpug.dungeony.utilities.shaders.ShaderProvider;
import com.dumbpug.dungeony.utilities.shaders.ShaderType;
import java.util.HashMap;

/**
 * Represents an in-game character.
 */
public abstract class GameCharacter extends Entity<SpriteBatch> {
    /**
     * The current game character state.
     */
    private GameCharacterState state = GameCharacterState.IDLE;
    /**
     * The character health.
     */
    private GameCharacterHealth health = new GameCharacterHealth(Constants.CHARACTER_DEFAULT_HEALTH_SLOTS);
    /**
     * The character inventory.
     */
    private Inventory inventory = new Inventory(Constants.CHARACTER_INVENTORY_SLOTS);
    /**
     * The weapon equipped by the character, or null if no weapon is equipped.
     */
    private Weapon weapon = null;
    /**
     * The facing direction of the character.
     */
    private FacingDirection facingDirection = FacingDirection.RIGHT;
    /**
     * The angle of view of the character.
     */
    private Float angleOfView = null;
    /**
     * The active dodge state of the character.
     */
    private Dodge activeDodge = null;
    /**
     * The time that the character last received damage.
     */
    private long lastDamagedReceivedTime = 0l;
    /**
     * The mappings of character facing directions to state and animation mappings.
     */
    private HashMap<FacingDirection, HashMap<GameCharacterState, Animation>> animations;
    /**
     * The walking dust particle emitters for the player.
     */
    private WalkingDustEmitterEntity leftWalkingDustEmitter, rightWalkingDustEmitter;
    /**
     * The game character shadow sprite.
     */
    protected Sprite shadowSprite;

    /**
     * Creates a new instance of the GameCharacter class.
     * @param origin The initial origin of the GameCharacter.
     */
    public GameCharacter(Position origin) {
        super(origin);

        // Create the mappings of character facing directions to state and animation mappings.
        this.animations = new HashMap<FacingDirection, HashMap<GameCharacterState, Animation>>() {{
            put(FacingDirection.LEFT, new HashMap<GameCharacterState, Animation>());
            put(FacingDirection.RIGHT, new HashMap<GameCharacterState, Animation>());
        }};

        // Create the walking dust emitters.
        this.leftWalkingDustEmitter  = new WalkingDustEmitterEntity(new Position(origin), FacingDirection.LEFT);
        this.rightWalkingDustEmitter = new WalkingDustEmitterEntity(new Position(origin), FacingDirection.RIGHT);

        onPositionChange();
    }

    /**
     * Sets the x position of the entity.
     * @param x The x position
     */
    public void setX(float x) {
        super.setX(x);
        this.onPositionChange();
    }

    /**
     * Sets the y position of the entity.
     * @param y The y position
     */
    public void setY(float y) {
        super.setY(y);
        this.onPositionChange();
    }

    /**
     * Gets the current character state.
     * @return The current character state.
     */
    public GameCharacterState getState() {
        return state;
    }

    /**
     * Sets the current character state.
     * @param state The current character state.
     */
    public void setState(GameCharacterState state) {
        GameCharacterState originalState = this.state;
        this.state = state;
        if (originalState != state) {
            onStateChange(originalState, state);
        }
    }

    /**
     * Ges the character health.
     * @return The character health.
     */
    public GameCharacterHealth getHealth() {
        return health;
    }

    /**
     * Gets the character inventory.
     * @return The character inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gets the weapon equipped by the character, or null if no weapon is equipped.
     * @return The weapon equipped by the character, or null if no weapon is equipped.
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Sets the weapon equipped by the character, or null if no weapon is equipped.
     * @param weapon The weapon equipped by the character, or null if no weapon is equipped.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.updateWeaponPosition();
    }

    /**
     * Gets the angle of the characters direction of view.
     * @return The angle of the characters direction of view.
     */
    public Float getAngleOfView() {
        return angleOfView;
    }

    /**
     * Sets the angle of the characters direction of view.
     * @param angleOfView The angle of the characters direction of view.
     */
    public void setAngleOfView(Float angleOfView) {
        this.angleOfView = angleOfView;
        if (angleOfView != null) {
            setFacingDirection(FacingDirection.fromAngle(this.angleOfView));
        }
        this.updateWeaponPosition();
    }

    /**
     * Gets the facing direction of the character.
     * @return The facing direction of the character.
     */
    public FacingDirection getFacingDirection() {
        return facingDirection;
    }

    /**
     * Sets the facing direction of the character.
     * @param direction The facing direction of the character.
     */
    public void setFacingDirection(FacingDirection direction) {
        this.facingDirection = direction;
        this.updateWeaponPosition();
    }

    @Override
    public int getCollisionLayers() {
        return EntityCollisionFlag.CHARACTER;
    }

    @Override
    public int getCollisionMask() {
        // Everything should collide with a character by default.
        return EntityCollisionFlag.WALL | EntityCollisionFlag.CHARACTER | EntityCollisionFlag.OBJECT;
    }

    @Override
    public void onEnvironmentEntry(InteractiveEnvironment environment) {
        environment.addEntity(this.leftWalkingDustEmitter);
        environment.addEntity(this.rightWalkingDustEmitter);
    }

    /**
     * Gets the character animation for the given state and facing direction.
     * @param state The character state.
     * @param direction The facing direction of the character.
     * @return The character animation for the given state and facing direction.
     */
    protected Animation getAnimation(GameCharacterState state, FacingDirection direction) {
        return this.animations.get(direction).get(state);
    }

    /**
     * Sets the character animation for the given state and facing direction.
     * @param state The character state.
     * @param direction The facing direction of the character.
     * @param animation The animation.
     */
    protected void setAnimation(GameCharacterState state, FacingDirection direction, Animation animation) {
        this.animations.get(direction).put(state, animation);
    }

    /**
     * Applies damage to the game character.
     * @param points The points of damage to apply to the game character.
     */
    public void applyDamage(InteractiveEnvironment environment, float delta, int points) {
        // We can only apply damage to a character if they aren't invincible and their health isn't already depleted.
        if (this.health.isInvincible() || this.health.isHealthDepleted()) {
            return;
        }

        // Reduce the characters health points by the amount specified.
        this.health.reduceHealthPoints(points);

        this.lastDamagedReceivedTime = System.currentTimeMillis();

        this.onDamageTaken(environment, delta, points);

        if (this.health.isHealthDepleted()) {
            this.onHealthDepleted(environment, delta);
        }
    }

    /**
     * Applies a status effect to the game character.
     */
    public void applyStatusEffect() {
        // TODO Apply a status effect to the character.
    }

    /**
     * Make the character walk by applying an angle and distance.
     * @param environment
     * @param angle
     * @param distance
     * @param delta
     */
    public void walkByAngle(InteractiveEnvironment environment, float angle, float distance, float delta) {
        // Is the character idle and not moving in any direction?
        if (distance == 0f) {
            return;
        }

        // Calculate the new position of the character to move based on the amount to move and the entity movement speed.
        float offsetX = (float) Math.sin(angle * Math.PI / 180) * distance;
        float offsetY = (float) Math.cos(angle * Math.PI / 180) * distance;

        this.walk(environment, offsetX, offsetY, delta);
    }

    /**
     * Make the character walk by applying an x/y offset.
     * @param environment
     * @param movementAxisX
     * @param movementAxisY
     * @param delta
     */
    public void walk(InteractiveEnvironment environment, float movementAxisX, float movementAxisY, float delta) {
        // There is nothing to do if the character cannot walk.
        if (!this.canWalk()) {
            return;
        }

        // Is the character idle and not moving in any direction?
        if (movementAxisX == 0f && movementAxisY == 0f) {
            // The character has not moved on either axis so is now idle.
            this.setState(GameCharacterState.IDLE);

            return;
        }

        // Check whether the character was idle before attempting to walk.
        boolean wasCharacterIdle = this.getState() == GameCharacterState.IDLE;

        // Get the direction that the character was facing before we do any walking.
        FacingDirection originalFacingDirection = this.getFacingDirection();

        // If the character has no defined angle of view, then the direction they are moving on the x axis will determine their new facing direction.
        if (this.angleOfView == null) {
            // We are running because we are moving on either axis, but the X axis movement determines which way we face.
            if (movementAxisX < 0) {
                setFacingDirection(FacingDirection.LEFT);
            } else if (movementAxisX > 0) {
                setFacingDirection(FacingDirection.RIGHT);
            }
        }

        // The character will be moving, so set the character state to 'RUNNING'.
        this.setState(GameCharacterState.RUNNING);

        // Any entity movement has to be taken care of by the level grid which handles all entity collisions.
        environment.move(this, movementAxisX, movementAxisY, delta);

        // Were we walking as part of the last update and changed direction?
        if (originalFacingDirection != this.getFacingDirection() && !wasCharacterIdle) {
            onWalkingDirectionChange(environment, delta);
        }
    }

    /**
     * Make the character dodge in the direction defined by an angle.
     * @param environment
     * @param angle
     */
    public void dodge(InteractiveEnvironment environment, float angle) {
        // We cannot dodge if we are already dodging.
        if (this.getState() == GameCharacterState.DODGING) {
            return;
        }

        // Start the character dodge.
        this.activeDodge = new Dodge(DodgeSpeed.DEFAULT, angle, Constants.CHARACTER_DODGE_DURATION_MS);

        // Set the character state.
        this.setState(GameCharacterState.DODGING);
    }

    /**
     * Render the renderable using the provided sprite batch.
     * @param batch The sprite batch to use in rendering the renderable.
     */
    @Override
    public void render(SpriteBatch batch) {
        // Get the relevant animation for the character based on their current state and facing direction.
        Animation animation = animations.get(this.facingDirection).get(this.getState());

        // Get the current animation frame for the animation.
        TextureRegion currentFrame = animation.getCurrentFrame();

        // Draw the character shadow (if they have one).
        if (this.shadowSprite != null) {
            float width = this.getLengthX();
            float height = shadowSprite.getRegionHeight() * (this.getLengthX()/shadowSprite.getRegionWidth());
            shadowSprite.setSize(width, height);
            shadowSprite.setPosition(this.getX(), this.getY() - (height / 2f));
            shadowSprite.draw(batch);
        }

        // Draw the weapon of the character if they have one, but do this behind the character if they are facing right. Don't render a weapon for dead characters.
        if (this.getWeapon() != null && this.facingDirection == FacingDirection.RIGHT && this.getState() != GameCharacterState.DEAD) {
            this.getWeapon().render(batch);
        }

        // How we render the actual game character sprite depends on how recently they took damage. For a small period
        // after a character takes damage they will be rendered with a solid white sprite instead of the standard one.
        if (this.lastDamagedReceivedTime > (System.currentTimeMillis() - Constants.CHARACTER_DAMAGE_OVERLAY_DURATION_MS)) {
            batch.end();

            // Render the current animation frame of the character using the solid white shader as damage feedback.
            batch.setShader(ShaderProvider.getShader(ShaderType.SOLID_WHITE));
            batch.begin();
            batch.draw(currentFrame, this.getX(), this.getY(), 0, 0, this.getLengthX(), this.getLengthZ(), 1.0f, 1.0f, 0);
            batch.end();

            batch.setShader(ShaderProvider.getDefault());
            batch.begin();
        } else {
            // Render the current animation frame of the character.
            batch.draw(currentFrame, this.getX(), this.getY(), 0, 0, this.getLengthX(), this.getLengthZ(), 1.0f, 1.0f, 0);
        }

        // Draw the weapon of the character if they have one, but do this in front of the character if they are facing left. Don't render a weapon for dead characters.
        if (this.getWeapon() != null && this.facingDirection == FacingDirection.LEFT && this.getState() != GameCharacterState.DEAD) {
            this.getWeapon().render(batch);
        }
    }

    /**
     * Gets the character movements speed per second.
     * @return The character movements speed per second.
     */
    public abstract float getMovementSpeed();

    /**
     * Called when the character takes damage.
     * @param environment The interactive environment.
     * @param delta The delta time.
     * @param points The points of damage taken.
     */
    public abstract void onDamageTaken(InteractiveEnvironment environment, float delta, int points);

    /**
     * Called when the characters health is depleted.
     * @param environment The interactive environment.
     * @param delta The delta time.
     */
    public abstract void onHealthDepleted(InteractiveEnvironment environment, float delta);

    /**
     * Gets whether the character can walk.
     * @return Whether the character can walk.
     */
    private boolean canWalk() {
        // The character cannot walk if they are dead.
        if (this.getState() == GameCharacterState.DEAD) {
            return false;
        }

        // The character cannot walk if they are currently dodging.
        if (this.getState() == GameCharacterState.DODGING) {
            return false;
        }

        return true;
    }

    /**
     * Called whenever the state of the character changes.
     */
    private void onStateChange(GameCharacterState previous, GameCharacterState current) {
        // Check whether we have stopped or started walking.
        if (current == GameCharacterState.RUNNING) {
            onWalkingStart();
        } else if (previous == GameCharacterState.RUNNING) {
            onWalkingStop();
        }
    }

    /**
     * Called whenever the x/y position of the character changes.
     */
    private void onPositionChange() {
        // Update the position of the character weapon.
        updateWeaponPosition();

        // Update the position of the walking particle emitters.
        updateWalkingParticleEmitterPositions();
    }

    /**
     * Updates the position of the equipped weapon if there is one.
     */
    private void updateWeaponPosition() {
        if (this.weapon != null) {
            // Where the weapon is positioned in the world will depend on the position of the character holding it.
            float weaponPositionY = this.getOrigin().getY() - (this.getLengthY() * 0.3f);
            float weaponPositionX = this.getOrigin().getX() + (this.getLengthX() * 0.25f);
            this.getWeapon().getPosition().set(weaponPositionX, weaponPositionY);
            this.getWeapon().setAngleOfAim(this.angleOfView == null ? this.facingDirection.getAngle() : this.angleOfView);
        }
    }

    /**
     * Updates the position of the walking particle emitters.
     */
    private void updateWalkingParticleEmitterPositions() {
        this.leftWalkingDustEmitter.setX(this.getX() + this.getLengthX());
        this.leftWalkingDustEmitter.setY(this.getY());
        this.rightWalkingDustEmitter.setX(this.getX());
        this.rightWalkingDustEmitter.setY(this.getY());
    }

    private void onWalkingStart() {
        if (getFacingDirection() == FacingDirection.LEFT) {
            this.leftWalkingDustEmitter.enable();
            this.rightWalkingDustEmitter.disable();
        } else {
            this.leftWalkingDustEmitter.disable();
            this.rightWalkingDustEmitter.enable();
        }
    }

    private void onWalkingStop() {
        this.leftWalkingDustEmitter.disable();
        this.rightWalkingDustEmitter.disable();
    }

    private void onWalkingDirectionChange(InteractiveEnvironment environment, float delta) {
        if (getFacingDirection() == FacingDirection.LEFT) {
            this.leftWalkingDustEmitter.enable();
            this.rightWalkingDustEmitter.disable();
        } else {
            this.leftWalkingDustEmitter.disable();
            this.rightWalkingDustEmitter.enable();
        }
    }
}
