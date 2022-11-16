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
	int filmID;
	String title, synopsis, directorLastName, directorFirstName, restrictedAge;
	ArrayList<String> actors, categories;

	public AL2000(){
		db = "";
		userName = "";
		pw = "";
		filmList = new ArrayList<Film>();
		actors = new ArrayList<String>();
		categories = new ArrayList<String>();

		try{
			Connection conn = DriverManager.getConnection();
			String reqFilms = "SELECT * FROM Films;";
			Statement stm = conn.createStatement();
			ResultSet res = stm.executeQuery(sqlReq);

			//Getting films from db
			while (res.next()){
				try {
					filmID = Integer.parseInt(res.getString("filmID"));
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
				}
				title = res.getString("title");
				synopsis = res.getString("synopsis");
				directorFirstName = res.getString("directorFirstName");
				directorLastName = res.getString("directorLastName");
				restrictedAge = res.getString("restrictedAge");
				//Actors extraction
				String reqt = "SELECT actorFirstName, actorLastName FROM FilmsActors F INNER JOIN Actors A ON F.actorID = A.actorID WHERE filmID = "+filmID;
				ResultSet restm = stm.executeQuery(reqt);
				while (restm.next()){
					actors.add(restm.getString("actorFirstName") + "," + restm.getString("actorLastName"));
				}
				//Categories extraction
				reqt = "SELECT catName FROM FilmsCategories F INNER JOIN Categories C ON F.categorieID = C.categorieID WHERE filmID = "+filmID;
				restm = stm.executeQuery(reqt);
				while (restm.next()){
					categories.add(restm.getString("catName"));
				}
				//Adding film object to filmList
				filmList.add(new Film(title, synopsis, actors, directorFirstName, directorLastName, restrictedAge, categories));
			}

		}
		catch(SQLException e){
			e.printStackTrace();
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