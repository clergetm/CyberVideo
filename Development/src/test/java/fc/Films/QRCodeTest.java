package fc.Films;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.zxing.WriterException;

class QRCodeTest {

	/* Create an instance of a BluRay for test BluRay class */
	protected QRCode qrcode = new QRCode();
	
	@Test
	void testGetType() {
		Assertions.assertEquals("QRCode", qrcode.getType());
	    Assertions.assertNotEquals("BluRay", qrcode.getType());
    }

	@Test
	void testGetTariff() {
		Assertions.assertEquals(4, qrcode.getTariff());
	}

	@Test
	void testIsAvailable() {
		Assertions.assertTrue(qrcode.isAvailable());
	}

	@Test
	void testGenerateQRCode() throws WriterException, IOException {
		Assertions.assertEquals("https://www.cybervideo/location/qrcode.com", qrcode.generateQRCode());
	}
}
