package fc.clients.cards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fc.films.AgeRestriction;

class SubscriberCardTest {
	
	/* Create Card attributes */
    private final int num = 162;
    private final double balance = 14.00;
    private final int limitw = 3;
	
    /* Create an instance of a Card for test BluRay class */
    protected SubscriberCard subCard = new SubscriberCard(num, balance, limitw);

	@Test
	final void testSubscriberCard() {
		Assertions.assertEquals(num, subCard.getNumber());
		Assertions.assertEquals(balance, subCard.getBalance());
		Assertions.assertEquals(limitw, subCard.getLimitWeek());
	}

	@Test
	final void testGetLimitWeek() {
		Assertions.assertEquals(limitw, subCard.getLimitWeek());
	}

	@Test
	final void testSetLimitWeek() {
		subCard.setLimitWeek(5);
		Assertions.assertEquals(5, subCard.getLimitWeek());
		Assertions.assertNotEquals(5, subCard.getLimitWeek());
		subCard.setLimitWeek(3);
	}
	
	@Test
	final void testGetBalance() {
		Assertions.assertNotEquals(balance, subCard.getBalance());
	}

	@Test
	final void testGetAgeRestriction() {
		Assertions.assertNotEquals(AgeRestriction.ALL, subCard.getAgeRestriction());
	}
	
	@Test
	final void testSetAgeRestriction() {
		subCard.setAgeRestriction(AgeRestriction.M12);
		Assertions.assertEquals(AgeRestriction.M12, subCard.getAgeRestriction());
		Assertions.assertNotEquals(AgeRestriction.ALL, subCard.getAgeRestriction());
		subCard.setAgeRestriction(AgeRestriction.ALL);
	}

	@Test
	final void testGetForbiddenCategories() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testAddForbiddenCategorie() {
		Assertions.assertNotEquals(subCard.getForbiddenCategories(), subCard.getForbiddenCategories().isEmpty());
	}

	@Test
	final void testRemoveForbiddenCategorie() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testRemoveAllForbiddenCategories() {
		Assertions.assertEquals(subCard.getForbiddenCategories(), subCard.getForbiddenCategories().isEmpty());
	}

	@Test
	final void testGetHistoric() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testAddRentalToHistoric() {
		fail("Not yet implemented"); // TODO
	}

}
