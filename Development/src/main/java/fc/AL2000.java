package fc;

import java.io.File;
import java.io.IOException;
import com.google.zxing.WriterException;

import fc.Films.BluRay;
import fc.Films.QRCode;
import fc.Films.StatesBluRay;


public class AL2000 {

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
	//		String ticket="CyberViveo";
	//		// get le film
	//		// get la carte
	//		// get la date
	//		// get le nom de l'aboné si carte d'aboné
	//		// get le prix
	//		return ticket;
	//	}

	public void printQRCode(QRCode film) throws WriterException, IOException{
		String qrCodeText = film.generateLink();
		String filePath = "QRCode.png";
		int size = 125;
		String fileType = "png";
		File qrFile = new File(filePath);
		film.generateQRCode(qrFile, qrCodeText, size, fileType);
		System.out.println("DONE");
	}

	private boolean showBluRayDamagedDiolog() {
		// IHM : Boite de dialog qui demande si oui ou non le DVD est endomagé
		return true;
	}


}
