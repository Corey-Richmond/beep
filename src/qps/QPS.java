package qps;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import database.MysqlPortal;

//Registry registry = LocateRegistry.getRegistry();
//System.out.println("Obtaining original remote object value.");
//RemoteDatabase remoteObject = (RemoteDatabase) registry.lookup("Database");

public class QPS implements QPSInterface{
	MysqlPortal mysql = new MysqlPortal();
	
	public String helloWorld(){
		return "\nHello world!\n";
	}
	
	public ArrayList<String> getCitiesByMovie(String movie){
		//String movie = "Pirates of the Caribbean";
		
		int rank = 1;
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String> result2 = new ArrayList<String>();

		while(!(result1 = mysql.query(
		"select cityName from city where cityID in " +
		"(select cityID from moviecitiesList where movieID in " +
		"(select movieID from movie where title = \""+movie+"\") " +
				"and cityRank = "+rank+")", "cityName")).isEmpty()){
			//System.out.println(result1.get(0));
			result2.add(result1.get(0));
			rank++;
		}
		return result2;
		
	}
	
	public static void main(String args[]){
	
		// Protects against malicious code
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
		try{
	        
	        System.out.println("================================================================");
	        System.out.println("Binding \"QPS\"...");
	        
			// Get handle to registry
	        Registry registry = LocateRegistry.getRegistry();
	        
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
	public String getActor(String search) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovie(String search) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getArtist(String search) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTeam(String search) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getActorsByCity(String city) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMoviesByCity(String city) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getArtistsByCity(String city) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTeamsByCity(String city) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String[]> getVenueByMovieAndRank(String movie, int rank)
			throws RemoteException {
		
		//Get List of cities by rank for a specific movie
		ArrayList<String> result1 = null;
		ArrayList<String> result2 = null;
		ArrayList<String[]> result3 = new ArrayList<String[]>();
		
		

		result1 = mysql.query(
		"select name, address from MovieVenue where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+movie+"\") " +
				"and cityRank = "+rank+")", "name");
		
		result2 = mysql.query(
		"select name, address from MovieVenue where cityID in " +
		"(select cityID from MovieCitiesList where movieID in " +
		"(select movieID from Movie where title = \""+movie+"\") " +
				"and cityRank = "+rank+")", "address");
		
		for(int i = 0; i<result1.size(); ++i){
			String[] temp = new String[2];
			temp[0] = result1.get(i);
			temp[1] = result2.get(i);
			result3.add(temp);
		}
		
		return result3;
	}
}
