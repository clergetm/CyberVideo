package fc.clients.cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest {
	
	/* Create Card attributes */
    private final int num = 162;
	
    /* Create an instance of a Card for test BluRay class */
    protected Card card = new CreditCard(num);

	@Test
	final void testCard() {
		Assertions.assertEquals(num, card.number);
	}

	@Test
	final void testGetNumber() {
		Assertions.assertEquals(num, card.getNumber());
	}
}
