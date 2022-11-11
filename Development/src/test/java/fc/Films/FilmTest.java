package fc.Films;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fc.LifecycleLoggerTest;

class FilmTest implements LifecycleLoggerTest {
	
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
	
	/* Create an instance of a Film for test Film class */
	protected Film film = new Film(title, synopsis, actors, director_fname, director_lname, restriction, categories);
	
	/**
	 * Check the constructor of Film
	 */
	@Test
	void testFilm() {
		Assertions.assertEquals(title, film.title, "film.title test passed");
		Assertions.assertEquals(synopsis, film.synopsis, "film.synopsis test passed");
		Assertions.assertEquals(actors, film.getActors(), "film.getActors() test passed");
		Assertions.assertEquals(director_fname, film.FNameDirector, "film.FNameDirector test passed");
		Assertions.assertEquals(director_lname, film.LNameDirector, "film.LNameDirector test passed");
		Assertions.assertEquals(categories, film.getCategories(), "film.getCategories() test passed");
		Assertions.assertEquals(restriction, film.restriction, "film.restriction test passed");
	}

	/**
	 * Check getType()
	 */
	@Test
	void testGetType() {
		Assertions.assertEquals("QRCode", film.getType(), "film.getType() test passed");
	}

	/**
	 * Check getTitle()
	 */
	@Test
	void testGetTitle() {
		Assertions.assertEquals(title, film.getTitle());
	}

	/**
	 * Check getSynopsis()
	 */
	@Test
	void testGetSynopsis() {
		Assertions.assertEquals(synopsis, film.getSynopsis());
	}

	/**
	 * Check getActors()
	 */
	@Test
	void testGetActors() {
		Assertions.assertEquals(actors, film.getActors());
	}

	/**
	 * Check getNamesDirector()
	 */
	@Test
	void testGetNamesDirector() {
		Assertions.assertEquals(director_fname+" "+director_lname, film.getNamesDirector());
	}

	/**
	 * Check getRestriction()
	 */
	@Test
	void testGetRestriction() {
		Assertions.assertEquals(restriction, film.getRestriction());
	}

	/**
	 * Check getCategories()
	 */
	@Test
	void testGetCategories() {
		Assertions.assertEquals(categories, film.getCategories());
	}
	
	/**
	 * Test Generation of the QRCode.
	 * @author MathysC
	 *
	 */
	@Test
	void testGenerateQRCode() {		
		File f = new File("QRCode.png");

		try {
			// Check if not exist a QRCode.png
			Assertions.assertFalse(f.exists());
			
			// Check the link and generate the QRCode.png
			String link = "https://www.cybervideo/location/"+title+".com";
			Assertions.assertEquals(link, film.generateQRCode(), "film.generateQRCode test passed");
			
			// Check is the file QRCode.png exist and if is a file
			Assertions.assertTrue(f.exists());
			Assertions.assertTrue(f.isFile());
		}
		finally {
			// Delete the file for the other tests
			f.delete();
		}
	}
}
