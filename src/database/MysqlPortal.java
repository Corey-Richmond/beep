/* University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package database;

import java.sql.*;
import java.util.ArrayList;

public class MysqlPortal implements MysqlFacet{
	
	// -------------------------------------------------------------------------------|
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/beep";

	//  Database credentials
	static final String USER = "student";
	static final String PASS = "441";
	
	static final String jdbcDriver = "com.mysql.jdbc.Driver";
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * Allows ANY MySql string query to be passed
	 * 
	 * @param query Any valid mysql string query
	 * @return True when complete
	 */
	public ArrayList<String> query(String query, String column){

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> result = new ArrayList<String>();

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				result.add(rs.getString(column));
			}

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		finally{
			if (!close(conn, stmt))
				return result;
		}//end try

		return result;
	}
	
	// -------------------------------------------------------------------------------|
	

	/**
	 * INSERT into <table> (<column>) values('<contents>')
	 * 
	 * For a given table and column, continuously insert all items into the 
	 * table as new entries
	 * 
	 * @param contents List of VARCHAR items to insert
	 * @param table Mysql table to insert into
	 * @param column Mysql column to insert into
	 * @return True when complete
	 */
	public boolean insert(ArrayList<String> contents, String table, String column){

		Connection conn = null;
		Statement stmt = null;
		int listSize = contents.size();

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;

			for (int x = 0 ; x < listSize ; x += 1){
				sql = "INSERT into "+ table +" ("+ column +") values('"+ contents.get(x) +"')";
				stmt.execute(sql);
			}

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		finally{
			if (!close(conn, stmt))
				return false;
		}//end try

		return true;
	}
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * INSERT into <table> (<column>) values('<content>')
	 * 
	 * For a given table and column, insert VARCHAR content
	 * 
	 * @param content VARCHAR item to insert
	 * @param table Mysql table to insert into
	 * @param column Mysql column to insert into
	 * @return True when complete
	 */
	public boolean insert(String content, String table, String column){

		Connection conn = null;
		Statement stmt = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;

			sql = "INSERT into "+ table +" ("+ column +") values('"+ content +"')";
			stmt.execute(sql);

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		finally{
			if (!close(conn, stmt))
				return false;
		}//end try

		return true;
	}
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * UPDATE <table> SET <column> = '<value>' WHERE <where> ='<whereValue>'
	 * 
	 * Inserts one single VARCHAR item into table at column where a condition
	 * holds true
	 * 
	 * @param table Table to insert into
	 * @param column Column to insert into
	 * @param value VARCHAR value to insert
	 * @param where Column whose value must be satisfied in the query
	 * @param whereValue Value of the column that must be satisfied
	 * @return True when complete
	 */
	public boolean update(String table, String column, String value, String where, String whereValue){

		Connection conn = null;
		Statement stmt = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;

			sql = "UPDATE "+table+" set "+column+" = '"+value+"' WHERE "+where+" ='"+whereValue+"'";
			stmt.execute(sql);

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		finally{
			if (!close(conn, stmt))
				return false;
		}//end try

		return true;
	}
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * UPDATE <table> SET <column> = '<value>' WHERE <where> ='<whereValue>'
	 * 
	 * Inserts one single VARCHAR item into table at column where a condition
	 * holds true
	 * 
	 * @param table Table to insert into
	 * @param column Column to insert into
	 * @param value int value to insert
	 * @param where Column whose value must be satisfied in the query
	 * @param whereValue Value of the column that must be satisfied
	 * @return True when complete
	 */
	public boolean update(String table, String column, int value, String where, String whereValue){

		Connection conn = null;
		Statement stmt = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;

			sql = "UPDATE "+table+" SET "+column+" = "+value+" WHERE "+where+" = '"+whereValue+"'";
			stmt.execute(sql);

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		finally{
			if (!close(conn, stmt))
				return false;
		}//end try

		return true;
	}
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * SELECT <what> FROM <table> WHERE <where> = '<whereValue>'
	 * 
	 * Retrieves string value from database
	 * 
	 * @param what Column to retrieve value from
	 * @param table Which table to retrieve from
	 * @param where Column whose value needs to be matched
	 * @param whereValue Value of 'where' column to be matched
	 * @return String value from database
	 */
	public String get(String what, String table, String where, String whereValue, ArrayList<String> contents){
		Connection conn = null;
		Statement stmt = null;

		String returnValue = "";
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			
			sql = "SELECT "+what+" FROM "+table+ " WHERE "+where+" = '"+whereValue+"'";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			rs.next();
			returnValue = rs.getString(what);

			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}

		return returnValue;
	}
	
	// -------------------------------------------------------------------------------|
	
	/**
	 * Closes connection
	 * 
	 * @param connection Connection to close
	 * @param statement 
	 * @return True if successful, else false
	 */
	private boolean close (Connection connection, Statement statement){
		//finally block used to close resources
		try{
			if(statement!=null)
				statement.close();
		}
		catch(SQLException se2){
			se2.printStackTrace();
			return false;
		}
		try{
			if(connection!=null)
				connection.close();
		}
		catch(SQLException se){
			se.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public ResultSet query(String query) {
		// TODO Auto-generated method stub
		return null;
	}
}

	// -------------------------------------------------------------------------------|
