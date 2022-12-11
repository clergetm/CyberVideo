package bd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * @author EvanP
 * Class that connects to IMDB API and select 250 films to import in database.
 */
public class ImdbAPI {

    /**
   * Connection management with the imdb api.
   * @author EvanP
   * 
   * @param
   * @return
   */

    public static String[][] getData(){
        HttpURLConnection connection = null;
        final String myAPIKey = "k_sfenmowl";
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
            String[][] filmData = getFilmData(result);

            return filmData;

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
    static String[][] getFilmData(String film){
        String [][] filmData;
        filmData = new String[250][8];
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
        String filmTitle = "";
        String filmYear;
        String filmDirector = "";
        String filmDirectorFirstName = "";
        String filmDirectorLastName = "";
        String filmActors = "";
        String[] directorNames;
        for(int i=0;i<250;i++){
            titlePos = film.indexOf("title",titlePos+1);
            titleEnd = film.indexOf("fullTitle",titleEnd+1);
            filmTitle = film.substring(titlePos+8, titleEnd-3);

            yearPos = film.indexOf("year",yearPos+1);
            yearEnd = film.indexOf("\"image\"",yearEnd+1);
            filmYear = film.substring(yearPos+7, yearEnd-2);

            directorPos = film.indexOf("crew",directorPos+1);
            directorEnd = film.indexOf("(dir.)",directorEnd+1);
            filmDirector = film.substring(directorPos+7, directorEnd-1);
            directorNames = filmDirector.split(" ");
            filmDirectorFirstName = directorNames[0];
            filmDirectorLastName = directorNames[1];

            actorPos = film.indexOf("(dir.)",actorPos+1);
            actorEnd = film.indexOf("\"imDbRating\"",actorEnd+1);
            filmActors = film.substring(actorPos+8, actorEnd-2);

            filmData[i][0] = Integer.toString(i); //filmID
            filmData[i][1] = filmTitle; //title
            filmData[i][2] = filmYear; //year
            filmData[i][3] = "Synopsis not available"; //synopsis
            filmData[i][4] = filmDirectorFirstName; //DirectorFirstName
            filmData[i][5] = filmDirectorLastName; //DirectorLastName

            generateNumber = rand.nextInt(5); //restrictedAge
            switch(generateNumber){
                case 0:
                    filmData[i][6] = "M10" ;
                    break;
                case 1:
                    filmData[i][6] = "M12" ;
                    break;
                case 2:
                    filmData[i][6] = "M16" ;
                    break;
                case 3:
                    filmData[i][6] = "M18" ;
                    break;
                case 4:
                    filmData[i][6] = "ALL" ;
                    break;
            }
            
            filmData[i][7] = filmActors;

        }
        return filmData;
    }
}
