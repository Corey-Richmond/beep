package qps;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import LoadBalancer.Balancer;

@WebService(endpointInterface = "qps.QPSWebInterface")
public class QPSWeb implements QPSWebInterface{

	Balancer lb;
	
	public QPSWeb(){
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(2001);
			lb = (Balancer) registry.lookup("LoadBalancer");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getCitiesByActor(String city)
			throws RemoteException {
		return lb.getQPS().getCitiesByActor(city).toString();
	}

	@Override
	public String getCitiesByMovie(String movie) throws RemoteException {
		return lb.getQPS().getCitiesByMovie(movie).toString();
	}

	@Override
	public String getCitiesByArtist(String city) throws RemoteException {
		return lb.getQPS().getCitiesByArtist(city).toString();
	}

	@Override
	public String getCitiesByAthlete(String city) throws RemoteException {
		return lb.getQPS().getCitiesByAthlete(city).toString();
	}

	@Override
	public String getCitiesByTeam(String city) throws RemoteException {
		return lb.getQPS().getCitiesByTeam(city).toString();
	}

	@Override
	public String getPopularActorsByCity(String city) throws RemoteException {
		return lb.getQPS().getPopularActorsByCity(city).toString();
	}

	@Override
	public String getPopularMoviesByCity(String city) throws RemoteException {
		return lb.getQPS().getPopularMoviesByCity(city).toString();
	}

	@Override
	public String getPopularArtistsByCity(String city) throws RemoteException {
		return lb.getQPS().getPopularArtistsByCity(city).toString();
	}

	@Override
	public String getPopularAthletesByCity(String city) throws RemoteException {
		return lb.getQPS().getPopularActorsByCity(city).toString();
	}

	@Override
	public String getPopularTeamsByCity(String city) throws RemoteException {
		return lb.getQPS().getPopularTeamsByCity(city).toString();
	}

	@Override
	public String getVenueByMovieAndRank(String input, int input1)
			throws RemoteException {
		return lb.getQPS().getVenueByMovieAndRank(input, input1).toString();
	}
	
	public static void main(String[] args){
		Endpoint.publish("http://localhost:9001/qps", new QPSWeb());
		System.out.println("QPSWeb service is running on: " + "http://localhost:9001/qps");
	}

}
