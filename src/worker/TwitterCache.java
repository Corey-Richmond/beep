package worker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import database.MysqlPortal;

import twitter4j.Status;
import facebook.FacebookClient.Domain;

public class TwitterCache implements Worker, Serializable{

	public static final String SER_FILE = "savedTweets.ser";
	public static final int BACKUP_THRESHOLD = 1000;

	ArrayList<Status> statuses;
	int newCount;
	MysqlPortal mysql;

	public TwitterCache(){
		newCount = 0;

		try
		{
			FileInputStream fileIn = new FileInputStream(SER_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			statuses = (ArrayList<Status>) in.readObject();        
			in.close();
			fileIn.close();
		}catch(Exception e)
		{
			statuses = new ArrayList<Status>();
		}

		mysql = new MysqlPortal();
	}

	@Override
	public void updateDB(Domain d, String name) throws RemoteException {
		
		new UpdateThread(name, d).start();
		
	}

	@Override
	public void storeTweet(Status s) throws RemoteException{
		newCount++;
		statuses.add(s);
		System.out.println("Storing: " + s.toString());
		if(newCount > BACKUP_THRESHOLD){
			try {
				saveData();
				System.out.println("Backing up data");
				newCount = 0;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void saveData() throws RemoteException {
		try
		{
			File f = new File(SER_FILE);
			if(f.exists())
				f.delete();

			FileOutputStream fileOut = new FileOutputStream(SER_FILE);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(statuses);
			out.close();
			fileOut.close();
			System.out.println("Data has been backed up");

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Checks if x contains most of the words in y
	 * @param x
	 * @param y
	 * @return true if x contains y, false otherwise
	 */
	private boolean containsKeywords(String x, String y){
		String[] words = y.split(" ");
		int count = 0;
		int limit = words.length;
		for(String s : words){
			if(x.contains(s))
				count++;
		}

		if(count > (limit / 2))
			return true;

		return false;
	}
	private List<Status> findAllTweets(String target){
		ArrayList<Status> tweets = new ArrayList<Status>();
		for(Status  s : statuses){
			if(containsKeywords(s.getText().toLowerCase(), target.toLowerCase()))
				tweets.add(s);
		}
		return tweets;
	}

	public static void main(String[] args){
		try {
			Registry registry = LocateRegistry.createRegistry(2001);
			TwitterCache tc = new TwitterCache();
			Worker w = tc;
			Worker wStub = (Worker) UnicastRemoteObject.exportObject(w, 0);
			registry.rebind("TwitterWorker", wStub);
			System.out.println("Bound as TwitterWorker");


		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class UpdateThread extends Thread{
		String name;
		Domain d;
		public UpdateThread(String name, Domain d){
			this.name = name;
			this.d = d;
		}
		public void run(){
			List<Status> mentions = findAllTweets(name);
			for(Status s : mentions){
				mysql.incrementLikes(d, name, s.getPlace().getName(), 1);
			}
		}
	}
	
}
