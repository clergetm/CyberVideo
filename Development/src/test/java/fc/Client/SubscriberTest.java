package fc.Client;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberTest {

    DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    Date d ;
    {
        try {
            d = df.parse("10/09/2025");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    Subscriber sub = new Subscriber("stingo", "bingo", d);

    @Test
    void getFirstNameTest() {
        assertEquals("stingo", sub.getFirstName());
    }

    @Test
    void getLastNameTest() {
        assertEquals("bingo", sub.getLastName());
    }

    @Test
    void getBirthDateTest() {
        assertEquals(d, sub.getBirthDate());
    }

    @Test
    void askCardTest() {
    }
}