package com.dumbpug.dungeony.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Represents an animation texture loaded from disk.
 */
public class AnimationTexture {
    /**
     * The time step for the animation.
     */
    private float step;
    /**
     * The number of rows/columns in the texture.
     */
    private int rows, columns;
    /**
     * Whether the animation loops.
     */
    private boolean loop;
    /**
     * The animation frames.
     */
    private TextureRegion[] frames;

    /**
     * Creates a new instance of the AnimationTexture class.
     * @param textureFilePath
     */
    public AnimationTexture(String textureFilePath) {
        // Create a file wrapper for the texture file.
        File textureFile = new File(textureFilePath);

        // Check the texture file exists.
        if (!textureFile.exists()) {
            throw new RuntimeException("cannot locate animation texture file: " + textureFile.getAbsolutePath());
        }

        // Create a file wrapper for the JSON file which should be in the same directory as the texture file and with the same name plus the .json extension.
        File jsonFile = new File(textureFile.getParentFile(), textureFile.getName() + ".json");

        // Check the JSON file exists.
        if (!jsonFile.exists()) {
            throw new RuntimeException("cannot locate animation JSON file: " + jsonFile.getAbsolutePath());
        }

        parseJsonFile(jsonFile);

        createFrames(textureFile);
    }

    public Animation createAnimation() {
        return null;
    }

    private void parseJsonFile(File file) {
        // Read the JSON as a string from the file.
        String content = null;
        try {
            content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("cannot read from animation JSON file: " + file.getAbsolutePath());
        }

        // Create the JSON object.
        JSONObject jsonObject = new JSONObject(content);

        if (!jsonObject.has("rows"))
        {
            throw new RuntimeException("cannot read property 'rows' from animation JSON file: " + file.getAbsolutePath());
        }

        if (!jsonObject.has("columns"))
        {
            throw new RuntimeException("cannot read property 'columns' from animation JSON file: " + file.getAbsolutePath());
        }

        if (!jsonObject.has("loop"))
        {
            throw new RuntimeException("cannot read property 'loop' from animation JSON file: " + file.getAbsolutePath());
        }

        if (!jsonObject.has("step"))
        {
            throw new RuntimeException("cannot read property 'step' from animation JSON file: " + file.getAbsolutePath());
        }

        this.rows    = jsonObject.getInt("rows");
        this.columns = jsonObject.getInt("columns");
        this.loop    = jsonObject.getBoolean("loop");
        this.step    = (float)jsonObject.getDouble("step");
    }

    private void createFrames(File textureFile) {
        Texture texture = new Texture(textureFile.getAbsolutePath());

        TextureRegion[][] tempRegion = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight() / rows);

        frames = new TextureRegion[rows * columns];

        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                frames[index++] = tempRegion[i][j];
            }
        }
    }
}
