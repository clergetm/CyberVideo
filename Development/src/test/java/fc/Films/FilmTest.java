package fc.Films;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fc.films.AgeRestriction;
import fc.films.BluRay;
import fc.films.Categories;
import fc.films.Film;
import fc.films.QRCode;
import fc.films.StatesBluRay;
import fc.films.Support;
import utils.LifecycleLoggerTest;

class FilmTest implements LifecycleLoggerTest{
    /* Create Film attributes */
    protected final String title = "The Matrix";
    protected final String synopsis = "Thomas A. Anderson is a man living two lives. "
	    + "By day he is an average computer programmer and by night "
	    + "a hacker known as Neo. Neo has always questioned his reality,"
	    + " but the truth is far beyond his imagination...";
    protected final String director_lname = "Wachowski";
    protected final String director_fname = "Lana";
    protected final ArrayList<String> actors = new ArrayList<>(Arrays.asList("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss", "Hugo Weaving"));
    protected final AgeRestriction restriction = AgeRestriction.ALL;
    protected final ArrayList<Categories> categories = new ArrayList<>(Arrays.asList(Categories.ACTION, Categories.DRAMAS));
    protected final Year year = Year.of(1999);
    protected final ArrayList<Support> supports = new ArrayList<>(Arrays.asList(new BluRay(22.5, StatesBluRay.RENTED), new QRCode(""), new BluRay(22.50, StatesBluRay.AVAILABLE)));

    /* Create an instance of a Film for test Film class */
    protected final Film film = new Film(title, synopsis, actors, director_fname, director_lname, year, categories, restriction, supports);
		
	
    @Test
    void testFilm() {
	Assertions.assertEquals(title, film.getTitle());
	Assertions.assertEquals(synopsis, film.getSynopsis());
	Assertions.assertEquals(actors, film.getActors());
	Assertions.assertEquals(director_fname, film.getNamesDirector().substring(0, film.getNamesDirector().indexOf(" ")));
	Assertions.assertEquals(director_lname, film.getNamesDirector().substring(film.getNamesDirector().indexOf(" ")+1));
	Assertions.assertEquals(year, film.getYear());
	Assertions.assertEquals(categories, film.getCategories());
	Assertions.assertEquals(restriction, film.getRestriction());
	Assertions.assertEquals(supports, film.getSupports());
    }

    @Test
    void testGetTitle() {
	Assertions.assertEquals(title, film.getTitle());
    }

    @Test
    void testGetSynopsis() {
	Assertions.assertEquals(synopsis, film.getSynopsis());
    }

    @Test
    void testGetActors() {
	Assertions.assertEquals(actors, film.getActors());
    }
    
    @Test
    void testGetNamesDirector() {
	Assertions.assertEquals(director_fname+" "+director_lname, film.getNamesDirector());
    }

    @Test
    void testGetYear() {
	Assertions.assertEquals(year, film.getYear());
    }

    @Test
    void testGetCategories() {
	Assertions.assertEquals(categories, film.getCategories());
    }

    @Test
    final void testGetRestriction() {
	Assertions.assertEquals(restriction, film.getRestriction());
    }

    @Test
    void testGetSupportsType() {
	Assertions.assertEquals(supports, film.getSupports());
    }

}
