package examples;


import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SongSheetRepository{
    private static final String BASE_URL = "https://api.apispreadsheets.com/data";
    private static final String FILE_ID = "fzMynCiZEOH4NYK5";

    public void create(Song song) throws Exception {

        String urlstr = BASE_URL + "/" + FILE_ID + "/"; // see apispreadsheets.com

        int responseCode = this.sendPostRequest(urlstr, new Gson().toJson(song));
        if (responseCode != HttpURLConnection.HTTP_CREATED) {
            System.out.println("Unexpected POST (create) response code: " + responseCode);
        }
    }

    private int sendPostRequest(String urlstr, String json) throws Exception {

        URL url = new URL(urlstr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();
        con.disconnect();

        int responseCode = con.getResponseCode();
        //System.out.println("POST response code: " + responseCode);

        return responseCode;
    }

}