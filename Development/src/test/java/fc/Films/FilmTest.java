package fc.Films;

import java.time.Year;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fc.LifecycleLoggerTest;
import fc.films.AgeRestriction;
import fc.films.BluRay;
import fc.films.Categories;
import fc.films.Film;
import fc.films.QRCode;
import fc.films.StatesBluRay;
import fc.films.Support;

class FilmTest implements LifecycleLoggerTest{

	
	/* Create Film attributes */
	private final String title = "The Matrix",
			synopsis = "Thomas A. Anderson is a man living two lives. "
					+ "By day he is an average computer programmer and by night "
					+ "a hacker known as Neo. Neo has always questioned his reality,"
					+ " but the truth is far beyond his imagination...",
			director_lname = "Wachowski",
			director_fname = "Lana";
	private final String[] actors = {"Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss", "Hugo Weaving"};
	private final AgeRestriction restriction = AgeRestriction.EVERYONE;
	private final Categories[] categories = {Categories.ACTION, Categories.DRAMAS};
	private final Year year = Year.of(1999);
	private final Support[] supports = {new BluRay(22.5, StatesBluRay.RENTED), new QRCode(), new BluRay(22.50, StatesBluRay.AVAILABLE)};
	
	/* Create an instance of a Film for test Film class */
	protected Film film = new Film(title, synopsis, actors, director_fname, director_lname, year, categories, restriction, supports);
	
	
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
		Assertions.assertEquals(supports, film.getSupportsType());
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
		Assertions.assertEquals(supports, film.getSupportsType());
	}

}
