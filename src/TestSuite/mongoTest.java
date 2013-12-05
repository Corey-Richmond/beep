/* Author: Donald Siuchninski
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package TestSuite;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.MongoPortal;
import java.util.ArrayList;
import java.util.Iterator;

import com.mongodb.DBObject;

/**
 * @author Donald Siuchninski
 *
 */
public class mongoTest {
	
	String name;
	String email;
	String user_id;
	String password;
	
	String invalidUser;
	
	String search1;
	String search2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Must much only user_id for testCreateUser()
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
		invalidUser = "USER_DOES_NOT_EXIST!";
		
		name = "Thomas Siuchninski";
		email = "tlsdrummer@gmail.com";
		user_id = "fysem";
		password = "mechanic";
		
		search1 = "Guns n Roses";
		search2 = "Terminator";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		MongoPortal portal = new MongoPortal();
		portal.deleteRecords(user_id);
	}

	/**
	 * Tests the names of all collections in Mongo
	 */
	@Test
	public void testGetCollections() {
		
		MongoPortal portal = new MongoPortal();
		
		String collections = portal.getCollections();
		
		assertEquals(collections, "history\nsystem.indexes\nusers\n");
	}

	/**
	 * Tests that a new user is entered successfully.
	 * Immediately try to enter the same user again which shouldn't be successful.
	 * NOTE: Must enter a new *user_id* each time or else test will fail.
	 */
	@Test
	public void testCreateUser() {

		MongoPortal portal = new MongoPortal();
		
		//Create user
		boolean insertResult = portal.createUser(name, email, user_id, password);
		assertEquals(insertResult, true);
		
		//Try to create the same user again
		insertResult = portal.createUser(name, email, user_id, password);
		assertEquals(insertResult, false);
	}

	/**
	 * Test insertion of history for existing user
	 */
	@Test
	public void testStoreHistoryExistingUser() {
		
		MongoPortal portal = new MongoPortal();
		
		// Create user
		portal.createUser(name, email, user_id, password);
		
		// Insert search history for existing user
		boolean storeResult = portal.storeHistory(user_id, search1);
		assertEquals(storeResult, true);
		
		// Insert search history for existing user
		storeResult = portal.storeHistory(user_id, search2);
		assertEquals(storeResult, true);
		
		// Insert a copy search history for existing user
		storeResult = portal.storeHistory(user_id, search1);
		assertEquals(storeResult, true);
		
		// Insert a copy search history for existing user
		storeResult = portal.storeHistory(user_id, search2);
		assertEquals(storeResult, true);
	}
	
	/**
	 * Test insertion of history for non-existent user
	 */
	@Test
	public void testStoreHistoryNonExistantUser() {
		
		MongoPortal portal = new MongoPortal();
		
		// Store search history for non-existent user
		boolean storeResult = portal.storeHistory(invalidUser, search1);
		assertEquals(storeResult, false);
		
		// Store a different search history for non-existent user
		storeResult = portal.storeHistory(invalidUser, search2);
		assertEquals(storeResult, false);
	}

	/**
	 * Creates user and stores 2x searches under the user_id. Then, retrieves 
	 * that search history. 
	 */
	@Test
	public void testGetHistoryExistingUser() {
		
		MongoPortal portal = new MongoPortal();
		
		// Create user
		portal.createUser(name, email, user_id, password);
		portal.storeHistory(user_id, search1);
		portal.storeHistory(user_id, search2);
		
		ArrayList<DBObject> history = new ArrayList<DBObject>();
		
		// Test valid history
		boolean getResult = portal.getHistory(user_id, history);
		assertEquals(getResult, true);
		
		String historyResults = "";
		String expectedResults = "";
		
		// Build the string of results
		Iterator<DBObject> i = history.iterator();
		while (i.hasNext()){
			historyResults += i.next().get("query");
		}
		
		// Compare the string of the expected results
		assertEquals(historyResults, expectedResults + search1 + search2);
	}
	
	/**
	 * Attempt to retrieve search history for a non-existing user
	 */
	@Test
	public void testGetHistoryNonExistingUser() {
		
		MongoPortal portal = new MongoPortal();
		
		ArrayList<DBObject> history = new ArrayList<DBObject>();
		
		// Test valid history
		boolean getResult = portal.getHistory(user_id, history);
		assertEquals(getResult, false);
	
	}
}
