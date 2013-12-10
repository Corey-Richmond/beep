package tweaper;

import java.rmi.RemoteException;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import database.MysqlPortal;

public class Tweaper{

	public final static String SEPARATOR = "|";
	private static MysqlPortal mysql;

	TwitterStream twitterStream;
	StatusListener listener;

	private Tweaper(){
		twitterStream = new TwitterStreamFactory().getInstance();
		listener = new SpecificListener(mysql);
		twitterStream.addListener(listener);
	}

	public void startListening() throws RemoteException{
		FilterQuery fq = new FilterQuery();
		double[][] bb= {{-180, -90}, {180, 90}};
		fq.locations(bb);
		twitterStream.filter(fq);
	}

	public void stopListening() throws RemoteException{
		twitterStream.shutdown();
	}

	public static void main(String[] args){
		mysql = new MysqlPortal();
		Tweaper tweaper = new Tweaper();
		try {
			tweaper.startListening();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
