package qps;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import facebook.FacebookClient.Domain;

import LoadBalancer.LoadBalanced;

public interface QPSInterface extends Remote, LoadBalanced{
	
	
	public ArrayList<String[]> getCitiesByActor(String city) throws RemoteException;
	
	public ArrayList<String[]> getCitiesByMovie(String movie) throws RemoteException;
	
	public ArrayList<String[]> getCitiesByArtist(String city) throws RemoteException;
	
	public ArrayList<String[]> getCitiesByAthlete(String city) throws RemoteException;
	
	public ArrayList<String[]> getCitiesByTeam(String city) throws RemoteException;
	
	public ArrayList<String[]> getPopularActorsByCity(String city) throws RemoteException;
	
	public ArrayList<String[]> getPopularMoviesByCity(String city) throws RemoteException;
	
	public ArrayList<String[]> getPopularArtistsByCity(String city) throws RemoteException;
	
	public ArrayList<String[]> getPopularAthletesByCity(String city) throws RemoteException;
	
	public ArrayList<String[]> getPopularTeamsByCity(String city) throws RemoteException;

	public ArrayList<String[]> getVenueByMovieAndRank(String input, int input1) throws RemoteException;
		
	public void addNewEntry(Domain d, String name) throws RemoteException;	
	
	// changes done by anusha

	public ArrayList<String> getCitiesByMovie1(String city) throws RemoteException;
	
	public ArrayList<String> getCitiesByActor1(String actor) throws RemoteException;
	
	public ArrayList<String> getCitiesByArtist1(String artist) throws RemoteException;
	
	public ArrayList<String> getCitiesByTeam1(String team) throws RemoteException;
	
	public String getSportByTeam1(String team) throws RemoteException;
	
}
