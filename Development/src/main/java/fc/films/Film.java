package fc.films;

import java.time.Year;

/**
 * Film is an abstract class that can implements the bluray class and the QRcode class
 * @author Clarisse
 *
 */
public class Film {

	private String title;
	private String synopsis;
	private String[] actors;
	private String directorFname;
	private String directorLname;
	private Year year;
	private Categories[] categories;
	private AgeRestriction restriction;
	private Support[] supports;
	
	/**
	 * Constructor of the class Film
	 * @param title is the title of the film
	 * @param synopsis is the summary of the film
	 * @param actors is the list of the actors of the film
	 * @param FNDirector is the first name of the director of the film
	 * @param LNDirector is the last name of the director of the film
	 * @param year is the year of the film's release
	 * @param categories is the list of the different categories to which the film belongs
	 * @param restriction is the age restriction of the films
	 * @param supports is the list of the different supports that exist for a film
	 */
	public Film(String title, String synopsis, String[] actors, String FNDirector, String LNDirector,
			Year year, Categories[] categories, AgeRestriction restriction, Support[] supports) {
		this.title=title;
		this.synopsis=synopsis;
		this.actors=actors;
		this.directorFname=FNDirector;
		this.directorLname=LNDirector;
		this.year=year;
		this.categories=categories;
		this.restriction=restriction;
		this.supports=supports;
	}

	/**
	 * 
	 * @return the title of the film
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @return the synopsis of the film
	 */
	public String getSynopsis() {
		return synopsis;
	}
	
	/**
	 * Getter of actors.
	 * @author MathysC
	 *
	 * @return film’s actors.
	 */
	public String[] getActors() {
		return this.actors;
	}

	/**
	 * 
	 * @return the names of the director of the film :
	 * "fnameDir lnameDir"
	 */
	public String getNamesDirector() {
		return directorFname+" "+directorLname;
	}

	/**
	 * 
	 * @return the first name of the director of the film :
	 */
	public String getFNDirector() {
		return directorFname;
	}

	/**
	 * 
	 * @return the last name of the director of the film :
	 */
	public String getLNDirector() {
		return directorLname;
	}
	
	/**
	 * 
	 * @return the year of the film's release
	 */
	public Year getYear() {
		return year;
	}

	/**
	 * Getter of categories.
	 * @author MathysC
	 *
	 * @return film’s categories
	 */
	public Categories[] getCategories() {
		return this.categories;
	}
	
	/**
	 * 
	 * @return the age restriction of the film
	 */
	public AgeRestriction getRestriction() {
		return restriction;
	}
	
	/**
	 * 
	 * @return the list of the different supports
	 */
	public Support[] getSupports() {
		return this.supports;
	}
}
