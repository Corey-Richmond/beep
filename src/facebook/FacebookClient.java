/* Authors: Corey Richmond & Donald Siuchninski
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import database.MysqlPortal;

public class FacebookClient {
	
	public enum Domain {MOVIE, ATHLETE, ARTIST, TEAM};

	public void getData(String inputFileName, String outputFileName, Domain d){
		
		//Parser 
		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
		HttpConnectionParams.setSoTimeout(httpParams, 3000);
		HttpClient client = new DefaultHttpClient(httpParams);

		File file = new File(inputFileName);
		
		try {
			
			PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
			BufferedReader fileReader = new BufferedReader(new FileReader(file));

			while (fileReader.ready()){

				// Read from the text file
				String facebookPage = fileReader.readLine();
				if(facebookPage.trim().isEmpty()){
					continue;
				}
				
				// Create a graph request
				facebookPage = facebookPage.replaceAll("www", "graph");
				
				// Changes https://graph.facebook.com/pages/Armageddon/9283931?ref=br_rs 
				// to https://graph.facebook.com/9283931?ref=br_rs
				if (facebookPage.matches("https://graph.facebook.com/.*/.*/[0-9]+\\?ref=br_rs$"))
					facebookPage = facebookPage.replaceAll("pages/.*/", "");
					if (!facebookPage.contains("https://graph.facebook.com"))
						continue;
				try{
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
						
						// Enter information for each object into the database
						mysql(info, d);
					}
				}catch (Exception e) {
					e.printStackTrace();
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
	 * @param object JSON movie object
	 * @throws JSONException
	 */
	private void mysql(JSONObject object, Domain d) throws JSONException {
		
		MysqlPortal mysql = new MysqlPortal();
		
		switch(d){
		case MOVIE:
			// Inserts title into the database
			if(object.has("name"))
				mysql.insert(object.get("name").toString(), "Movie", "Title");

			// Inserts likes into the database
			if (object.has("likes"))
				mysql.update("Movie", "totalLikes", Integer.parseInt(object.get("likes").toString()), "title", object.get("name").toString());
			break;
			
		case ATHLETE:
			// Inserts title into the database
			if(object.has("name")){
				String[] name = object.get("name").toString().split(" ");
				int likes = 0;
				if(object.has("likes")){
					likes = object.getInt("likes");
				}
				if(name.length >= 3){
					mysql.insertAthleteFull(name[0], name[1], name[2], likes, 0, -1);
				}
				else if(name.length >= 2){
					mysql.insertAthleteFull(name[0], "", name[1], likes, 0, -1);
				}
				else{
					mysql.insertAthleteFull(name[0], "", "", likes, 0, -1);
				}
			}
			break;
		case ARTIST:
			// Inserts title into the database
			if(object.has("name")){
				String name = object.getString("name");
				int likes = 0;
				if(object.has("likes")){
					likes = object.getInt("likes");
				}
				mysql.insertArtistFull(name, "", "", -1, likes, -1);				
			}
			break;
		case TEAM:
			// Inserts title into the database
			if(object.has("name"))
				mysql.insert(object.get("name").toString(), "Team", "teamName");
			int likes = 0;
			if(object.has("likes")){
				likes = object.getInt("likes");
			}
			// Inserts likes into the database
			if (object.has("likes"))
				mysql.update("Team", "totalLikes", likes, "teamName", object.getString("name"));
			break;
		}
	
	}
}
