package database;

import java.util.Set;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

public class MongoPortal {
	
	static final String domain = "localhost";  
	static final int port = 27017;
	static final String database = "test";
	
	public boolean insert(){
		
		try {
			MongoClient mongoClient = new MongoClient(domain, port);
			
			DB db = mongoClient.getDB(database);
			
			// Get and print all the collections
			Set<String> colls = db.getCollectionNames();
			for (String s : colls)
			    System.out.println(s);
			
			mongoClient.close();
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
			
		return true;
	}
}
