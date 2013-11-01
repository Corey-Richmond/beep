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
		
//		FacebookClient getter = new FacebookClient();
//		getter.getData("./files/extracted_https.txt");
		
		// Insert into database
		/*MysqlPortal mysql_portal = new MysqlPortal();
		mysql_portal.insert(contents, "info", "name");
		
		MongoPortal mongo_portal = new MongoPortal();
		mongo_portal.insert();*/
		
		// Likes generator test
		ArrayList<Integer> likes = new ArrayList<Integer>();
		LikesGenerator gen = new LikesGenerator();
		gen.generateLikes(300000, likes);
		
		int likesCounted = 0;
		
		for (int x = 0 ; x < likes.size(); x ++){
			System.out.println(x + ": " + likes.get(x));
			likesCounted += likes.get(x);
		}
		
		
		System.out.println(likesCounted);
	}
}
