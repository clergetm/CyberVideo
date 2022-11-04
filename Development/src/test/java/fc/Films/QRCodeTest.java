package fc.Films;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.zxing.WriterException;

import fc.LifecycleLoggerTest;

import java.io.File;

class QRCodeTest implements LifecycleLoggerTest{

	protected static QRCode qrCode = new QRCode("toto", "toto tutu tata", new String[]{"JM COCO","PE SOSO"}, "DIDI", "DODO", AgeRestriction.MINUS12, new Categories[] {Categories.DRAMAS, Categories.COMEDIES});

	/**
	 * Check the getType methode
	 */
	@Test
	void testGetType() {
		// Test will pass
		Assertions.assertEquals("QRCode", qrCode.getType(), "qrCode.getType() test passed");

		//Test will fail 
		//Assertions.assertEquals("BluRay", qrCode.getType(), "qrCode.getType() test failed");
		Assertions.assertNotEquals("BluRay", qrCode.getType(), "qrCode.getType() test failed");	
	}

	/**
	 * Check the constructor of QRCode
	 */
	@Test
	void testQRCode() {
		Assertions.assertEquals("toto", qrCode.title, "qrCode.title test passed");
		Assertions.assertEquals("toto tutu tata", qrCode.synopsis, "qrCode.synopsis test passed");
		Assertions.assertEquals("JM COCO, PE SOSO", qrCode.getActors(), "qrCode.getActors() test passed");
		Assertions.assertEquals("DIDI", qrCode.FNameDirector, "qrCode.FNameDirector test passed");
		Assertions.assertEquals("DODO", qrCode.LNameDirector, "qrCode.LNameDirector test passed");
		Assertions.assertEquals("DRAMAS, COMEDIES", qrCode.getCategories(), "qrCode.getCategories() test passed");
		Assertions.assertEquals(AgeRestriction.MINUS12, qrCode.restriction, "qrCode.restriction test passed");
	}

	/**
	 * Check if the link for the QRCode generation is the good link
	 * Check if a file.png is generate
	 * It is voluntary not to test the reading of the QRcode
	 * @throws IOException 
	 * @throws WriterException 
	 */
	@Test
	void testGenerateQRCode() throws WriterException, IOException {		
		// Check if not exist a QRCode.png
		File f = new File("QRCode.png");
		Assertions.assertFalse(f.exists());
		
		// Check the link and generate the QRCode.png
		Assertions.assertEquals("https://www.cybervideo/location/toto.com", qrCode.generateQRCode(), "qrCode.generateQRCode test passed");
		
		// Check is the file QRCode.png exist and if is a file
		Assertions.assertTrue(f.exists());
		Assertions.assertTrue(f.isFile());
		
		// Delete the file for the other tests
		f.delete();
	}

	//https://patouche.github.io/2015/01/17/temporary-folder-rule/
}
