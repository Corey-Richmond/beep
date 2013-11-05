package driver;

import utilities.Parser;
import facebook.FacebookClient;
import database.MysqlPortal;
import utilities.LikesGenerator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import qps.*;

import com.mysql.jdbc.ResultSet;

public class Driver {

	public static void main (String args[]){
		
		// Read contents from file
//		ArrayList<String> contents = new ArrayList<String>();
//		Parser parser = new Parser();
//		parser.extractFBMovieLinks("./files/raw_facebook.txt", contents);
		
		// Grab movie info and populate database
//		FacebookClient getter = new FacebookClient();
//		getter.getData("./files/felix.txt");
		
//		Parser parser = new Parser();
//		try{
//			parser.parseCities();
//			parser.parseTheatres();
//			
//		} catch (Exception e){}
		
//		QPS qps = new QPS();
//		ArrayList<String> result1 = qps.getCitiesByMovie("Pirates of the Caribbean");
//		for(int i = 0; i<result1.size(); ++i){
//			System.out.println(result1.get(i)) ;
//		}
		
//		QPS qps = new QPS();
//		ArrayList<String[]> result1 = null;
//		try {
//			result1 = qps.getVenueByMovieAndRank("Pirates of the Caribbean", 1);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(int i = 0; i<result1.size(); ++i){
//			System.out.println(result1.get(i)[0]) ;
//		}
		
//		MysqlPortal mysql = new MysqlPortal();
//		//String movie = "Robin Hood";
//		String movie = "Pirates of the Caribbean";
//		
//		int rank = 1;
//		
//		//Get List of cities by rank for a specific movie
//		ArrayList<String> result1 = null;
//		ArrayList<String> result2 = null;
//
//		while(!(result1 = mysql.query(
//		"select cityName from City where cityID in " +
//		"(select cityID from MovieCitiesList where movieID in " +
//		"(select movieID from Movie where title = \""+movie+"\") " +
//				"and cityRank = "+rank+")", "cityName")).isEmpty()){
//			System.out.println(result1.get(0));
//			rank++;
//		}
//		
//		rank = 1;
//		//Get List of venues for movie by rank
//		result1 = mysql.query(
//				"select name, address from MovieVenue where cityID in " +
//				"(select cityID from MovieCitiesList where movieID in " +
//				"(select movieID from Movie where title = \""+movie+"\") " +
//						"and cityRank = "+rank+")", "name");
//		result2 = mysql.query(
//				"select name, address from MovieVenue where cityID in " +
//				"(select cityID from MovieCitiesList where movieID in " +
//				"(select movieID from Movie where title = \""+movie+"\") " +
//						"and cityRank = "+rank+")", "address");
//		for(int i = 0; i<result1.size(); ++i){
//			System.out.println(result1.get(i) + ", " + result2.get(i)) ;
//		}
		
		
		Registry registry;
		try {
			
			registry = LocateRegistry.getRegistry();
			QPSInterface qps = (QPSInterface) registry.lookup("QPS");
			ArrayList<String[]> result1 = qps.getVenueByMovieAndRank("Pirates of the Caribbean", 1);
			System.out.println(result1.get(0)[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
