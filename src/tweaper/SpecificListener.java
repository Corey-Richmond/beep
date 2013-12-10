package tweaper;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import database.MysqlPortal;
import facebook.FacebookClient.Domain;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import worker.Worker;

public class SpecificListener implements StatusListener{

	MysqlPortal mysql;
	Domain domain;
	String target;
	Worker w;
	List<String> cities;

	public SpecificListener(MysqlPortal p){
		
		try {
			mysql = p;
			cities = p.getCities();
			Registry registry = LocateRegistry.getRegistry(2001);
			w = (Worker) registry.lookup("TwitterWorker");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onStatus(Status status) {
		String cityName = status.getPlace().getName();
		if(cities.contains(cityName)){
			System.out.println(status);
			try {
				w.storeTweet(status);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}

	}

	public boolean containsKeyword(String text, String keyword){
		keyword = keyword.toLowerCase();
		text = text.toLowerCase();
		String[] split = keyword.split(" ");
		int count = 0;
		for(String s : split){
			if(text.contains(s))
				count++;
		}
		int size = split.length;
		if(count > size / 2)
			return true;
		return false;
	}

	@Override
	public void onException(Exception arg0) {	
	}
	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {		
	}
	@Override
	public void onScrubGeo(long arg0, long arg1) {		
	}
	@Override
	public void onStallWarning(StallWarning arg0) {		
	}
	@Override
	public void onTrackLimitationNotice(int arg0) {		
	}

}
