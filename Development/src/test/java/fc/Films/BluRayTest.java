package fc.Films;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fc.films.BluRay;
import fc.films.StatesBluRay;

class BluRayTest {

	/* Create Film attributes */
	private final double price = 20;
	private final StatesBluRay state = StatesBluRay.AVAILABLE;
	
	/* Create an instance of a BluRay for test BluRay class */
	protected BluRay bluRay = new BluRay(price, state);
	
	
	@Test
	void testGetType() {
		Assertions.assertEquals("BluRay", bluRay.getType());
	    Assertions.assertNotEquals("QRCode", bluRay.getType());
	}

	@Test
	void testGetTariff() {
	    Assertions.assertEquals(5, bluRay.getTariff());
	}

	@Test
	void testIsAvailable() {
		Assertions.assertTrue(bluRay.isAvailable());
	}

	@Test
	void testBluRay() {
		Assertions.assertEquals(price, bluRay.getPurchasePrice());
		Assertions.assertEquals(state, StatesBluRay.AVAILABLE);	
	}

	@Test
	void testGetPurchasePrice() {
		Assertions.assertEquals(price, bluRay.getPurchasePrice());
	}

	@Test
	void testGetState() {
		Assertions.assertEquals(state, bluRay.getState());
	}

	@Test
	void testSetState() {
		bluRay.setState(StatesBluRay.RENTED);
		Assertions.assertEquals(StatesBluRay.RENTED, bluRay.getState());
		Assertions.assertNotEquals(StatesBluRay.AVAILABLE, bluRay.getState());
		bluRay.setState(StatesBluRay.AVAILABLE);

	}
}
