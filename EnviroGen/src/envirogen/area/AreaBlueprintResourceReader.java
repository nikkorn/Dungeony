package envirogen.area;

import java.io.File;
import java.util.ArrayList;
import org.json.JSONObject;

import envirogen.Configuration;
import envirogen.Utility;

public class AreaBlueprintResourceReader {
	/**
	 * The area file extension.
	 */
	private static final String AREA_FILE_EXTENSION = ".area.json";
	
	/**
	 * Get all area blueprints from the given directory.
	 * @param configuration The application configuration.
	 * @param resourceDirectory The area resource directory.
	 * @return All area blueprints from the given directory.
	 */
	public static AreaBlueprints getAreaBlueprints(Configuration configuration, File resourceDirectory) {
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
			blueprints.add(AreaBlueprintFactory.create(new JSONObject(Utility.getFileContents(areaResourceFile)), configuration));
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
}
