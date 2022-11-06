package fc.Films;

/**
 * Film is an abstract class that can implements the bluray class and the QRcode class
 * @author Clarisse
 *
 */
public abstract class Film {

	protected String title;
	protected String synopsis;
	protected String[] actors;
	protected String FNameDirector;
	protected String LNameDirector;
	protected AgeRestriction restriction;
	protected Categories[] categories;
	
	
	/**
	 * Constructor of the class Film
	 * @param title is the title of the film
	 * @param synopsis is the summary of the film
	 * @param actors is the list of the actors of the film
	 * @param FNDirector is the first name of the director of the film
	 * @param LNDirector is the last name of the director of the film
	 * @param restriction is the age restriction of the films
	 * @param categories is the list of the different categories to which the film belongs
	 */
	public Film(String title, String synopsis, String[] actors, String FNDirector,
			String LNDirector, AgeRestriction restriction, Categories[] categories) {
		this.title=title;
		this.synopsis=synopsis;
		this.actors=actors;
		this.FNameDirector=FNDirector;
		this.LNameDirector=LNDirector;
		this.restriction=restriction;
		this.categories=categories;
	}
	
	/**
	 * Allows to know the film support
	 * @return the film support "BluRay" or "QRCode"
	 */
	public abstract String getType();

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
	 * 
	 * @return the actors of the film : 
	 * "fNameActeur1 lNameActeur1, fNameActeur2 lNameActeur2" 
	 */
	public String getActors() {
		String actorsList="";
		for (int i=0;i<actors.length-1;i++) {
			actorsList+=actors[i]+", ";
		}
		actorsList+=actors[actors.length-1];
		return actorsList;
	}

	/**
	 * 
	 * @return the names of the director of the film :
	 * "fnameDir lnameDir"
	 */
	public String getNamesDirector() {
		return FNameDirector+" "+LNameDirector;
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
	 * @return the categories of the film
	 */
	public String getCategories() {
		String categoryList="";
		for (int i=0;i<categories.length-1;i++) {
			categoryList+=categories[i]+", ";
		}
		categoryList+=categories[categories.length-1];
		return categoryList;
	}	
}
