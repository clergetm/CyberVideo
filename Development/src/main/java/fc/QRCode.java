package fc;

public class QRCode extends Film {

	public QRCode(String title, String synopsis, String[] actors, String FNDirector, String LNDirector, AgeRestriction restriction, Categories[] categories) {
		super(title, synopsis, actors, FNDirector, LNDirector, restriction, categories);
	}

	@Override
	public String getType() {
		return "QRCode";
	}
	
	public void generate() {
		
	}

}
