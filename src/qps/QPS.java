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

import tweaper.Tweaper;
import worker.Worker;

import LoadBalancer.LoadBalanced;
import database.MysqlPortal;
import facebook.FacebookClient.Domain;

//Registry registry = LocateRegistry.getRegistry();
//System.out.println("Obtaining original remote object value.");
//RemoteDatabase remoteObject = (RemoteDatabase) registry.lookup("Database");

public class QPS implements QPSInterface{
	int count;
	MysqlPortal mysql = new MysqlPortal();
	Worker w;
	
	public QPS(){
		try {
			count = 0;
			Registry registry = LocateRegistry.getRegistry(2001);
			w = (Worker) registry.lookup("TwitterWorker");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String[]> getVenueByMovieAndRank(String movie, int rank)
	throws RemoteException {
		count++;
		w.updateDB(Domain.MOVIE, movie);
		//Get List of cities by rank for a specific movie
		ArrayList<String[]> result;

		result = mysql.query(
				"select name, address from MovieVenue where cityID in " +
				"(select cityID from MovieCitiesList where movieID in " +
				"(select movieID from Movie where title = \""+movie+"\") " +
				"and cityRank = "+rank+")", "name, address");
		if(result.size() > 0){
			if(result.get(0).length > 0)
				System.out.println(result.get(0)[0]);	
		}
		count--;
		return result;
	}

	@Override
	public ArrayList<String[]> getCitiesByActor(String city) throws RemoteException {
		// Load balance count
		count++;

		int rank = 1;

		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String[]> result2 = new ArrayList<String[]>();

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

		getResult = mysql.query(
				"select cityName from City where cityID in " +
				"(select cityID from MovieCitiesList where movieID in " +
				"(select movieID from Movie where title = \""+city+"\") " +
				"and cityRank is not null)", "cityName");

		for(String [] s : getResult){
			if(s.length > 0){
				result.add(s);
				rank++;
			}
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
	
	// changes by anusha
	@Override
	public ArrayList<String> getCitiesByMovie1(String movie) throws RemoteException {

		ArrayList<String> result = new ArrayList<String>();
		


String queryNew ="select cityName , cityState from city where cityID in (select cityID from moviecitieslist where movieID in (select movieId from movie where title = '"+movie+"'))";
	
//String queryNew="select title from movie where movieID in ( select movieID from moviecast where actorID in (select actorId from actor where personId in (select personID from person where firstName= "+movie+")))";
	
//		String queryNew="select name , address, S.cityName, S.cityState from movievenue ,(select cityName , cityState,cityID from city where cityID in (select cityID from moviecitieslist where movieID in (select movieId from movie where title = '"+movie+"'))) AS S where movievenue.cityId in (S.cityID)";	
		result = mysql.query1(queryNew);
		
		return result;

	}
	
	// changes done by anusha
	@Override
	public ArrayList<String> getCitiesByActor1(String actor) throws RemoteException {

		ArrayList<String> result = new ArrayList<String>();
		

// select cityID, cityRank from moviecitieslist where movieID in (select movieID from moviecast where actorID in (select actorID from actor where personID in (select personID from person where firstName ="jones"))) order by cityRank;
   
 //String queryNew = "select cityName from city where cityID in (select cityID from moviecitieslist where movieID in (select movieID from moviecast where actorID in (select actorID from actor where personID in (select personID from person where firstName ='"+actor+"'))) order by cityRank)";
	
		/*tring queryNew = "select K.title,cityName" +
				" from city , (select cityID,Y.title,Y.movieID from moviecitieslist, (select S.movieID, title from (select movieID from moviecast where actorID in (select actorID from actor where personID in (select personID from person where firstName ='"+actor+"'))) AS S " 
						+ ", movie where movie.movieID in  (S.movieID)) AS Y where moviecitieslist.movieID in (Y.movieID)) AS K where city.cityID in (K.cityID)";*/
	String queryNew = "select K.title,cityName" +
	" from city , (select cityID,Y.title,Y.movieID from moviecitieslist, (select S.movieID, title from (select movieID from moviecast where actorID in (select actorID from actor where personID in (select personID from person where firstName ='"+actor+"'))) AS S " 
			+ ", movie where movie.movieID in  (S.movieID)) AS Y where moviecitieslist.movieID in (Y.movieID)) AS K where city.cityID in (K.cityID)";
	
		result = mysql.query2(queryNew);
		
		return result;

	}
	
	// changes done by anusha
	@Override
	public ArrayList<String> getCitiesByArtist1(String artist) throws RemoteException {

		ArrayList<String> result = new ArrayList<String>();
		
        String queryNew ="select name , address,S.cityName from concertvenue , (select cityID, cityName from city where cityID in (select artistsCitiesListID from artistcitieslist where artistID in (select artistID from artist where personID in (select personID from person where firstname = '"+artist+"')))) AS S where concertvenue.cityID in (S.cityID)";
		result = mysql.query3(queryNew);
		
		return result;

	}
	
	// changes done by anusha
			@Override
			public String getSportByTeam1(String team) throws RemoteException {

				String result = "";
				
		        String queryNew ="select name from sport where sportID in (select sportID from team where teamName='"+team+"')";
				result = mysql.query4(queryNew);
				
				return result;

			}
	
	// changes done by anusha
		@Override
		public ArrayList<String> getCitiesByTeam1(String team) throws RemoteException {

			ArrayList<String> result = new ArrayList<String>();
			
	        String queryNew ="select name, address , S.cityName from sportsvenue , (select cityID, cityName from city where cityID in (select cityID from sportcitieslist where sportID in (select sportID from team where teamName='"+team+"'))) AS S where sportsvenue.cityID in (S.cityID)";
			result = mysql.query5(queryNew);
			
			return result;

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

	@Override
	public void addNewEntry(Domain d, String name) {
		count ++;
		switch(d){
		case ARTIST:
			if(!mysql.isAlreadyInDB(d, name)){
				mysql.insertArtistFull(name, "", "", -1, 0, 0);
			}
			break;
		case ATHLETE:
			if(!mysql.isAlreadyInDB(d, name)){
				mysql.insertAthleteFull(name, "", "", 0, 0, -1);
			}
			break;
		case MOVIE:
			if(!mysql.isAlreadyInDB(d, name)){
				mysql.insert(name, "Movie", "Title");
				mysql.update("Movie", "totalLikes", "" + 0, "title", name);
			}
			break;
		case TEAM:
			if(!mysql.isAlreadyInDB(d, name)){
				mysql.insert(name, "Team", "teamName");
			}
			break;
		}
		try {
			w.updateDB(d, name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count --;
	}

}
