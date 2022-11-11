package fc.Films;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class BluRayTest {

	/* Create Film attributes */
	private final double price = 20;
	private final StatesBluRay state = StatesBluRay.AVAILABLE;
	private final String title = "Ghostbusters",
			synopsis = "Three parapsychologists forced out of their university "
					+ "funding set up shop as a unique ghost removal service in New York City, "
					+ "attracting frightened yet skeptical customers.",
			director_lname = "Reitman",
			director_fname = "Ivan";
	private final String[] actors = {"Bill Murray", "Dan Aykroyd", "Harold Ramis", "Ernie Hudson"};
	private final AgeRestriction restriction = AgeRestriction.EVERYONE;
	private final Categories[] categories = {Categories.ACTION, Categories.COMEDIES};
	
	/* Create an instance of a BluRay for test BluRay class */
	protected BluRay bluRay = new BluRay(price, state, title, synopsis, actors, director_fname, director_lname, restriction, categories);


	/**
	 * Check the constructor of BluRay
	 */
	@Test
	void testBluRay() {
		Assertions.assertEquals(title, bluRay.title, "bluRay.title test passed");
		Assertions.assertEquals(synopsis, bluRay.synopsis, "bluRay.synopsis test passed");
		Assertions.assertEquals(actors, bluRay.getActors(), "bluRay.getActors() test passed");
		Assertions.assertEquals(director_fname, bluRay.FNameDirector, "bluRay.FNameDirector test passed");
		Assertions.assertEquals(director_lname, bluRay.LNameDirector, "bluRay.LNameDirector test passed");
		Assertions.assertEquals(categories, bluRay.getCategories(), "bluRay.getCategories() test passed");
		Assertions.assertEquals(restriction, bluRay.restriction, "bluRay.restriction test passed");
	}

	/**
	 * Check getType()
	 */
	@Test
	void testGetType() {
		// Test will pass
		Assertions.assertEquals("BluRay", bluRay.getType(), "bluRay.getType() test passed");
	    Assertions.assertNotEquals("QRCode", bluRay.getType(), "bluRay.getType() test failed");

	}
	/**
	 * Check getPrice()
	 */
	@Test
	void testGetPrice() {
		Assertions.assertEquals(price, bluRay.getPrice(), "bluRay.getPrice() test passed");
	}

	/**
	 * Check setPrice
	 */
	@Test
	void testSetPrice() {
		final double testPrice = 24.57;
		bluRay.setPrice(testPrice);
		
		Assertions.assertNotEquals(price, bluRay.getPrice(), "bluRay.setPrice() test failed");
		Assertions.assertEquals(testPrice, bluRay.getPrice(), "bluRay.setPrice() test passed");
	}
	
	/**
	 * Check getState()
	 */
	@Test
	void testGetState() {
		// Test will pass
		Assertions.assertEquals(state, bluRay.getState(), "bluRay.getState() test passed");
	}

	/**
	 * Check setState()
	 */
	@Test
	void testSetState() {
		final StatesBluRay testState = StatesBluRay.STOLEN;
		bluRay.setState(testState);
		
		Assertions.assertNotEquals(state, bluRay.getState(), "bluRay.setState() test failed");
		Assertions.assertEquals(testState, bluRay.getState(), "bluRay.setState() test passed");
	}
	
	@Test
	void testIsBRAvailable() {
		bluRay.setState(state);
		Assertions.assertTrue(bluRay.isBRAvailable());
		
		bluRay.setState(StatesBluRay.DAMAGED);
		Assertions.assertFalse(bluRay.isBRAvailable());
	}
}
