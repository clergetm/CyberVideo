package fc;

import java.io.File;
import java.io.IOException;

import com.google.zxing.WriterException;

/**
 * Main Class
 * @author Mathys
 */
public class Tests {

	protected static QRCode film1 = new QRCode("toto", "toto tutu tata", new String[]{"JM COCO","PE SOSO"}, "DIDI", "DODO", AgeRestriction.MINUS12, new Categories[] {Categories.DRAMAS, Categories.COMEDIES});
	protected static BluRay film2 = new BluRay(22.90, StatesBluRay.AVAILABLE, "tata", "tata tutu toto", new String[]{"JM POPO","PE RORO"}, "DUDU", "DADA", AgeRestriction.EVERYONE, new Categories[] {Categories.ANIME});

	public static void main(String[] args) throws WriterException, IOException {
		// Checks for the Film class :
		// Check the type of the films
		if(!film1.getType().equals("QRCode")) {
			System.out.println("Problème avec le type : QRCode");
		}
		if(!film2.getType().equals("BluRay")) {
			System.out.println("Problème avec le type : BluRay");
		}

		// Check the titles of the films
		if(!film1.getTitle().equals("toto"))
			System.out.println("PB avec titres film1");
		if(!film2.getTitle().equals("tata"))
			System.out.println("PB avec titres film2");

		// Check the synopsis of the films
		if(!film1.getSynopsis().equals("toto tutu tata"))
			System.out.println("PB avec synopsis film1");
		if(!film2.getSynopsis().equals("tata tutu toto"))
			System.out.println("PB avec synopsis film2");

		// Check the actors list of the films
		if(!film1.getActors().equals("JM COCO, PE SOSO"))
			System.out.println("PB avec actors list film1");
		if(!film2.getActors().equals("JM POPO, PE RORO"))
			System.out.println("PB avec actors list film2");

		// Check names of the film's director 
		if(!film1.getNamesDirector().equals("DIDI DODO"))
			System.out.println("PB avec names director film1");
		if(!film2.getNamesDirector().equals("DUDU DADA"))
			System.out.println("PB avec names director film2");

		// Check age restrictions of the films
		if(!film1.getRestriction().equals(AgeRestriction.MINUS12))
			System.out.println("PB with restriction film1");
		if(!film2.getRestriction().equals(AgeRestriction.EVERYONE))
			System.out.println("PB with restriction film2");
		
		// Check the categories list of the films
		if(!film1.getCategories().equals("DRAMAS, COMEDIES"))
			System.out.println("PB with categories film1");
		if(!film2.getCategories().equals("ANIME"))
			System.out.println("PB with categories film2");
		
		
		// Checks for the BluRay class :
		// Check the price of the BluRay
		if(film2.getPrice()!=22.90)
			System.out.println("PB with getPrice film2");
		film2.setPrice(30.90);
		if(film2.getPrice()==22.90)
			System.out.println("PB with setPrice film2");
		film2.setPrice(22.90);
		
		// Check the states of the bluRay
		if(!film2.getState().equals(StatesBluRay.AVAILABLE))
			System.out.println("PB with getState film2");
		film2.setState(StatesBluRay.RENTED);
		if(film2.getState().equals(StatesBluRay.AVAILABLE))
			System.out.println("PB with setState film2");
		film2.setState(StatesBluRay.AVAILABLE);
		
		
		// Checks for the AL2000 class :
		// Check the generation of the link
		System.out.println(film1.generateLink());
		
		// Check the generation of the QRCode
		String qrCodeText = film1.generateLink();
		String filePath = "QRCode.png";
		int size = 125;
		String fileType = "png";
		File qrFile = new File(filePath);
		film1.generateQRCode(qrFile, qrCodeText, size, fileType);
	}
}
