/* Author: Donald Siuchninski
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

/**
 * 
 */
package TestSuite;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.MysqlPortal;
import java.util.ArrayList;

/**
 * @author donnie
 *
 */
public class mysqlTest {

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
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		MysqlPortal portal = new MysqlPortal();
		int rows = portal.deleteRowsInTable("test");
	}

	/**
	 * Test method for {@link database.MysqlPortal#query(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testQueryStringString() {
boolean result = false;
		
		MysqlPortal portal = new MysqlPortal();
		
		// Initialize 2 people
		ArrayList<String> contents = new ArrayList<String>();
		contents.add("Person1");
		
		String table = "test";
		String column = "FirstName";
		
		// Insert into database
		result = portal.insert(contents, table, column);
		assertTrue(result);
		
		ArrayList<String> retrieved = portal.query("SELECT * FROM test", column);
		
		assertEquals(retrieved.size(),1);
	}

	/**
	 * Inserts person into a test table
	 */
	@Test
	public void testInsertArrayListOfStringStringString() {
		
		boolean result = false;
		
		MysqlPortal portal = new MysqlPortal();
		
		// Initialize 2 people
		ArrayList<String> contents = new ArrayList<String>();
		contents.add("Person1");
		contents.add("Person2");
		
		String table = "test";
		String column = "FirstName";
		
		// Insert into database
		result = portal.insert(contents, table, column);
		assertTrue(result);
		
		// Verify people were inserted correctly
		ArrayList<String> extractedContents = portal.query("SELECT * FROM test", "FirstName");
		assertEquals(extractedContents.size(), contents.size());
		
		// Delete the rows that were just inserted
		int rows = portal.deleteRowsInTable(table);
		assertEquals(rows, contents.size());
		
		// Attempt to grab all users in the table
		extractedContents = portal.query("SELECT * FROM test", "FirstName");
		assertEquals(extractedContents.size(), 0);
	}

	/**
	 * Test method for {@link database.MysqlPortal#insert(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testInsertStringStringString() {
		
		int result = -1;
		
		MysqlPortal portal = new MysqlPortal();
		
		// Initialize 1 person
		String content = "Person1";
		
		String table = "test";
		String column = "FirstName";
		
		// Insert into database
		result = portal.insert(content, table, column);
		assertTrue(result >= 0);
		
		// Verify people were inserted correctly
		ArrayList<String> extractedContents = portal.query("SELECT * FROM test", "FirstName");
		assertEquals(extractedContents.size(), 1);
		
		// Delete the rows that were just inserted
		int rows = portal.deleteRowsInTable(table);
		assertEquals(rows, 1);
		
		// Attempt to grab all users in the table
		extractedContents = portal.query("SELECT * FROM test", "FirstName");
		assertEquals(extractedContents.size(), 0);
	}

	/**
	 * Test method for {@link database.MysqlPortal#update(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateStringStringStringStringString() {

		int result = -1;
		
		MysqlPortal portal = new MysqlPortal();
		
		// Initialize 1 person
		String content = "Person1";
		String table = "test";
		String column = "FirstName";
		String value = "PersonX";
		String where = "Firstname";
		String whereValue = "Person1";
		
		// Insert into database
		result = portal.insert(content, table, column);
		assertTrue(result >= 0);
		
		// Verify people were inserted correctly
		ArrayList<String> extractedContents = portal.query("SELECT * FROM test", "FirstName");
		assertEquals(extractedContents.get(0), "Person1");
		
		portal.update(table, column, value, where, whereValue);
		
		// Verify update completed correctly
		extractedContents = portal.query("SELECT * FROM test", "FirstName");
		assertEquals(extractedContents.get(0), "PersonX");
		
		// Delete the rows that were just inserted
		int rows = portal.deleteRowsInTable(table);
		assertEquals(rows, 1);
		
		// Attempt to grab all users in the table
		extractedContents = portal.query("SELECT * FROM test", "FirstName");
		assertEquals(extractedContents.size(), 0);
	}

	/**
	 * Test method for {@link database.MysqlPortal#update(java.lang.String, java.lang.String, int, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateStringStringIntStringString() {

		int result = -1;
		
		MysqlPortal portal = new MysqlPortal();
		
		// Initialize 1 person
		String content = "Person1";
		String table = "test";
		String column = "FirstName";
		int value = 777;
		String where = "Age";
		String whereValue = null;
		
		// Insert into database
		result = portal.insert(content, table, column);
		assertTrue(result >= 0);
		
		// Verify people were inserted correctly
		ArrayList<String> extractedContents = portal.query("SELECT * FROM test", "Firstname");
		assertEquals(extractedContents.get(0), "Person1");
		
		extractedContents = portal.query("SELECT * FROM test", "Age");
		assertEquals(extractedContents.get(0), null);
		
		column = "Age";
		where = "FirstName";
		whereValue = "content";
		
		// UPDATE <table> SET <column> = '<value>' WHERE <where> ='<whereValue>'
		portal.update(table, column, value, where, whereValue);
		
		// Verify update completed correctly
		extractedContents = portal.query("SELECT * FROM test", "Age");
		assertEquals(extractedContents.get(0), 777);
		
		// Delete the rows that were just inserted
		int rows = portal.deleteRowsInTable(table);
		assertEquals(rows, 1);
		
		// Attempt to grab all users in the table
		extractedContents = portal.query("SELECT * FROM test", "Age");
		assertEquals(extractedContents.size(), 0);
	}

	/**
	 * Test method for {@link database.MysqlPortal#get(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	public void testGet() {
		
		boolean result = false;
		
		MysqlPortal portal = new MysqlPortal();
		
		// Initialize 2 people
		ArrayList<String> contents = new ArrayList<String>();
		contents.add("Person1");
		
		String table = "test";
		String column = "FirstName";
		
		// Insert into database
		result = portal.insert(contents, table, column);
		assertTrue(result);
		
		String what = "FirstName";
		String whereValue = "Person1";
		
		String retrieved = portal.get(what, table, column, whereValue);
		assertEquals(retrieved, "Person1");
	}

	/**
	 * Test method for {@link database.MysqlPortal#query(java.lang.String)}.
	 */
	@Test
	public void testDeleteRowsInTable() {

		boolean result = false;
		
		MysqlPortal portal = new MysqlPortal();
		
		// Initialize 2 people
		ArrayList<String> contents = new ArrayList<String>();
		contents.add("Person1");
		
		String table = "test";
		String column = "FirstName";
		
		// Insert into database
		result = portal.insert(contents, table, column);
		assertTrue(result);
		
		// Verify update completed correctly
		ArrayList<String> extractedContents = portal.query("SELECT * FROM test", "FirstName");
		assertEquals(extractedContents.size(), 1);
		
		int rows = portal.deleteRowsInTable(table);
		assertEquals(rows, 1);
	}

}
