package envirogen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Various utility methods.
 */
public class Utility {
	
	/**
	 * Get the contents of the specified file.
	 * @param file The file to read from.
	 * @return The contents of the specified file.
	 */
	public static String getFileContents(File file) {
		try {
			return new String(Files.readAllBytes(Paths.get(file.getPath())));
		} catch (IOException e) {
			throw new RuntimeException("cannot read test from file: " + file.getAbsolutePath());
		}
	}
}
