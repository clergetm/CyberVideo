package fc.clients.cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreditCardTest {

	/* Create Card attributes */
    private final int num = 162;
	
    /* Create an instance of a Card for test BluRay class */
    protected CreditCard creditCard = new CreditCard(num);
    
	@Test
	final void testCreditCard() {
		Assertions.assertEquals(num, creditCard.number);
	}

}
