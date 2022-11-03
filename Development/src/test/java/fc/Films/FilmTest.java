package fc.Films;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fc.LifecycleLoggerTest;

class FilmTest implements LifecycleLoggerTest {
	
	protected static Film film = new QRCode("toto", "toto tutu tata", new String[]{"JM COCO","PE SOSO"}, "DIDI", "DODO", AgeRestriction.MINUS12, new Categories[] {Categories.DRAMAS, Categories.COMEDIES});
	
	@Test
	void testFilm() {
		Assertions.assertEquals("toto", film.title, "film.title test passed");
		Assertions.assertEquals("toto tutu tata", film.synopsis, "film.synopsis test passed");
		Assertions.assertEquals("JM COCO, PE SOSO", film.getActors(), "film.getActors() test passed");
		Assertions.assertEquals("DIDI", film.FNameDirector, "film.FNameDirector test passed");
		Assertions.assertEquals("DODO", film.LNameDirector, "film.LNameDirector test passed");
		Assertions.assertEquals("DRAMAS, COMEDIES", film.getCategories(), "film.getCategories() test passed");
		Assertions.assertEquals(AgeRestriction.MINUS12, film.restriction, "film.restriction test passed");
	}

	@Test
	void testGetType() {
		// Test will pass
		Assertions.assertEquals("QRCode", film.getType(), "film.getType() test passed");
	}

	@Test
	void testGetTitle() {
		Assertions.assertEquals("toto", film.getTitle());
	}

	@Test
	void testGetSynopsis() {
		Assertions.assertEquals("toto tutu tata", film.getSynopsis());
	}

	@Test
	void testGetActors() {
		Assertions.assertEquals("JM COCO, PE SOSO", film.getActors());
	}

	@Test
	void testGetNamesDirector() {
		Assertions.assertEquals("DIDI DODO", film.getNamesDirector());
	}

	@Test
	void testGetRestriction() {
		Assertions.assertEquals(AgeRestriction.MINUS12, film.getRestriction());
	}

	@Test
	void testGetCategories() {
		Assertions.assertEquals("DRAMAS, COMEDIES", film.getCategories());
	}

}
