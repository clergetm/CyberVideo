package fc.Client;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberCardTest {
    SubscriberCard sbc = new SubscriberCard(100.50, 2);
    @Test
    void getLimitWeek() {
        assertEquals(2, sbc.getLimitWeek());
    }

    @Test
    void setLimitWeek() {
        sbc.setLimitWeek(3);
        assertEquals(3, sbc.getLimitWeek());
    }

    @Test
    void getAgeRestriction(){
        sbc.setAgeRestriction(AgeRestriction.EVERYONE);
        assertEquals(AgeRestriction.EVERYONE, sbc.getAgeRestriction());
    }

    @Test
    void setAgeRestriction() {
        sbc.setAgeRestriction(AgeRestriction.EVERYONE);
        assertEquals(AgeRestriction.EVERYONE, sbc.getAgeRestriction());
    }

    @Test
    void addForbiddenCategorie() {
        sbc.addForbiddenCategorie(Categories.ANIME);
        assertEquals(Categories.ANIME, sbc.getForbiddenCategories().get(0));
    }

    @Test
    void removeForbiddenCategorie() {
        sbc.removeForbiddenCategorie(Categories.ANIME);
        assertFalse(sbc.getForbiddenCategories().contains(Categories.ANIME));
    }

    @Test
    void removeAllForbiddenCategories() {
        ArrayList<Categories> arr = new ArrayList<>();
        assertEquals(arr, sbc.getForbiddenCategories());
    }
}
