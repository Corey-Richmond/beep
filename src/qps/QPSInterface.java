package qps;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import LoadBalancer.LoadBalanced;

public interface QPSInterface extends Remote, LoadBalanced{
	
	public String helloWorld() throws RemoteException;
	
	/**
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String getActor(String search) throws RemoteException;
	
	/**
	 * 
	 * @param search
	 * @return
	 * @throws RemoteException
	 */
	public String getMovie(String search) throws RemoteException;
	
	/**
	 * 
	 * @param search
	 * @return
	 * @throws RemoteException
	 */
	public String getArtist(String search) throws RemoteException;
	
	/**
	 * 
	 * @param search
	 * @return
	 * @throws RemoteException
	 */
	public String getTeam(String search) throws RemoteException;
	
	public String getActorsByCity(String city) throws RemoteException;
	
	public String getMoviesByCity(String city) throws RemoteException;
	
	public String getArtistsByCity(String city) throws RemoteException;
	
	public String getTeamsByCity(String city) throws RemoteException;
	
	public ArrayList<String> getCitiesByMovie(String movie) throws RemoteException;
	
	public ArrayList<String[]> getVenueByMovieAndRank(String movie, int rank) throws RemoteException;
}
