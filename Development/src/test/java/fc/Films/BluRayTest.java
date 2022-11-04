package fc.Films;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class BluRayTest {

	/**
	 * Create an instance of a BluRay for test BluRay class
	 */
	protected static BluRay bluRay = new BluRay(22.90, StatesBluRay.AVAILABLE, "tata", "tata tutu toto", new String[]{"JM POPO","PE RORO"}, "DUDU", "DADA", AgeRestriction.EVERYONE, new Categories[] {Categories.ANIME});

	/**
	 * Check getType()
	 */
	@Test
	void testGetType() {
		// Test will pass
		Assertions.assertEquals("BluRay", bluRay.getType(), "bluRay.getType() test passed");
		
		//Test will fail 
	    //Assertions.assertEquals("QRCode", bluRay.getType(), "bluRay.getType() test failed");
	    Assertions.assertNotEquals("QRCode", bluRay.getType(), "bluRay.getType() test failed");

	}

	/**
	 * Check the constructor of BluRay
	 */
	@Test
	void testBluRay() {
		Assertions.assertEquals("tata", bluRay.title, "bluRay.title test passed");
		Assertions.assertEquals("tata tutu toto", bluRay.synopsis, "bluRay.synopsis test passed");
		Assertions.assertEquals("JM POPO, PE RORO", bluRay.getActors(), "bluRay.getActors() test passed");
		Assertions.assertEquals("DUDU", bluRay.FNameDirector, "bluRay.FNameDirector test passed");
		Assertions.assertEquals("DADA", bluRay.LNameDirector, "bluRay.LNameDirector test passed");
		Assertions.assertEquals("ANIME", bluRay.getCategories(), "bluRay.getCategories() test passed");
		Assertions.assertEquals(AgeRestriction.EVERYONE, bluRay.restriction, "bluRay.restriction test passed");
	}

	/**
	 * Check getPrice()
	 */
	@Test
	void testGetPrice() {
		Assertions.assertEquals(22.90, bluRay.getPrice(), "bluRay.getPrice() test passed");
	}

	/**
	 * Check setPrice
	 */
	@Test
	void testSetPrice() {
		bluRay.setPrice(24.57);
		
		// Test will pass
		Assertions.assertEquals(24.57, bluRay.getPrice(), "bluRay.setPrice() test passed");
		
		//Test will fail 
		//Assertions.assertEquals(22.90, bluRay.getPrice(), "bluRay.setPrice() test failed");
		Assertions.assertNotEquals(22.90, bluRay.getPrice(), "bluRay.setPrice() test failed");
	}
	
	/**
	 * Check getState()
	 */
	@Test
	void testGetState() {
		// Test will pass
		Assertions.assertEquals(StatesBluRay.AVAILABLE, bluRay.getState(), "bluRay.getState() test passed");
	}

	/**
	 * Check setStat()
	 */
	@Test
	void testSetState() {
		bluRay.setState(StatesBluRay.STOLEN);
		
		// Test will pass
		Assertions.assertEquals(StatesBluRay.STOLEN, bluRay.getState(), "bluRay.setState() test passed");
		
		//Test will fail 
		//Assertions.assertEquals(StatesBluRay.AVAILABLE, bluRay.getState(), "bluRay.setState() test failed");
		Assertions.assertNotEquals(StatesBluRay.AVAILABLE, bluRay.getState(), "bluRay.setState() test failed");
	}
}
