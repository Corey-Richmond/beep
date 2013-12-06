/* Author: Donald Siuchninski
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package Authentication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AuthenticationInterface extends Remote{

	/**
	 * Returns true if user has an account, else returns false
	 * @param user_id User's unique id
	 * @param password User's password
	 * @return True if authentication succeeds, else false
	 * @throws RemoteException
	 */
	public boolean authenticate(String user_id, String password) throws RemoteException;
	
	/**
	 * New user creation
	 * @param name User's full name
	 * @param email User's e-mail
	 * @param username User name
	 * @param password Password
	 * @return True if complete, else false
	 */
	public boolean registerUser(String name, String email, String user_id, String password) throws RemoteException;
	
	/**
	 * Stores a search query in the user's history (associated with user_id)
	 * @param search The search performed by the user
	 * @param user_id The user's unique identifier
	 * @return True if completed successfully, else false
	 */
	public boolean storeHistory(String user_id, String search) throws RemoteException;
	
	/**
	 * Retrieve search history for all users
	 * @param user_id The user's unique identifier 
	 * @param history [out] List of user history
	 * @return True for successful, else false
	 */
	public ArrayList<String> getHistory(String user_id) throws RemoteException;
}
