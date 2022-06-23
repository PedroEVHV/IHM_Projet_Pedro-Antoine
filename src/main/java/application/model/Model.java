package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
//import javafx.stage.Screen;
import java.io.IOException;
import java.io.Reader;
//import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import application.geohash.GeoHashHelper;
import application.geohash.Location;
import org.json.JSONArray;
import org.json.JSONObject;


public class Model {
    private String searchBasic;
    private String currSearch;
    private int occMax = 0;
    private HashMap<String, Integer> geoHashList;

    public int[] legendCouleur() {
    	int[] buf = new int[8];
    	
    	for(int i = 1; i < 8; i++) {
    		buf[i]= (int) (occMax/(Math.pow((8-i), 4)));
    		System.out.println(buf[i]);
    	}
    	return buf;
    }
    
    private double[] center(JSONArray j1, JSONArray j2) {
		double[] buf = {0, 0};
		buf[0] = (j1.getDouble(0) + j2.getDouble(0))/2;
		buf[1] = (j1.getDouble(1) + j2.getDouble(1))/2;
		
		return buf;
	}

    public Model() {
        searchBasic = " https://api.obis.org/v3/taxon/complete/verbose/";
    }

    public void setCurrSearch(String currSearch) {
        this.currSearch = searchBasic + currSearch;
    }
    
	
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public JSONObject fileToJSONObject(String file) {
		JSONObject jsonRoot = null;
		try(Reader reader = new FileReader(file)){
			BufferedReader rd = new BufferedReader(reader);
			String jsonText = readAll(rd);
			jsonRoot = new JSONObject(jsonText);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return jsonRoot;
	}
	
	int occurance = 0;
	public HashMap<String, Integer> coordFromJSONObject(JSONObject obj) {
		geoHashList = new HashMap<>();
		JSONArray featuresArray = obj.getJSONArray("features");
		featuresArray.forEach(item->{
			JSONObject object = (JSONObject) item;
			JSONObject geometry = object.getJSONObject("geometry");
			
			JSONObject occ = object.getJSONObject("properties");
			occurance = (int) occ.get("n");
			if(occurance > occMax) {
				occMax = occurance;
			}
			
			JSONArray coordsArray = geometry.getJSONArray("coordinates");
			coordsArray.forEach(item2->{
				JSONArray coord = (JSONArray) item2;
				double[] c = center((JSONArray) coord.get(0), (JSONArray) coord.get(2));
				Location loc = new Location("GeoHash", c[1], c[0]);
				geoHashList.put(GeoHashHelper.getGeohash(loc, 3), occurance);
			});		
		});
		return geoHashList;
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


        }
    }
}
