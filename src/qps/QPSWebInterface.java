/* Authors: Felix Rodriqguez
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 * 
 * This is a wrapper class around QPS which makes QPS a web service in order to test it with Jmeter
 */
package qps;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface QPSWebInterface {
	@WebMethod String getCitiesByActor(String city) throws RemoteException;

	@WebMethod String getCitiesByMovie(String movie) throws RemoteException;

	@WebMethod String getCitiesByArtist(String city) throws RemoteException;

	@WebMethod String getCitiesByAthlete(String city) throws RemoteException;

	@WebMethod String getCitiesByTeam(String city) throws RemoteException;

	@WebMethod String getPopularActorsByCity(String city) throws RemoteException;

	@WebMethod String getPopularMoviesByCity(String city) throws RemoteException;

	@WebMethod String getPopularArtistsByCity(String city) throws RemoteException;

	@WebMethod String getPopularAthletesByCity(String city) throws RemoteException;

	@WebMethod String getPopularTeamsByCity(String city) throws RemoteException;

	@WebMethod String getVenueByMovieAndRank(String input, int input1) throws RemoteException;


}
