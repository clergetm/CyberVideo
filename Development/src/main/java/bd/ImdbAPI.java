package bd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * @author EvanP
 * Class that connects to IMDB API
 */
public class ImdbAPI {

    protected Connection connect = null;

    public ImdbAPI(Connection conn){
        this.connect = conn;
    }

    /**
   * Connection management with the imdb api.
   * @author EvanP
   * 
   * @param
   * @return
   */

    public void getData(){
        HttpURLConnection connection = null; 
        final String myAPIKey = "k_f1j4cxkh";
        try{
            URL url = new URL("https://imdb-api.com/en/API/Top250Movies/"+myAPIKey);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder responce = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                responce.append(line);
                responce.append("\r");
            }
            reader.close();
            String result = responce.toString();
            insertFilmData(result);

        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    /**
   * Get Film Data from IMDB.
   * @author EvanP
   * 
   * @param film the film information retrieved in html
   * @return a string array containing the information of the 250 films
   */
    public void insertFilmData(String film){

        try{
            int titlePos = 0;
            int titleEnd = 0;
            int yearPos = 0;
            int yearEnd = 0;
            int actorPos = 0;
            int actorEnd = 0;
            int directorPos = 0;
            int directorEnd = 0;
            Random rand = new Random();
            int generateNumber;
            String filmRestrictedAge = "";
            int filmCategorieID = 1;
            String filmTitle = "";
            int filmYear = 0;
            String filmDirector = "";
            String filmDirectorFirstName = "";
            String filmDirectorLastName = "";
            String filmActors = "";
            String[] directorNames;

            for(int i=0;i<50;i++){
                titlePos = film.indexOf("title",titlePos+1);
                titleEnd = film.indexOf("fullTitle",titleEnd+1);
                filmTitle = film.substring(titlePos+8, titleEnd-3);

                yearPos = film.indexOf("year",yearPos+1);
                yearEnd = film.indexOf("\"image\"",yearEnd+1);
                filmYear = Integer.parseInt(film.substring(yearPos+7, yearEnd-2));

                directorPos = film.indexOf("crew",directorPos+1);
                directorEnd = film.indexOf("(dir.)",directorEnd+1);
                filmDirector = film.substring(directorPos+7, directorEnd-1);
                directorNames = filmDirector.split(" ");
                filmDirectorFirstName = directorNames[0];
                filmDirectorLastName = directorNames[1];

                actorPos = film.indexOf("(dir.)",actorPos+1);
                actorEnd = film.indexOf("\"imDbRating\"",actorEnd+1);
                filmActors = film.substring(actorPos+8, actorEnd-2);

                generateNumber = rand.nextInt(5); //restrictedAge
                switch(generateNumber){
                    case 0:
                        filmRestrictedAge = "M10" ;
                        break;
                    case 1:
                        filmRestrictedAge = "M12" ;
                        break;
                    case 2:
                        filmRestrictedAge = "M16" ;
                        break;
                    case 3:
                        filmRestrictedAge = "M18" ;
                        break;
                    case 4:
                        filmRestrictedAge = "ALL" ;
                        break;
                }

                generateNumber = rand.nextInt(4); //catName
                switch(generateNumber){
                    case 0:
                        filmCategorieID = 1 ;
                        break;
                    case 1:
                        filmCategorieID = 2 ;
                        break;
                    case 2:
                        filmCategorieID = 3 ;
                        break;
                    case 3:
                        filmCategorieID = 4 ;
                        break;
                }

                String synopsis = "Synopsis not available";

                String sql = "INSERT INTO Films (title,year,synopsis,directorFirstName,directorLastName,restrictedAge) Values(?,?,?,?,?,?)";
                PreparedStatement preparedStmt = this.connect.prepareStatement(sql);
                preparedStmt.setString(1, filmTitle);
                preparedStmt.setInt(2, filmYear);
                preparedStmt.setString(3, synopsis);
                preparedStmt.setString(4, filmDirectorFirstName);
                preparedStmt.setString(5, filmDirectorLastName);
                preparedStmt.setString(6, filmRestrictedAge);
                preparedStmt.execute();
                preparedStmt.close();

                sql = "INSERT INTO FilmsCategories (categorieID) Values(?)";
                preparedStmt = this.connect.prepareStatement(sql);
                preparedStmt.setInt(1, filmCategorieID);
                preparedStmt.execute();
                preparedStmt.close();

                sql = ("INSERT INTO SupportFilms Values(supportFilmID_sequence.nextval,?,'BR')");
                preparedStmt = this.connect.prepareStatement(sql);
                preparedStmt.setInt(1, i+1);
                preparedStmt.execute();
                this.connect.createStatement().executeUpdate("INSERT INTO BluRays (supportFilmID,price) Values(supportFilmID_sequence.currval,10.0)");
                preparedStmt.close();

                sql = ("INSERT INTO SupportFilms Values(supportFilmID_sequence.nextval,?,'QR')");
                preparedStmt = this.connect.prepareStatement(sql);
                preparedStmt.setInt(1, i+1);
                preparedStmt.execute();
                this.connect.createStatement().executeUpdate("INSERT INTO QRCodes (supportFilmID,link) Values(supportFilmID_sequence.currval,'https://www.cybervideo/location/qrcode.com')");
                preparedStmt.close();

                String[] ActorNames = filmActors.split(" ");
                String ActorFirstName = ActorNames[0];
                ActorNames = ActorNames[1].split(",");
                String ActorLastName = ActorNames[0];
                sql = "SELECT actorID FROM Actors WHERE actorFirstName = ? AND actorLastName = ? ";
                preparedStmt = this.connect.prepareStatement(sql);
                preparedStmt.setString(1,ActorFirstName);
                preparedStmt.setString(2,ActorLastName);
                ResultSet rs = preparedStmt.executeQuery();
                if(!rs.next()){ //If actor in the film is not in Actors table then we add him
                    sql = "INSERT INTO Actors (actorFirstName,actorLastName) Values(?,?)";
                    preparedStmt = this.connect.prepareStatement(sql);                   
                    preparedStmt.setString(1, ActorFirstName);
                    preparedStmt.setString(2, ActorLastName);
                    preparedStmt.execute();
                    preparedStmt.close();
                }
                
                sql = "INSERT INTO FilmsActors (actorID) SELECT actorID FROM Actors WHERE actorFirstName = ? AND actorLastName = ? ";
                preparedStmt = this.connect.prepareStatement(sql);
                preparedStmt.setString(1, ActorFirstName);
                preparedStmt.setString(2, ActorLastName);
                preparedStmt.execute();
                preparedStmt.close();

            }
        }catch (SQLException e) { e.printStackTrace(); }
    }
}
