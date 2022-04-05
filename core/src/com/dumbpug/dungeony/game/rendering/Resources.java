package com.dumbpug.dungeony.game.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.dungeony.game.character.FacingDirection;
import com.dumbpug.dungeony.game.character.GameCharacterState;
import com.dumbpug.dungeony.game.character.enemy.EnemyType;
import com.dumbpug.dungeony.game.character.friendly.FriendlyType;
import com.dumbpug.dungeony.game.character.player.PlayerType;
import com.dumbpug.dungeony.game.projectile.ProjectileType;
import com.dumbpug.dungeony.game.weapon.WeaponState;
import com.dumbpug.dungeony.game.weapon.WeaponType;
import com.dumbpug.dungeony.rendering.Animation;
import com.dumbpug.dungeony.rendering.AnimationDetails;
import java.util.HashMap;

/**
 * Game resources provider.
 */
public class Resources {
    /**
     * Level sprite map.
     */
    private static HashMap<LevelSprite, Sprite> levelSpriteMap;
    /**
     * Tile sprite map.
     */
    private static HashMap<TileSprite, Sprite> tileSpriteMap;
    /**
     * Game object sprite map.
     */
    private static HashMap<GameObjectSprite, Sprite> gameObjectSpriteMap;
    /**
     * Particle sprite map.
     */
    private static HashMap<ParticleSprite, Sprite> particleSpriteMap;
    /**
     * Dialog sprite map.
     */
    private static HashMap<DialogSprite, Sprite> dialogSpriteMap;

    private static HashMap<ParticleAnimation, AnimationDetails> particleAnimationMap;
    private static HashMap<PlayerType, HashMap<String, AnimationDetails>> playerStateAnimationMap;
    private static HashMap<EnemyType, HashMap<String, AnimationDetails>> enemyStateAnimationMap;
    private static HashMap<FriendlyType, HashMap<String, AnimationDetails>> friendlyStateAnimationMap;
    private static HashMap<WeaponType, HashMap<WeaponState, AnimationDetails>> weaponAnimationMap;
    private static HashMap<ProjectileType, AnimationDetails> projectileAnimationMap;

