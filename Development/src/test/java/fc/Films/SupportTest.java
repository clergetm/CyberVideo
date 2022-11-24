package fc.Films;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fc.films.BluRay;
import fc.films.QRCode;
import fc.films.StatesBluRay;
import fc.films.Support;

class SupportTest {
	
	/* Create instances of a Support for test Support class */
	private Support supportBRav = new BluRay(22, StatesBluRay.AVAILABLE);
	private Support supportBRre = new BluRay(22, StatesBluRay.RENTED);
	private Support supportQR = new QRCode();

	@Test
	void testGetType() {
		Assertions.assertEquals("BluRay", supportBRav.getType());
		Assertions.assertEquals("QRCode", supportQR.getType());

	    Assertions.assertNotEquals("QRCode", supportBRav.getType());
	    Assertions.assertNotEquals("BluRay", supportQR.getType());
	}

	@Test
	void testGetTariff() {
		Assertions.assertEquals(5, supportBRav.getTariff());
		Assertions.assertEquals(4, supportQR.getTariff());

	    Assertions.assertNotEquals(4, supportBRav.getTariff());
	    Assertions.assertNotEquals(5, supportQR.getTariff());
	}

	@Test
	void testIsAvailable() {
		Assertions.assertTrue(supportBRav.isAvailable());
		Assertions.assertFalse(supportBRre.isAvailable());
		Assertions.assertTrue(supportQR.isAvailable());
	}
}
