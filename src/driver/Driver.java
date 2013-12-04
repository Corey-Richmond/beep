/* University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package driver;

import utilities.Parser;
import utilities.zipUtil;
import facebook.FacebookClient;
import database.MysqlPortal;
import database.MongoPortal;
import utilities.LikesGenerator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import LoadBalancer.Balancer;

import com.mongodb.DBObject;

import qps.*;

import com.mysql.jdbc.ResultSet;

public class Driver {

	public static void main (String args[]){
		//*****************STARTS DATABASE POPULATION*******************************
		DatabasePopulator dp = new DatabasePopulator();
		dp.populateDatabase();
		
		//*****************ENDS DATABASE POPULATION*******************************
//		// Read contents from file
//		ArrayList<String> contents = new ArrayList<String>();
//		Parser parser = new Parser();
//		parser.extractFBMovieLinks("./files/raw_facebook.txt", contents);
//		
//		// Grab movie info and populate database
//		FacebookClient getter = new FacebookClient();
//		getter.getData("./files/felix.txt");
//		
//		//Parser parser = new Parser();
//		try{
//			parser.parseCities();
//			parser.parseTheatres();
//			
//		} catch (Exception e){}
//		
//		QPS qps = new QPS();
//		ArrayList<String> result1 = qps.getCitiesByMovie("Pirates of the Caribbean");
//		for(int i = 0; i<result1.size(); ++i){
//			System.out.println(result1.get(i)) ;
//		}
//		
//		//QPS qps = new QPS();
//		ArrayList<String[]> result2 = null;
//		try {
//			result2 = qps.getVenueByMovieAndRank("Pirates of the Caribbean", 1);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(int i = 0; i<result1.size(); ++i){
//			System.out.println(result2.get(i)[0]) ;
//		}
//		
//		MysqlPortal mysql = new MysqlPortal();
//		//String movie = "Robin Hood";
//		String movie = "Pirates of the Caribbean";
//		
//		int rank = 1;
//		
//		//Get List of cities by rank for a specific movie
//		ArrayList<String> result3 = null;
//		ArrayList<String> result4 = null;
//
//		while(!(result3 = mysql.query(
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
//		result4 = mysql.query(
//				"select name, address from MovieVenue where cityID in " +
//				"(select cityID from MovieCitiesList where movieID in " +
//				"(select movieID from Movie where title = \""+movie+"\") " +
//						"and cityRank = "+rank+")", "address");
//		for(int i = 0; i<result1.size(); ++i){
//			System.out.println(result1.get(i) + ", " + result2.get(i)) ;
//		}
		
//*********************************************************************************************
//		
//	// << DEMO >>
//		
//		Registry registry;
//		try {
//			
//			registry = LocateRegistry.getRegistry(2001);
//			Balancer loadBalancer = (Balancer) registry.lookup("LoadBalancer");
//			
//			
//			QPSInterface qps = loadBalancer.getQPS();
//			
//			while(true) {
//				Scanner scanner = new Scanner( System.in );
//				System.out.print( "\nFind List of most popular city by Movie Title.\n" +
//							      "What moive do you want to lookup? > " );
//				String input = scanner.nextLine();
//				
//				//Pirates of the Caribbean
//				ArrayList<String> result1 = qps.getCitiesByMovie(input);
//				for(int i = 0; i<result1.size(); ++i){
//					System.out.println((i+1) + ". " + result1.get(i)) ;
//				}
//				
//				System.out.print( "From the above list which city looks most appealing.\n" +
//					      		  "Enter the number from the above list > " );
//				int input1 = scanner.nextInt();
//				
//				System.out.print( "Do you also want the address [y/n] > " );
//				boolean input2 = (scanner.next().equals("y"));
//				
//				
////				QPS qps = new QPS();
//				ArrayList<String[]> result2 = null;
//				try {
//					result2 = qps.getVenueByMovieAndRank(input, input1);
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				for(int i = 0; i<result1.size(); ++i){
//					if(input2)
//						System.out.println("\nVenue " +(i+1) +" = " + result2.get(i)[0] + "\n" +
//										   "Address = " + result2.get(i)[1]) ;
//					else
//						System.out.println("\nVenue " +(i+1) +" = " + result2.get(i)[0]);
//						
//				}
//			
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
// *********************************************************************************************
	// << MONGODB TESTING >>
	/*	
//		MongoPortal portal = new MongoPortal();
//		/*if(portal.createUser("Nala", "nala@aol.com", "nal8", "i<3tennisballs"))
//			System.out.println("Added new user");
//		
//		else
//			System.out.println("Failed to add new user");
//		
//		if (portal.storeHistory("nal8", "Bon Jovi"))
//			System.out.println("Stored successfully in the database");
//		
//		else
//			System.out.println("Failed to record history");*/
//		
//		ArrayList<DBObject> history = new ArrayList<DBObject>();
//		portal.getHistory("nal8", history);
//		
//		System.out.println("Size: " + history.size());
//		
//		Iterator<DBObject> i = history.iterator();
//		while (i.hasNext()){
//			System.out.println(i.next().get("query"));
//		}
	}
	
}
