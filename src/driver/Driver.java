package driver;

import utilities.Parser;
import facebook.FacebookClient;
import database.MysqlPortal;
import utilities.LikesGenerator;

import java.util.ArrayList;

public class Driver {

	public static void main (String args[]){
		
		// Read contents from file
//		ArrayList<String> contents = new ArrayList<String>();
//		Parser parser = new Parser();
//		parser.extractFBMovieLinks("./files/raw_facebook.txt", contents);
		
		// Grab movie info and populate database
		FacebookClient getter = new FacebookClient();
		getter.getData("./files/felix.txt");
		
		Parser parser = new Parser();
		try{
			//parser.parseCities();
			parser.parseTheatres();
			
		} catch (Exception e){}

	}
}
