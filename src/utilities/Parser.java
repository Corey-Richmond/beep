package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import database.MysqlPortal;

public class Parser implements ParserFacet{

	public boolean tokenize (String pathname, ArrayList<String> contents){
		
		if (pathname == null || contents == null){
			return false;
		}
		
		File file = new File(pathname);
		
		Scanner inputStream = null;
		
		try{
			inputStream = new Scanner(new FileInputStream(file));
		}
		
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		while (inputStream.hasNext())
			contents.add(inputStream.next());
		
		inputStream.close();
		
		return true;
	}
	
	public boolean lineReadGeneric (String pathname, ArrayList<String> contents){
		
		if (pathname == null || contents == null){
			return false;
		}
		
		File file = new File(pathname);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			//PrintWriter writer = new PrintWriter("movies_regex.txt", "UTF-8");
			
			while (reader.ready()){
				 String line = reader.readLine();	 
				 contents.add(line);
			}
			
			reader.close();
			//writer.close();
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	public boolean lineRead (String pathname, ArrayList<String> contents){
		
		if (pathname == null || contents == null){
			return false;
		}
		
		File file = new File(pathname);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			//PrintWriter writer = new PrintWriter("movies_regex.txt", "UTF-8");
			
			while (reader.ready()){
				 String line = reader.readLine();
				 
				 if (line.length() > 0){
					 line = line.replaceAll("\\d*.\\W*\\d.\\d*\\W\\b", "");
					 line = line.replaceAll("\\W\\(.*", "");
					 contents.add(line);
					 //writer.println(line);
				 }
			}
			
			reader.close();
			//writer.close();
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	
	public boolean extractFBMovieLinks (String pathname, ArrayList<String> contents){
		
		if (pathname == null || contents == null){
			return false;
		}
		
		File file = new File(pathname);
		
		Scanner inputStream = null;
		
		try{
			inputStream = new Scanner(new FileInputStream(file));
			
			PrintWriter writer = new PrintWriter("./files/extracted_https.txt", "UTF-8");
			
			inputStream.useDelimiter("\"");
			
			while (inputStream.hasNext()){
				inputStream.findInLine("_7kf _8o _8s lfloat");
				inputStream.findInLine("\"");
				inputStream.findInLine("\"");
				String extractedString = inputStream.next();
				if(extractedString.contains("https://www.facebook.com/"))
					writer.println(extractedString);
			}
			
			//writer.println("Finished.");
			writer.close();
			inputStream.close();
		}
		
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void parseCities() throws IOException{
		
		File file = new File("./files/felix/Data/cities.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		MysqlPortal mysql = new MysqlPortal();
		
		String line = "";
		
		while (line != null){
			line = reader.readLine();
			
			if (line != null){
				System.out.println(line);
				mysql.insert(line, "City", "cityName");
			}
		}
	}
	
	public void parseTheatres() throws IOException{
		
		File file = new File("./files/felix/Data/theatres.csv");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		MysqlPortal mysql = new MysqlPortal();
		
		String line = "";
		
		while (line != null){
			
			// Extract Venue
			line = reader.readLine().trim();		
			int terminator = line.indexOf(',', 0);
			String venue = line.substring(0, terminator);
			
			// Extract Address
			line = reader.readLine();
			terminator = line.indexOf(',', 0);
			String streetAddress = line.substring(0, terminator);
			
			// Another error Correction...
			if (streetAddress.charAt(0) == ',')
				continue;
			
			line = line.substring(terminator+1, line.length());
			String city = line.trim();		
			
			// Disregard last line
			reader.readLine();
			
			if (line != null){
				System.out.println("Venue: " + venue);
				
				// Make new entry for movie
				mysql.insert(venue, "MovieVenue", "name");
				
				// Insert the address of venue into the table
				System.out.println("Street: " + streetAddress);
				mysql.update("MovieVenue", "address", streetAddress, "name", venue);
				
				// Get the cityID from City table and insert into Movie VenueTable
				String id = mysql.get("cityID", "City", "cityName", city, null);
				if (id.equals(null) || id.equals(""))
					continue;
				mysql.update("MovieVenue", "cityID", Integer.parseInt(id), "name", venue);
			}
		}
	}
}