    static {
        levelSpriteMap = new HashMap<LevelSprite, Sprite>() {{
            put(LevelSprite.UNDERLAY, new Sprite(new Texture("images/level/UNDERLAY.png")));
        }};
        tileSpriteMap = new HashMap<TileSprite, Sprite>() {{
            for (TileSprite sprite : TileSprite.values()) {
                put(sprite, new Sprite(new Texture("images/tile/" + sprite + ".png")));
            }
        }};
        gameObjectSpriteMap = new HashMap<GameObjectSprite, Sprite>() {{
            for (GameObjectSprite sprite : GameObjectSprite.values()) {
                put(sprite, new Sprite(new Texture("images/game_object/" + sprite + ".png")));
            }
        }};
        particleSpriteMap = new HashMap<ParticleSprite, Sprite>() {{
            for (ParticleSprite sprite : ParticleSprite.values()) {
                put(sprite, new Sprite(new Texture("images/particle/" + sprite + ".png")));
            }
        }};
        dialogSpriteMap = new HashMap<DialogSprite, Sprite>() {{
            for (DialogSprite sprite : DialogSprite.values()) {
                put(sprite, new Sprite(new Texture("images/dialog/" + sprite + ".png")));
            }
        }};
        particleAnimationMap = new HashMap<ParticleAnimation, AnimationDetails>() {{
            for (ParticleAnimation animation : ParticleAnimation.values()) {
                put(animation, new AnimationDetails("images/particle/" + animation + ".png"));
            }
        }};
        playerStateAnimationMap = new HashMap<PlayerType, HashMap<String, AnimationDetails>>() {{
            for (PlayerType playerType : PlayerType.values()) {
                HashMap<String, AnimationDetails> animationMap = new HashMap<String, AnimationDetails>();

                put(playerType, animationMap);

                for (GameCharacterState state : GameCharacterState.values()) {
                    animationMap.put(createCharacterAnimationKey(state, FacingDirection.LEFT),
                            new AnimationDetails("images/character/player/" + playerType  + "/" + state +  "_" + FacingDirection.LEFT +".png"));
                    animationMap.put(createCharacterAnimationKey(state, FacingDirection.RIGHT),
                            new AnimationDetails("images/character/player/" + playerType  + "/" + state +  "_" + FacingDirection.RIGHT +".png"));
                }
            }
        }};
        enemyStateAnimationMap = new HashMap<EnemyType, HashMap<String, AnimationDetails>>() {{
            for (EnemyType enemyType : EnemyType.values()) {
                HashMap<String, AnimationDetails> animationMap = new HashMap<String, AnimationDetails>();

                put(enemyType, animationMap);

                for (GameCharacterState state : GameCharacterState.values()) {
                    animationMap.put(createCharacterAnimationKey(state, FacingDirection.LEFT),
                            new AnimationDetails("images/character/enemy/" + enemyType  + "/" + state +  "_" + FacingDirection.LEFT +".png"));
                    animationMap.put(createCharacterAnimationKey(state, FacingDirection.RIGHT),
                            new AnimationDetails("images/character/enemy/" + enemyType  + "/" + state +  "_" + FacingDirection.RIGHT +".png"));
                }
            }
        }};
        friendlyStateAnimationMap = new HashMap<FriendlyType, HashMap<String, AnimationDetails>>() {{
            for (FriendlyType friendlyType : FriendlyType.values()) {
                HashMap<String, AnimationDetails> animationMap = new HashMap<String, AnimationDetails>();

                put(friendlyType, animationMap);

                for (GameCharacterState state : GameCharacterState.values()) {
                    animationMap.put(createCharacterAnimationKey(state, FacingDirection.LEFT),
                            new AnimationDetails("images/character/friendly/" + friendlyType  + "/" + state +  "_" + FacingDirection.LEFT +".png"));
                    animationMap.put(createCharacterAnimationKey(state, FacingDirection.RIGHT),
                            new AnimationDetails("images/character/friendly/" + friendlyType  + "/" + state +  "_" + FacingDirection.RIGHT +".png"));
                }
            }
        }};
        weaponAnimationMap = new HashMap<WeaponType, HashMap<WeaponState, AnimationDetails>>() {{
            for (WeaponType weaponType : WeaponType.values()) {
                HashMap<WeaponState, AnimationDetails> animationMap = new HashMap<WeaponState, AnimationDetails>();

                put(weaponType, animationMap);

                for (WeaponState weaponState : WeaponState.values()) {
                    animationMap.put(weaponState, new AnimationDetails("images/weapon/" + weaponType  + "/" + weaponState + ".png"));
                }
            }
        }};
        projectileAnimationMap = new HashMap<ProjectileType, AnimationDetails>() {{
            for (ProjectileType projectileType : ProjectileType.values()) {
                put(projectileType, new AnimationDetails("images/projectile/" + projectileType + ".png"));
            }
        }};
    }

    /**
     * Gets the sprite for the specified level sprite type.
     * @param levelSprite
     * @return
     */
    public static Sprite getSprite(LevelSprite levelSprite) {
        return levelSpriteMap.get(levelSprite);
    }

    /**
     * Gets the sprite for the specified tile sprite type.
     * @param tileSprite
     * @return
     */
    public static Sprite getSprite(TileSprite tileSprite) {
        return tileSpriteMap.get(tileSprite);
    }

    /**
     * Gets the sprite for the specified game object sprite type.
     * @param gameObjectSprite
     * @return
     */
    public static Sprite getSprite(GameObjectSprite gameObjectSprite) {
        return gameObjectSpriteMap.get(gameObjectSprite);
    }

