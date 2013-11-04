package facebook;

import utilities.Parser;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import database.MysqlPortal;

public class FacebookClient {

	public void getData(String pathname){
		
		//Parser 
		HttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);

		File file = new File(pathname);
		
		try {
			
			PrintWriter writer = new PrintWriter("./files/json_movies.txt", "UTF-8");
			BufferedReader fileReader = new BufferedReader(new FileReader(file));

			while (fileReader.ready()){

				// Read from the text file
				String facebookPage = fileReader.readLine();
				
				// Create a graph request
				facebookPage = facebookPage.replaceAll("www", "graph");
				
				// Changes https://graph.facebook.com/pages/Armageddon/9283931?ref=br_rs 
				// to https://graph.facebook.com/9283931?ref=br_rs
				if (facebookPage.matches("https://graph.facebook.com/.*/.*/[0-9]+\\?ref=br_rs$"))
					facebookPage = facebookPage.replaceAll("pages/.*/", "");
				
				// Get request
				HttpGet get = new HttpGet(facebookPage);

				// Performs GET request and receives response
				HttpResponse response = client.execute(get);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent(), "UTF-8"));

				StringBuilder builder = new StringBuilder();

				for (String line = null; (line = reader.readLine()) != null; ) 
					builder.append(line).append("\n");

				// Verifies "error" doesn't exist in returned object
				if (!builder.toString().contains("\"error\"")) {
				
					System.out.println(builder.toString());

					JSONTokener tokener = new JSONTokener(builder.toString());
					JSONObject info = new JSONObject(tokener);
					
					// Enter information for each movie into the database
					mysql(info);
				}
			}
			writer.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts movie title into Movie table
	 * Inserts total likes into Movie table
	 * 
	 * @param movie JSON movie object
	 * @throws JSONException
	 */
	private void mysql(JSONObject movie) throws JSONException {
		
		MysqlPortal mysql = new MysqlPortal();
		
		// Inserts title into the database
		if(movie.has("name"))
			mysql.insert(movie.get("name").toString(), "Movie", "Title");
		
		// Inserts likes into the database
		if (movie.has("likes"))
			mysql.update("Movie", "totalLikes", Integer.parseInt(movie.get("likes").toString()), "title", movie.get("name").toString());
	}
}
