/* Authors: Donald Siuchninski, Corey Richmond & Felix Rodriqguez
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package qps;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.jws.WebService;

import apiParsers.Tweaper;
import LoadBalancer.LoadBalanced;
import database.MysqlPortal;
import facebook.FacebookClient.Domain;

//Registry registry = LocateRegistry.getRegistry();
//System.out.println("Obtaining original remote object value.");
//RemoteDatabase remoteObject = (RemoteDatabase) registry.lookup("Database");

public class QPS implements QPSInterface{
	int count;
	MysqlPortal mysql = new MysqlPortal();
	public QPS(){
		count = 0;
	}
	
	@Override
	public ArrayList<String[]> getVenueByMovieAndRank(String movie, int rank)
			throws RemoteException {
		count++;
		//Get List of cities by rank for a specific movie
		ArrayList<String[]> result;

		result = mysql.query(
		"select name, address from MovieVenue where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+movie+"\") " +
				"and cityRank = "+rank+")", "name, address");
		System.out.println(result.get(0)[0]);	
		count--;
		return result;
	}

	@Override
	public ArrayList<String> getCitiesByActor(String city) throws RemoteException {
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String> result2 = new ArrayList<String>();

//		while(!(result1 = mysql.query(
//		"select cityName from City where cityID in " +
//		"(select cityID from MovieCitiesList where movieID in " +
//		"(select movieID from Movie where title = \""+city+"\") " +
//				"and cityRank = "+rank+")", "cityName")).isEmpty()){
//			//System.out.println(result1.get(0));
//			result2.add(result1.get(0));
//			rank++;
//		}
		
		count--;
		
		return result2;
	}

	@Override
	public ArrayList<String[]> getCitiesByMovie(String city) throws RemoteException {
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String[]> getResult = null;
		ArrayList<String[]> result = new ArrayList<String[]>();
		

		while(!(getResult = mysql.query(
		"select cityName from City where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			result.add(getResult.get(0));
			rank++;
		}
		
		count--;
		
		return result;

	}

	@Override
	public ArrayList<String[]> getCitiesByArtist(String city) throws RemoteException {
		
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String[]> result;

		while(!(result = mysql.query(
		"select cityName from City where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			//System.out.println(result1.get(0));
			rank++;
		}
		
		count--;
		
		return result;
	}
	
	@Override
	public ArrayList<String[]> getCitiesByAthlete(String city) throws RemoteException {
		
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String[]> result;

		while(!(result = mysql.query(
		"select cityName from City where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			//System.out.println(result1.get(0));
			rank++;
		}
		
		count--;
		
		return result;
	}

	@Override
	public ArrayList<String[]> getCitiesByTeam(String city) throws RemoteException {
		
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String[]> result;

		while(!(result = mysql.query(
		"select cityName from City where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			//System.out.println(result1.get(0));
			rank++;
		}
		
		count--;
		
		return result;
	}
	
	@Override
	public ArrayList<String[]> getPopularActorsByCity(String city) throws RemoteException {
		
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String[]> result;

		while(!(result = mysql.query(
		"select cityName from City where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			//System.out.println(result1.get(0));
			rank++;
		}
		
		count--;
		
		return result;
	}

	@Override
	public ArrayList<String[]> getPopularMoviesByCity(String city) throws RemoteException {
		
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String[]> result;

		while(!(result = mysql.query(
		"select cityName from City where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			//System.out.println(result1.get(0));
			rank++;
		}
		
		count--;
		
		return result;
	}

	@Override
	public ArrayList<String[]> getPopularArtistsByCity(String city) throws RemoteException {
		
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String[]> result;

		while(!(result = mysql.query(
		"select cityName from City where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			//System.out.println(result1.get(0));
			rank++;
		}
		
		count--;
		
		return result;
	}
	
	@Override
	public ArrayList<String[]> getPopularAthletesByCity(String city) throws RemoteException {
		
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String[]> result;

		while(!(result = mysql.query(
		"select cityName from City where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			//System.out.println(result1.get(0));
			rank++;
		}
		
		count--;
		
		return result;
	}

	@Override
	public ArrayList<String[]> getPopularTeamsByCity(String city) throws RemoteException {
		
		// Load balance count
		count++;
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String[]> result;

		while(!(result = mysql.query(
		"select cityName from City where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			//System.out.println(result1.get(0));
			rank++;
		}
		
		count--;
		
		return result;
	}

	@Override
	public int getLoad() {
		return count;
	}

	@Override
	public void incrementLoad() throws RemoteException {
		count++;
	}
	
	public static void main(String args[]){
        
		try{
	        
	        System.out.println("================================================================");
	        System.out.println("Binding \"QPS\"...");
	        
			// Get handle to registry
	        Registry registry = LocateRegistry.createRegistry(2001);
	        
            //Register Department object in the naming registry
            String qpsRMIName = "QPS";
            QPSInterface qpsObject = new QPS();
            QPSInterface qpsStub = (QPSInterface) UnicastRemoteObject.exportObject(qpsObject, 0);
            registry.rebind(qpsRMIName, qpsStub);
        	
            System.out.println("\"QPS\" bound.");
		} 
		catch (Exception e) {
			System.err.println("\"QPS\" bind exception:");
			e.printStackTrace();
		}
	}

}
