package worker;

import java.rmi.Remote;
import java.rmi.RemoteException;

import twitter4j.Status;

import facebook.FacebookClient.Domain;

public interface Worker extends Remote{
	public void storeTweet(Status s) throws RemoteException;
	
	/**
	 * looks through all saved tweets for mentions of name, updates likes in database
	 * @param d domain of the name
	 * @param name name of target
	 * @throws RemoteException
	 */
	public void updateDB(Domain d, String name) throws RemoteException;
	
	/**
	 * backs up all data currently held by the worker
	 * @throws RemoteException
	 */
	public void saveData()throws RemoteException;
}
