/* Authors: Donald Siuchninski, Corey Richmond, Felix Rodriguez & Patrick Masier
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import facebook.FacebookClient.Domain;

import utilities.Parser;

public class MysqlPortal implements MysqlFacet{

	// -------------------------------------------------------------------------------|

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/beep";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "";

	static final String jdbcDriver = "com.mysql.jdbc.Driver";

	// -------------------------------------------------------------------------------|

	/**
	 * Execute any custom query and return an ArrayList<String> of results
	 * @param query
	 * @param column
	 * @return ArrayList<String> 
	 */
	public ArrayList<String[]> query(String query, String column){

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> columns = getArrayOfColumns(column);
		ArrayList<String[]> result = new ArrayList<String[]>();

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String a[]= new String[columns.size()];
				for(int i = 0; i<columns.size(); ++i) {
					a[i] = rs.getString(columns.get(i));
					//System.out.println(a[i]);
				}
				result.add(a);
			}

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
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
	
	public ArrayList<String> getArrayOfColumns(String column){
		column = column.replaceAll(" ","");
		ArrayList<String> strs = new ArrayList<String>();
		int end = 0;
		int start = 0;
		do {
			end = column.indexOf(',' , start);
			if(end > 0) {
				strs.add(column.substring(start, end));
				//System.out.println(column.substring(start, end));
			} else {
				strs.add(column.substring(start));
				//System.out.println(column.substring(start));
			}
			start = end+1;
		} while(start > 0);

		return strs;
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
	 * @return id of last insertion, -1 if there was an error
	 */
	public int insert(String content, String table, String column){

		Connection conn = null;
		Statement stmt = null;
		int id = -1;

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;

			if(content.contains("'")){
				content = content.replace("'", "");
			}

			sql = "INSERT into "+ table +" ("+ column +") values('"+ content +"')";
			id = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

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
				return id;
		}//end try

		return id;
	}

	public boolean insertMovieGenres(String genre, int likes){

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

			sql = "INSERT into moviegenre (genre, totalLikes) values('"+ genre +"', '"+likes+"')";
			stmt.execute(sql);

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
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


	public boolean insertArtistFull(String fname, String mname, String lname, int genreID, int likes, int cityID) {
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

			sql = "insert into Person(firstName, middleName, lastName) values ('"+ fname +"', '"+mname+"', '"+ lname +"')";
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

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;

			sql = "insert into Artist(personID, musicGenreID, totalLikes) "+ 
			"values(" +
			"(select personID from Person where firstName = '"+fname+"' and middleName = '"+mname+"' and lastName = '"+lname+"' limit 1 )," +
			"(select musicGenreID from musicGenre where musicGenreID = "+genreID+"), " +
			"(totalLikes = "+likes+"));";
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


		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;

			sql = "insert into ArtistCitiesList(cityID, artistID, cityRank)"+
			"values("+
			"(select cityID from City where cityID = "+cityID+"),"+
			"(select Artist.artistID from Artist inner join Person on Artist.personID = Person.personID where Person.firstName = '"+fname+"' and Person.middleName = '"+mname+"' and Person.lastName = '"+lname+"' limit 1),"+
			"(cityRank = 2));";
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


	public boolean insertAthleteFull(String fname, String mname, String lname, int likes, int rating, int teamID) {
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

			sql = "insert into Person(firstName, middleName, lastName) values ('"+ fname +"', '"+mname+"', '"+ lname +"')";
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

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;

			sql = "insert into Athlete(personID, totalLikes, rating, teamID) "+ 
			"values(" +
			"(select personID from Person where firstName = '"+fname+"' and middleName = '"+mname+"' and lastName = '"+lname+"' limit 1 )," +
			"(totalLikes = "+likes+"), " +
			"(rating = " +rating+")," +
			"(teamID =" +teamID+") );";
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

	public boolean insertMusicGenre(String content, int likes){

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

			sql = "insert into musicgenre(genre, totalLikes) values ('"+ content +"',"+ likes +")";
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

	/*insert into concertvenue(name, address, cityID)
	select 'New York', '123FAKE', cityID
	from city 
	where city.cityName = 'New York'
	 */
	public boolean insertConcert(String name, String address, String city) {
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

			sql = "insert into concertvenue(name, address, cityID) select '"
				+ name + "', '" + address + "' , cityID from city where city.cityName = '"+city+"';";
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
	 * INSERT into <table> values('<content>')
	 * 
	 * For a given table and column, insert VARCHAR content
	 * 
	 * (UNUSED)
	 * 
	 * @param content VARCHAR item to insert
	 * @param table Mysql table to insert into
	 * @return True when complete
	 */
	public boolean insert(String content, String table){

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

			sql = "INSERT into "+ table +" values("+ content +")";
			System.out.println(sql);
			stmt.execute(sql);

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
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
	public String get(String what, String table, String where, String whereValue){
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

			String sql = "";

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
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}

		return returnValue;
	}

	// -------------------------------------------------------------------------------|

	/**
	 * Deletes all rows in a given table
	 * @param Table name
	 * @return Number of rows affected by delete
	 */
	public int deleteRowsInTable(String table){

		Connection conn = null;
		Statement stmt = null;
		int rowCount = 0;
		ArrayList<String> result = new ArrayList<String>();

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			rowCount = stmt.executeUpdate("DELETE FROM " + table);

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		finally{
			if (!close(conn, stmt))
				return rowCount;
		}//end try

		return rowCount;
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
	
	// -------------------------------------------------------------------------------|

	@Override
	public ResultSet query(String query) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

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

		return rs;
	}

	// -------------------------------------------------------------------------------|
	
	public void createDataBase(){
		Connection conn = null;
		Statement stmt = null;
		System.out.println("Starting to populate database");
		String file = "Create_441_DB/create_table.sql";
		try {
			//STEP 2: Register JDBC driver
			Class.forName(jdbcDriver);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/",USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();
			Parser p = new Parser();
			ArrayList<String> temp = new ArrayList<String>();
			p.lineReadGeneric(file , temp);

			String str= "";
			for (int i=0; i<temp.size(); ++i){
				str += temp.get(i);
				if(str.length()>0 && str.charAt(str.length()-1) == ';'){
					stmt.executeUpdate(str);
					str = "";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// -------------------------------------------------------------------------------|


	public List<String[]> getCities(){
		return query("select * from City;", "cityName");
	}
	
	// -------------------------------------------------------------------------------|

	public int getDomainID(Domain d, String name){
		name = name.replace("'", "");
		try{
			switch(d){
			case ARTIST:
				return query("select artistID from Artist JOIN Person on Artist.personID = Person.personID where concat(Person.firstName, ' ', Person.middleName, ' ', Person.lastName) = '" + name + "';").getInt(1);
			case ATHLETE:
				return query("select AthleteID from Athlete JOIN Person on Athlete.personID = Person.personID where concat(Person.firstName, ' ', Person.middleName, ' ', Person.lastName) = '" + name + "';").getInt(1);
			case MOVIE:
				return query("select movieID from Movie where title = '" + name + "';").getInt(1);
			case TEAM:
				return query("select teamID from Team where teamName = '" + name + "';").getInt(1);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	// -------------------------------------------------------------------------------|
	
	public int getCityID(String cityName){
		try {
			return query("select cityID from City where cityName = '" + cityName + "';").getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	// -------------------------------------------------------------------------------|

	/**
	 * 
	 * @param d domain (MOVIE, ARTIST, TEAM, ATHLETE)
	 * @param name name of object being tracked
	 * @param cityName name of city to add likes
	 * @param likes likes to be added to like count
	 */
	public void incrementLikes(Domain d, String name,String cityName, int likes){
		//TODO
		
	}
}
// -------------------------------------------------------------------------------|
