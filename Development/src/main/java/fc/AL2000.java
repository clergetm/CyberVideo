package fc;

import java.util.ArrayList;

import fc.Films.AgeRestriction;
import fc.Films.BluRay;
import fc.Films.Categories;
import fc.Films.Film;
import fc.Films.StatesBluRay;

public class AL2000 {

	private ArrayList<Film> films = new ArrayList<>();
	/**
	 * Constructor of {@code AL2000}
	 * @author MathysC
	 *
	 */
	public AL2000() {	
	}
	
	/**
	 * TODO #38 TO REMOVE AFTER LINK BETWEEN EACH PART
	 * @author MathysC
	 *
	 */
	public ArrayList<Film> TESTHYDRATE() {
		final double price = 20;
		final StatesBluRay state = StatesBluRay.AVAILABLE;
		final String title = "Ghostbusters",
				synopsis = "Three parapsychologists forced out of their university "
						+ "funding set up shop as a unique ghost removal service in New York City, "
						+ "attracting frightened yet skeptical customers.",
				director_lname = "Reitman",
				director_fname = "Ivan";
		final String[] actors = {"Bill Murray", "Dan Aykroyd", "Harold Ramis", "Ernie Hudson"};
		final AgeRestriction restriction = AgeRestriction.EVERYONE;
		final Categories[] categories = {Categories.ACTION, Categories.COMEDIES};
		
		/* Create an instance of a BluRay for test BluRay class */
		films.add(new BluRay(price, state, title, synopsis, actors, director_fname, director_lname, restriction, categories));
		
		final String title1 = "The Matrix",
				synopsis1 = "Thomas A. Anderson is a man living two lives. "
						+ "By day he is an average computer programmer and by night "
						+ "a hacker known as Neo. Neo has always questioned his reality,"
						+ " but the truth is far beyond his imagination...",
				director_lname1 = "Wachowski",
				director_fname1 = "Lana";
		final String[] actors1 = {"Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss", "Hugo Weaving"};
		final AgeRestriction restriction1 = AgeRestriction.EVERYONE;
		final Categories[] categories1 = {Categories.ACTION, Categories.DRAMAS};
		
		/* Create an instance of a Film for test Film class */
		films.add(new Film(title1, synopsis1, actors1, director_fname1, director_lname1, restriction1, categories1));
		
		return films;
		
	}
	
	public boolean collectBluRay(BluRay film) {
		if(checkIDBluRay(film))
			if(film.getState().equals(StatesBluRay.RENTED)) {
				if(showBluRayDamagedDiolog()) 
					film.setState(StatesBluRay.DAMAGED);
				else 
					film.setState(StatesBluRay.AVAILABLE);
				// CODE : Si le client a bien payé ses retards, si sa situation est reglementé
				// IHM : Afficher un message comme quoi le BluRay a bien été rendu
				return true;
			}
		// IHM : Afficher un message comme quoi le BluRay ne peut être rendu
		return false;
	}

	private static boolean checkIDBluRay(BluRay film) {
		// BDD : si ID corespond ID de la base 		
		return true;
	}

	public boolean distributeBluRay(BluRay film){
		if(checkIDBluRay(film))
			if(film.getState().equals(StatesBluRay.AVAILABLE)) {
				film.setState(StatesBluRay.RENTED);
				// IHM : Afficher un message comme quoi le BluRay a bien été ditribué
				return true;
			}
		// IHM : Afficher un message comme quoi le BluRay ne peut être distribué
		return false;	
	}

	//	public String printReceipt(Rented location) {
	//		String ticket="CyberVideo";
	//		// get le film
	//		// get la carte
	//		// get la date
	//		// get le nom de l'aboné si carte d'aboné
	//		// get le prix
	//		return ticket;
	//	}

	public void printQRCode(){
		
	}

	private boolean showBluRayDamagedDiolog() {
		// IHM : Boite de dialog qui demande si oui ou non le DVD est endomagé
		return true;
	}
}
