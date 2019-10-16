package envirogen.area;

import java.util.HashMap;

/**
 * A mapping of area names to the count of times the area has been generated.
 */
public class AreaCountMap extends HashMap<String, Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Get the number of times the area with the specified name has been generated.
	 * @param name The area name.
	 * @return The number of times the area with the specified name has been generated.
	 */
	public int getCount(String name) {
		return this.containsKey(name) ? this.get(name) : 0;
	}
	
	/**
	 * Increment the count for the specified area.
	 * @param name The area name.
	 */
	public void incrementCount(String name) {
		if (this.containsKey(name)) {
			this.put(name, this.get(name) + 1);
		} else {
			this.put(name, 1);
		}
	}
	
	/**
	 * Get the total number of generated areas.
	 * @return The total number of generated areas.
	 */
	public int getTotal() {
		int total = 0;
		for (Integer count : this.values()) {
			total += count;
		}
		return total;
	}
}
