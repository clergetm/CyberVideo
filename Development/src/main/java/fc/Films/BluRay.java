package fc.Films;

public class BluRay extends Film {

	private double price;
	private StatesBluRay state;
	
	public BluRay(double price, StatesBluRay state, String title, 
			String synopsis, String[] actors, String FNDirector, String LNDirector,
			AgeRestriction restriction, Categories[] categories) {
		super(title, synopsis, actors, FNDirector, LNDirector, restriction, categories);

		this.price=price;
		this.state=state;
	}
	
	@Override
	public String getType() {
		return "BluRay";
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public StatesBluRay getState() {
		return state;
	}
	
	public void setState(StatesBluRay state) {
		this.state=state;
		
	}

}
