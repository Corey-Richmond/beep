/* University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

public class MongoPortal implements MongoFacet{
	
	// Database/collections information
	static final String DOMAIN = "localhost";  
	static final int PORT = 27017;
	static final String DATABASE = "test";
	static final String AUTH_COLLECTION = "users";
	static final String HISTORY_COLLECTION = "history";
	
	// Field ID's
	static final String NAME = "name";
	static final String EMAIL = "email";
	static final String USER_ID = "user_id";
	static final String PASSWORD = "password";
	static final String QUERY = "query";
	
	// -------------------------------------------------------------------------------|
		
	/**
	 * Retrieves all collections and returns formatted string
	 * @return String list of collections separated by newlines
	 */
	public String getCollections() {
		
		String collections = "";
		
		try {
			MongoClient mongoClient = new MongoClient(DOMAIN, PORT);
			
			DB db = mongoClient.getDB(DATABASE);
			
			Set<String> colls = db.getCollectionNames();
			
			for (String s : colls)
			    collections += (s + "\n");
			
			mongoClient.close();
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
			
		return collections;
	}
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * New user creation
	 * @param name User's full name
	 * @param email User's e-mail
	 * @param username Username
	 * @param password Password
	 * @return True if complete, else false
	 */
	public boolean createUser(String name, String email, String user_id, String password) {
		
		try {
			MongoClient mongoClient = new MongoClient(DOMAIN, PORT);
			
			DB db = mongoClient.getDB(DATABASE);
			
			DBCollection collection = db.getCollection(AUTH_COLLECTION);
			
			if (!exists(collection, "user_id", user_id)){
			
				DBObject document = new BasicDBObject();
			
				document.put(NAME, name);
				document.put(EMAIL, email);
				document.put(USER_ID, user_id);
				document.put(PASSWORD, password);
			
				collection.insert(document);
			
				mongoClient.close();
			}
			
			else 
				return false;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
			
		return true;
	}
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * Stores a search query in the user's history (associated with user_id)
	 * @param search The search performed by the user
	 * @param user_id The user's special id
	 * @return True if completed successfully, else false
	 */
	public boolean storeHistory(String user_id, String search){
		
		try {
			MongoClient mongoClient = new MongoClient(DOMAIN, PORT);
			
			DB db = mongoClient.getDB(DATABASE);
			
			DBCollection collection = db.getCollection(HISTORY_COLLECTION);
			
			DBObject document = new BasicDBObject();
		
			document.put(USER_ID, user_id);
			document.put(QUERY, search);
		
			collection.insert(document);
		
			mongoClient.close();
			
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * Retrieve search history for all users
	 * @param user_id The user's unique identifier 
	 * @param history [out] List of user history
	 * @return True for successful, else false
	 */
	public boolean getHistory(String user_id, ArrayList<DBObject> history){
		
		try {
			MongoClient mongoClient = new MongoClient(DOMAIN, PORT);
			
			DB db = mongoClient.getDB(DATABASE);
			
			DBCollection collection = db.getCollection(HISTORY_COLLECTION);
			
			DBObject document = new BasicDBObject();
		
			document.put(USER_ID, user_id);
		
			DBCursor cursor = collection.find(document);
			
			history.addAll(cursor.toArray());
		
			mongoClient.close();
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * Returns whether a document exists in a given collection
	 * @param collection Reference to the collection to search
	 * @param field Field which contains the entry for the search
	 * @param entry Entry to match
	 * @return True if exists, else false
	 */
	private boolean exists(DBCollection collection, String field, String entry){
		
		DBObject document = new BasicDBObject();
		
		document.put(field, entry);
		
		DBCursor cursor = collection.find(document);
		
		if (cursor.size() <= 0)
			return false;
		
		return true;
	}
	
	// -------------------------------------------------------------------------------|
}
