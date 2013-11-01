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

				HttpGet get = new HttpGet(facebookPage);

				HttpResponse response = client.execute(get);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent(), "UTF-8"));

				StringBuilder builder = new StringBuilder();

				for (String line = null; (line = reader.readLine()) != null; ) 
					builder.append(line).append("\n");

				//System.out.println(builder.toString());

				JSONTokener tokener = new JSONTokener(builder.toString());
				JSONObject info = new JSONObject(tokener);

				try {
					writer.println(info.toString(2));
				}catch (JSONException e){}
			}
			writer.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		

	}
}
