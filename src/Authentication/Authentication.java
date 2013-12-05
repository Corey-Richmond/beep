/* Author: Donald Siuchninski
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package Authentication;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import com.mongodb.DBObject;

import database.MongoPortal;

public class Authentication implements AuthenticationInterface{
	
	public static void main(String args[]){
	
		try{
	        
	        System.out.println("================================================================");
	        System.out.println("Binding \"Authentication\"...");
	        
			// Get handle to registry
	        Registry registry = LocateRegistry.createRegistry(2001);
	        
            //Register Department object in the naming registry
            String authRMIName = "Authentication";
            AuthenticationInterface authObject = new Authentication();
            AuthenticationInterface authStub = (AuthenticationInterface) UnicastRemoteObject.exportObject(authObject, 0);
            registry.rebind(authRMIName, authStub);
        	
            System.out.println("\"Authentication\" bound.");
		} 
		catch (Exception e) {
			System.err.println("\"Authentication\" bind exception:");
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns true if user has an account, else returns false
	 * @param user_id User's unique id
	 * @param password User's password
	 * @return True if authentication succeeds, else false
	 * @throws RemoteException
	 */
	public boolean authenticate(String user_id, String password) throws RemoteException {
		
		MongoPortal portal = new MongoPortal();

		return portal.exists("users", "user_id", user_id) && portal.exists("users", "password", password);
	}
	
	/**
	 * New user creation
	 * @param name User's full name
	 * @param email User's e-mail
	 * @param username User name
	 * @param password Password
	 * @return True if complete, else false
	 */
	public boolean registerUser(String name, String email, String user_id, String password){
		
		MongoPortal portal = new MongoPortal();
		
		return portal.createUser(name, email, user_id, password);
	}
	
	/**
	 * Stores a search query in the user's history (associated with user_id)
	 * @param search The search performed by the user
	 * @param user_id The user's unique identifier
	 * @return True if completed successfully, else false
	 */
	public boolean storeHistory(String user_id, String search){
		
		MongoPortal portal = new MongoPortal();
		
		return portal.storeHistory(user_id, search);
	}
	
	/**
	 * Retrieve search history for all users
	 * @param user_id The user's unique identifier 
	 * @return List of user search history (size 0 if none found)
	 */
	public ArrayList<String> getHistory(String user_id){
		
		MongoPortal portal = new MongoPortal();
		
		ArrayList<DBObject> userHistory = new ArrayList<DBObject>();
		
		portal.getHistory(user_id, userHistory);
		
		ArrayList<String> history = new ArrayList<String>();
		
		Iterator<DBObject> i = userHistory.iterator();
		
		while (i.hasNext()){
			history.add(i.next().get("query").toString());
		}
		
		return history;
	}
}