    /**
     * Gets the sprite for the specified particle sprite type.
     * @param particleSprite
     * @return
     */
    public static Sprite getSprite(ParticleSprite particleSprite) {
        return particleSpriteMap.get(particleSprite);
    }

    /**
     * Gets the animation for the specified particle animation type.
     * @param particleAnimation
     * @return
     */
    public static Animation getAnimation(ParticleAnimation particleAnimation) {
        return new Animation(particleAnimationMap.get(particleAnimation));
    }

    /**
     * Gets the animation for the specified weapon and weapon state type.
     * @param state The weapon state type.
     * @param type The weapon type.
     * @return The animation for the specified weapon state and type.
     */
    public static Animation getWeaponAnimation(WeaponState state, WeaponType type) {
        return new Animation(weaponAnimationMap.get(type).get(state));
    }

    /**
     * Gets the animation for the specified projectile type.
     * @param type The projectile type.
     * @return The animation for the specified projectile type.
     */
    public static Animation getProjectileAnimation(ProjectileType type) {
        return new Animation(projectileAnimationMap.get(type));
    }

    /**
     * Gets the sprite for the specified game character sprite and player type.
     * @param sprite The game character sprite type.
     * @param type The player type.
     * @return The sprite for the specified game character sprite and player type.
     */
    public static Sprite getCharacterSprite(GameCharacterSprite sprite, PlayerType type) {
        return new Sprite(new Texture("images/character/player/" + type  + "/" + sprite + ".png"));
    }

    /**
     * Gets the sprite for the specified game character sprite and enemy type.
     * @param sprite The game character sprite type.
     * @param type The enemy type.
     * @return The sprite for the specified game character sprite and enemy type.
     */
    public static Sprite getCharacterSprite(GameCharacterSprite sprite, EnemyType type) {
        return new Sprite(new Texture("images/character/enemy/" + type  + "/" + sprite + ".png"));
    }

    /**
     * Gets the sprite for the specified game character sprite and friendly type.
     * @param sprite The game character sprite type.
     * @param type The friendly type.
     * @return The sprite for the specified game character sprite and friendly type.
     */
    public static Sprite getCharacterSprite(GameCharacterSprite sprite, FriendlyType type) {
        return new Sprite(new Texture("images/character/friendly/" + type  + "/" + sprite + ".png"));
    }

    /**
     * Gets the animation for the specified player state type.
     * @param state The state type.
     * @param type The enemy type.
     * @param direction The character facing direction.
     * @return The animation for the specified player state and type.
     */
    public static Animation getCharacterAnimation(GameCharacterState state, PlayerType type, FacingDirection direction) {
        return new Animation(playerStateAnimationMap.get(type).get(createCharacterAnimationKey(state, direction)));
    }

    /**
     * Gets the animation for the specified enemy state type.
     * @param state The state type.
     * @param type The enemy type.
     * @param direction The character facing direction.
     * @return The animation for the specified enemy state and type.
     */
    public static Animation getCharacterAnimation(GameCharacterState state, EnemyType type, FacingDirection direction) {
        return new Animation(enemyStateAnimationMap.get(type).get(createCharacterAnimationKey(state, direction)));
    }

    /**
     * Gets the animation for the specified friendly state type.
     * @param state The state type.
     * @param type The friendly type.
     * @param direction The character facing direction.
     * @return The animation for the specified friendly state and type.
     */
    public static Animation getCharacterAnimation(GameCharacterState state, FriendlyType type, FacingDirection direction) {
        return new Animation(friendlyStateAnimationMap.get(type).get(createCharacterAnimationKey(state, direction)));
    }

    /**
     * Creates a unique key based on a facing direction value and a game character state value.
     * @param state The state type.
     * @param direction The character facing direction.
     * @return A unique key based on a facing direction value and a game character state value.
     */
    private static String createCharacterAnimationKey(GameCharacterState state, FacingDirection direction) {
        return state + "_" + direction;
    }
}
