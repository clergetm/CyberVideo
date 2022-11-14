package fc;

import fc.Films.BluRay;
import fc.Films.StatesBluRay;
import fc.Films.Film;

import java.sql.*;
import java.util.ArrayList;

public class AL2000 {
	String db;
	String userName;
	String pw;
	ArrayList<Film> filmList;
	//Film attrs
	String title, synopsis, directorLastName, directorFirstName, restrictedAge;
	int filmID;
	ArrayList<String> actors;

	public AL2000(){
		db = "";
		userName = "";
		pw = "";
		filmList = new ArrayList<String>();
		actors = new ArrayList<String>();

		try{
			Connection conn = DriverManager.getConnection();
			String sqlReq = "SELECT * FROM Films;";
			Statement stm = conn.createStatement();
			ResultSet res = stm.executeQuery(sqlReq);

			while (res.next()){
				//Get db informations
			}
		}
		catch(SQLException e){
			System.out.println(e.getSQLState());
		}
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
	//		String ticket="CyberViveo";
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