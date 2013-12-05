/* Author: Donald Siuchninski
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;

import database.MysqlPortal;



public class Parser implements ParserFacet{
	final int MAX_LIKES = 100000;
	final Random rand = new Random();
	final double numMusicGenres = 35;
	
	public int numLikes(double place, double total, int max) {
		int n = rand.nextInt(max);
		while(n == 0) {
			n = rand.nextInt(max);
		}
		//System.out.println(total-)
		int b = (int)((total-place)/total * max);
		if(n<=b || b==0) {
			return n;
		} else {
			return rand.nextInt(b);
		}
	}

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
	
	
	/**
	 * UPDATE <table> SET <column> = '<value>' WHERE <where> ='<whereValue>'
	 * 
	 * Inserts one single VARCHAR item into table at column where a condition
	 * holds true
	 * 
	 * @param table Table to insert into
	 * @param column Column to insert into
	 * @param value VARCHAR value to insert
	 * @param where Column whose value must be satisfied in the query
	 * @param whereValue Value of the column that must be satisfied
	 * @return True when complete
	 */
	//public boolean update(String table, String column, String value, String where, String whereValue)
	
	public void parseCities() throws IOException{
		
		File file = new File("./files/felix/Data/cities.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		MysqlPortal mysql = new MysqlPortal();
		
		String line = "";
		boolean hasState = true;
		
		l:while (line != null){
			line = reader.readLine();
			if(line==null) {
				break l;
			}
			if(line.equals("#######")) {
				hasState=false;
				line = reader.readLine();
			}
			
			if(!hasState) {
				if (line != null){
					//System.out.println(line);
					mysql.insert(line, "City", "cityName");
				}
			} else {
				String state = reader.readLine();
				mysql.insert(line, "City", "cityName");
				if(state.equals("Russia")||state.equals("Canada")||state.equals("Poland")||state.equals("China")||state.equals("England")) {
					mysql.update("City", "cityCountry", state, "cityName", line);
				} else {
					mysql.update("City", "cityCountry", "USA", "cityName", line);
					mysql.update("City", "cityState", state, "cityName", line);
				}
			}
		}
	}
	
	public void parseMusicGenres() throws IOException { 
		File file = new File("./files/felix/Data/musicgenres.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		MysqlPortal mysql = new MysqlPortal();
		
		int i=0;
		String line = "";
		while (line != null){
			i++;
			line = reader.readLine();
			if (line != null){
				mysql.insertMusicGenre(line, numLikes((double)i, numMusicGenres, MAX_LIKES));
			}
		}
	}
	
	public void parseArtists() throws IOException { 
		File file = new File("./files/felix/Data/testArtists.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		MysqlPortal mysql = new MysqlPortal();
		
		long i=0;
		String line = "";
		while (line != null){
			i++;
			line = reader.readLine();
			if (line != null){
				String [] t = line.split(" ");
				if(t.length==1) {
					mysql.insertArtistFull(t[0],"", "", numLikes((double)i, numMusicGenres, 29), numLikes((double)i, (double)i, MAX_LIKES), numLikes((double)i, (double)i, 256));
				} else if(t.length==2) {
					mysql.insertArtistFull(t[0], "", t[1], numLikes((double)i, numMusicGenres, 29), numLikes((double)i, (double)i, MAX_LIKES), numLikes((double)i, (double)i, 256));
				} else {
					mysql.insertArtistFull(t[0], t[2], t[1], numLikes((double)i, numMusicGenres, 29), numLikes((double)i, (double)i, MAX_LIKES), numLikes((double)i, (double)i, 256));
				}
			}
		}
	}
	
	public void parseMovieGenres() throws IOException{
		File file = new File("./files/felix/Data/musicGenres.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		MysqlPortal mysql = new MysqlPortal();
		
		String line = "";

		int i=0;
		while (line != null){
			i++;
			line = reader.readLine();

			if (line != null){
				mysql.insertMovieGenres(line, numLikes((double)i, numMusicGenres, MAX_LIKES));
			}
						
		}
	}
	
	public void parseMusicVenues() throws IOException{
		File file = new File("./files/felix/Data/musicVenues.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		MysqlPortal mysql = new MysqlPortal();
		
		boolean isVenue=true;
		String line = "", name="", city="";

		l:while (line != null){
			line = reader.readLine();
			 if(line==null) { break l;}
			 if(isVenue) {
				 if (line != null){
					 city = line.substring(line.indexOf('\t')+1, line.length());
					 name = line.substring(0,line.indexOf('\t'));
					 isVenue=false;
				 }
			 } else {
			 	line = line.substring(0,line.indexOf('\t'));
			 	isVenue=true;
			 	mysql.insertConcert(name, line, city);
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
			
			try{
				// Another error Correction...
				if (streetAddress.charAt(0) == ',')
					continue;
			}
			catch(StringIndexOutOfBoundsException e){
			}
			
			
			line = line.substring(terminator+1, line.length());
			String city = line.trim();		
			
			// Disregard last line
			reader.readLine();
			
			if (line != null){
				System.out.println("Venue: " + venue);
				
				if(venue.contains("'"))
					venue = venue.replace("'", "");
				// Make new entry for movie
				mysql.insert(venue, "MovieVenue", "name");
				
				// Insert the address of venue into the table
				System.out.println("Street: " + streetAddress);
				mysql.update("MovieVenue", "address", streetAddress, "name", venue);
				
				// Get the cityID from City table and insert into Movie VenueTable
				String id = mysql.get("cityID", "City", "cityName", city);
				if (id.equals(null) || id.equals(""))
					continue;
				mysql.update("MovieVenue", "cityID", Integer.parseInt(id), "name", venue);
			}
		}
	}	
	
}
