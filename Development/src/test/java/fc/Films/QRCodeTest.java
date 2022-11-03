package fc.Films;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QRCodeTest {

	protected static QRCode qrCode = new QRCode("toto", "toto tutu tata", new String[]{"JM COCO","PE SOSO"}, "DIDI", "DODO", AgeRestriction.MINUS12, new Categories[] {Categories.DRAMAS, Categories.COMEDIES});

	@Test
	void testGetType() {
		// Test will pass
		Assertions.assertEquals("QRCode", qrCode.getType(), "qrCode.getType() test passed");

		//Test will fail 
		//Assertions.assertEquals("BluRay", qrCode.getType(), "qrCode.getType() test failed");
		Assertions.assertNotEquals("BluRay", qrCode.getType(), "qrCode.getType() test failed");	
	}

	@Test
	void testQRCode() {
		// Tests will pass
		Assertions.assertEquals("toto", qrCode.title, "qrCode.title test passed");
		Assertions.assertEquals("toto tutu tata", qrCode.synopsis, "qrCode.synopsis test passed");
		Assertions.assertEquals("JM COCO, PE SOSO", qrCode.getActors(), "qrCode.getActors() test passed");
		Assertions.assertEquals("DIDI", qrCode.FNameDirector, "qrCode.FNameDirector test passed");
		Assertions.assertEquals("DODO", qrCode.LNameDirector, "qrCode.LNameDirector test passed");
		Assertions.assertEquals("DRAMAS, COMEDIES", qrCode.getCategories(), "qrCode.getCategories() test passed");
		Assertions.assertEquals(AgeRestriction.MINUS12, qrCode.restriction, "qrCode.restriction test passed");
	}

//	@Test
//	void testGenerateQRCode() {
//		fail("Not yet implemented");
//	}

	@Test
	void testGenerateLink() {
		Assertions.assertEquals("https://www.cybervideo/location/toto.com", qrCode.generateLink(), "qrCode.generateLink() test passed");
	}

}
