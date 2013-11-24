/* University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package database;

import java.util.ArrayList;
import com.mongodb.DBObject;

public interface MongoFacet {
	
	/**
	 * Retrieves all collections and returns formatted string
	 * @return String list of collections seperated by newlines
	 */
	public String getCollections();

	/**
	 * New user creation
	 * @param name User's full name
	 * @param email User's e-mail
	 * @param username User name
	 * @param password Password
	 * @return True if complete, else false
	 */
	public boolean createUser(String name, String email, String user_id, String password);
	
	/**
	 * Stores a search query in the user's history (associated with user_id)
	 * @param search The search performed by the user
	 * @param user_id The user's unique identifier
	 * @return True if completed successfully, else false
	 */
	public boolean storeHistory(String user_id, String search);
	
	/**
	 * Retrieve search history for all users
	 * @param user_id The user's unique identifier 
	 * @param history [out] List of user history
	 * @return True for successful, else false
	 */
	public boolean getHistory(String user_id, ArrayList<DBObject> history);
}
