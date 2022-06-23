package application.model;

import javafx.stage.Screen;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.*;

public class Model {
    private String searchBasic;
    private String currSearch;

    public Model() {
        searchBasic = " https://api.obis.org/v3/taxon/complete/verbose/";
    }

    public void setCurrSearch(String currSearch) {
        this.currSearch = searchBasic + currSearch;
    }

    public void queryDataVerbose() throws IOException {
        URL url = new URL(currSearch);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if(responseCode != 200) {
            throw new RuntimeException("code:" + responseCode);
        } else {
            StringBuilder infoString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                infoString.append(scanner.nextLine());
            }

            scanner.close();
            System.out.println(infoString.charAt(0));




        }
    }
}
