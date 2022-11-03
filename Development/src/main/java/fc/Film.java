package fc;

public abstract class Film {

	protected String title;
	protected String synopsis;
	protected String[] actors;
	protected String FNameDirector;
	protected String LNameDirector;
	protected AgeRestriction restriction;
	protected Categories[] categories;
	
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
	
	
	public abstract String getType();

	public String getTitle() {
		return title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getActors() {
		String actorsList="";
		for (int i=0;i<actors.length-1;i++) {
			actorsList+=actors[i]+", ";
		}
		actorsList+=actors[actors.length-1];
		return actorsList;
	}

	public String getNamesDirector() {
		return FNameDirector+" "+LNameDirector;
	}

	public AgeRestriction getRestriction() {
		return restriction;
	}

	public String getCategories() {
		String categoryList="";
		for (int i=0;i<categories.length-1;i++) {
			categoryList+=categories[i]+", ";
		}
		categoryList+=categories[categories.length-1];
		return categoryList;
	}	
}
