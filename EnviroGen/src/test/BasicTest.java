package test;

import envirogen.Envirogen;

/**
 * A basic test.
 */
public class BasicTest {
	
	/**
	 * Entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		Envirogen.generate("resources/test_environment/environment.config.json", 12345678);
	}
}
