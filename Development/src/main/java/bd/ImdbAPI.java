package bd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ImdbAPI {
    public static void main(String[] args){
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
            getFilmTitleData(result);
            
            /*JSONObject object = new JSONObject(result);
            String title = object.getString("title");
            String year = object.getString("year");
            System.out.println("Title: "+title);
            System.out.println("Year: "+year);*/

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static String getFilmTitleData(String film){
        int titlePos = 0;
        int titleEnd = 0;
        int yearPos = 0;
        int yearEnd = 0;
        int actorPos = 0;
        int actorEnd = 0;
        int directorPos = 0;
        int directorEnd = 0;
        String filmTitle = "";
        int filmYear;
        String filmDirector = "";
        String filmActors = "";
        for(int i=0;i<250;i++){
            titlePos = film.indexOf("title",titlePos+1);
            titleEnd = film.indexOf("fullTitle",titleEnd+1);
            filmTitle = film.substring(titlePos+8, titleEnd-3);

            yearPos = film.indexOf("year",yearPos+1);
            yearEnd = film.indexOf("\"image\"",yearEnd+1);
            filmYear = Integer.parseInt(film.substring(yearPos+7, yearEnd-2));

            directorPos = film.indexOf("crew",directorPos+1);
            directorEnd = film.indexOf("(dir.)",directorEnd+1);
            filmDirector = film.substring(directorPos+7, directorEnd-1);

            actorPos = film.indexOf("(dir.)",actorPos+1);
            actorEnd = film.indexOf("\"imDbRating\"",actorEnd+1);
            filmActors = film.substring(actorPos+8, actorEnd-2);

            System.out.println(filmTitle+" "+filmYear+" "+filmDirector+" "+filmActors);
        }
        return filmTitle;
    }
}
