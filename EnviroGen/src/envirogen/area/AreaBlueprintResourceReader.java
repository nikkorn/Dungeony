package envirogen.area;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONObject;

public class AreaBlueprintResourceReader {
	/**
	 * The area file extension.
	 */
	private static final String AREA_FILE_EXTENSION = ".area.json";
	
	/**
	 * Get all area blueprints from the given directory.
	 * @param resourceDirectory The area resource directory.
	 * @return All area blueprints from the given directory.
	 */
	public static AreaBlueprints getAreaBlueprints(File resourceDirectory) {
		// The file must exist and be a directory for us to continue.
		if (!resourceDirectory.exists() || !resourceDirectory.isDirectory()) {
			throw new RuntimeException("area resource directory '" + resourceDirectory.getAbsolutePath() + "' does not exist or is a file");
		}
		
		// Get all of the area resource files.
		ArrayList<File> areaResourceFiles = findFilesWithExtension(resourceDirectory, AREA_FILE_EXTENSION);
		
		// Create a list to store all of the blueprints.
		ArrayList<AreaBlueprint> blueprints = new ArrayList<AreaBlueprint>();
		
		// Get the contents of every area definition file and convert it to an actual AreaBlueprint.
		for (File areaResourceFile : areaResourceFiles) {
			blueprints.add(AreaBlueprintFactory.create(new JSONObject(getFileContents(areaResourceFile))));
		}
		
		return new AreaBlueprints(blueprints);
	}
	
	/**
	 * Find all files recursively nested within the directory with the given extension.
	 * @param resourceDirectory The directory containing resources.
	 * @param extension The resource file extension.
	 * @return All files nested within the directory with the given extension.
	 */
	private static ArrayList<File> findFilesWithExtension(File resourceDirectory, String extension) {
		// Create a list to hold all the matching files.
		ArrayList<File> found = new ArrayList<File>();
		
		findFilesInDirectoryWithExtension(resourceDirectory, extension, found);
		
		// Return the list holding all the matching files.
		return found;
	}
	
	/**
	 * Find all files recursively nested within the directory with the given extension.
	 * @param directory The current directory.
	 * @param extension The resource file extension.
	 * @param found The list of found files.
	 */
	private static void findFilesInDirectoryWithExtension(File directory, String extension, ArrayList<File> found) {
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				findFilesInDirectoryWithExtension(file, extension, found);
			} else {
				if (file.getName().endsWith(extension)) {
					found.add(file);
				}
			}
		}
	}
	
	/**
	 * Get the contents of the specified file.
	 * @param file The file to read from.
	 * @return The contents of the specified file.
	 */
	private static String getFileContents(File file) {
		try {
			return new String(Files.readAllBytes(Paths.get(file.getPath())));
		} catch (IOException e) {
			throw new RuntimeException("cannot read test from file: " + file.getAbsolutePath());
		}
	}
}
