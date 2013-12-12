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
	String sportName;
	//  Database credentials
	static final String USER = "student";
	static final String PASS = "441";
	static final String jdbcDriver = "com.mysql.jdbc.Driver";

	// -------------------------------------------------------------------------------|

	
	// changes done by Anusha
	
	public ArrayList<String> query1(String query){

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
		//	1 cond	
				//String name = rs.getString("name");
				//String addr = rs.getString("address");
				// changes for venue
				/*String name = rs.getString("name");
				String addr = rs.getString("address");*/
	            String name1 = rs.getString("cityName");
				String addr1 = rs.getString("cityState");
			//	result.add(name+","+addr+","+name1+","+addr1);
				result.add(name1+","+addr1);
			//	String title = rs.getString("title");
			//	result.add(title);
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
	
	
	// changes done by Anusha
	
		public ArrayList<String> query2(String query){

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
					String title = rs.getString("K.title");
		            String name = rs.getString("cityName");
					result.add(title+"," +name);
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
	
		
		// changes done by Anusha
		
			public ArrayList<String> query3(String query){

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
						String title = rs.getString("name");
						String addr = rs.getString("address");
			            String name = rs.getString("cityName");
						result.add(title+"," +addr +" "+name);
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
		
			
			// changes done by Anusha
			
				public String query4(String query){

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
							sportName = rs.getString("name");	
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
							return sportName;
					}//end try

					return sportName;
				}
				
				// changes done by Anusha
				
				public ArrayList<String> query5(String query){

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
							String title = rs.getString("name");
							String addr = rs.getString("address");
				            String name = rs.getString("cityName");
							result.add(title+"," +addr +" "+name);
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
				
				// changes done by anusha
				public String query6(String query){

					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;
					String name="";

					try{
						//STEP 2: Register JDBC driver
						Class.forName(jdbcDriver);

						//STEP 3: Open a connection
						conn = DriverManager.getConnection(DB_URL,USER,PASS);

						//STEP 4: Execute a query
						stmt = conn.createStatement();

						rs = stmt.executeQuery(query);
						while (rs.next()) {
					
				            name = rs.getString("name");
						
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
							return name;
					}//end try

					return name;
				}
		
				
				// changes done by anusha
				public String query7(String query){

					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;
					String name="";
					

					try{
						//STEP 2: Register JDBC driver
						Class.forName(jdbcDriver);

						//STEP 3: Open a connection
						conn = DriverManager.getConnection(DB_URL,USER,PASS);

						//STEP 4: Execute a query
						stmt = conn.createStatement();

						rs = stmt.executeQuery(query);
						while (rs.next()) {
				            name = rs.getString("teamName");
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
							return name;
					}//end try

					return name;
				}
		
				// changes done by anusha
				public ArrayList<String> query8(String query){

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
				            String name1 = rs.getString("cityName");
							String addr1 = rs.getString("cityState");
							result.add(name1+","+addr1);
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


	public boolean insertActorFull(String fname, String mname, String lname, int genreID, int likes, int cityID) {
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

			sql = "insert into Actor(personID, totalLikes) "+ 
			"values(" +
			"(select personID from Person where firstName = '"+fname+"' and middleName = '"+mname+"' and lastName = '"+lname+"' limit 1 )," +
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

			sql = "insert into ActorCitiesList(cityID, actorID, cityRank)"+
			"values("+
			"(select cityID from City where cityID = "+cityID+"),"+
			"(select Actor.actorID from Actor inner join Person on Actor.personID = Person.personID where Person.firstName = '"+fname+"' and Person.middleName = '"+mname+"' and Person.lastName = '"+lname+"' limit 1),"+
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

			sql = "insert into ConcertVenue(name, address, cityID) select '"
				+ name + "', '" + address + "' , cityID from City where City.cityName = '"+city+"';";
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
	public int insert(String content, String table){

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

			sql = "INSERT into "+ table +" values("+ content +")";
			System.out.println(sql);
			id = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

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
				return id;
		}//end try

		return id;
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

			if(whereValue.contains("'"))
				whereValue = whereValue.replace("'", "");
			
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
			//se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			//e.printStackTrace();
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


	public List<String> getCities(){
		List<String[]> cities = query("select * from City;", "cityName");
		List<String> result = new ArrayList<String>();
		for(String[] array : cities){
			if(array.length > 0){
				result.add(array[0]);
			}
		}
		return result;
	}
	
	// -------------------------------------------------------------------------------|

	public int getDomainID(Domain d, String name){
		name = name.replace("'", "");
		try{
			switch(d){
			case ARTIST:
				return Integer.parseInt(query("select artistID from Artist JOIN Person on Artist.personID = Person.personID where concat(Person.firstName, ' ', Person.middleName, ' ', Person.lastName) = '" + name + "';", "artistID").get(0)[0]);
			case ATHLETE:
				return Integer.parseInt(query("select athleteID from Athlete JOIN Person on Athlete.personID = Person.personID where concat(Person.firstName, ' ', Person.middleName, ' ', Person.lastName) = '" + name + "';", "athleteID").get(0)[0]);
			case MOVIE:
				return Integer.parseInt(query("select movieID from Movie where title = '" + name + "';", "movieID").get(0)[0]);
			case TEAM:
				return Integer.parseInt(query("select teamID from Team where teamName = '" + name + "';", "teamID").get(0)[0]);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return -1;
	}
	
	// -------------------------------------------------------------------------------|
	
	public int getCityID(String cityName){
		try {
			return Integer.parseInt(query("select cityID from City where cityName = '" + cityName + "';", "cityID").get(0)[0]);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getCitiesListID(Domain d, String target, String cityName){
		int targetID = getDomainID(d, target);
		int cityID = getCityID(cityName);
		int citiesListID = -1;
		switch(d){
		case ARTIST:
			try {
				citiesListID = query("select artistsCitiesListID from ArtistCitiesList where artistID  = " + targetID + " and cityID = " + cityID + ";").getInt(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case ATHLETE:
			try {
				citiesListID = query("select athleteCitiesListID from AthleteCitiesList where athleteID  = " + targetID + " and cityID = " + cityID + ";").getInt(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case MOVIE:
			try {
				citiesListID = query("select citiesListID from MovieCitiesList where movieID  = " + targetID + " and cityID = " + cityID + ";").getInt(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case TEAM:
			try {
				citiesListID = query("select teamCitiesListID from TeamCitiesList where teamID  = " + targetID + " and cityID = " + cityID + ";").getInt(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		return citiesListID;
	}
	
	public int insertNewCitiesListRecord(Domain d, String target, String cityName){
		int newID = -1;
		int cityID = getCityID(cityName);
		int targetID; 
		switch(d){
		case ARTIST:
			targetID = getDomainID(d, target);
			newID = insert( "" + cityID, "ArtistCitiesList", "cityID");
			update("ArtistCitiesList", "artistID", "" + targetID , "artistsCitiesListID", "" + newID);
			break;
		case ATHLETE:
			targetID = getDomainID(d, target);
			newID = insert( "" + cityID, "AthleteCitiesList", "cityID");
			update("AthleteCitiesList", "athleteID", "" + targetID , "athleteCitiesListID", "" + newID);
			break;
		case MOVIE:
			targetID = getDomainID(d, target);
			newID = insert( "" + cityID, "MovieCitiesList", "cityID");
			update("MovieCitiesList", "movieID", "" + targetID , "citiesListID", "" + newID);
			break;
		case TEAM:
			targetID = getDomainID(d, target);
			newID = insert( "" + cityID, "TeamCitiesList", "cityID");
			update("teamCitiesListID", "teamID", "" + targetID , "teamCitiesListID", "" + newID);
			break;
		}
		
		return newID;
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
		String table = "";
		String idColumn = "";
		String listIDColumn = "";
		int targetID = getDomainID(d, name);		
		int cityID = getCityID(cityName);
		int citiesListID = -1;

		switch(d){
		case ARTIST:
			table = "ArtistCitiesList";
			idColumn = "artistID";
			listIDColumn = "artistsCitiesListID";
			try {
				citiesListID = Integer.parseInt(query("select artistsCitiesListID from " + table + " where " + idColumn + " = " + targetID + " and cityID = " + cityID + ";", "artistsCitiesListID").get(0)[0]);
			} catch (Exception e) {
				if(citiesListID < 0){
					citiesListID = insertNewCitiesListRecord(d, name, cityName);
				}
			}
			break;
		case ATHLETE:
			table = "AthleteCitiesList";
			idColumn = "athleteID";
			listIDColumn = "athleteCitiesListID";
			try {
				citiesListID = Integer.parseInt(query("select athleteCitiesListID from " + table + " where " + idColumn + " = " + targetID + " and cityID = " + cityID + ";", "athleteCitiesListID").get(0)[0]);
			} catch (Exception e) {
				e.printStackTrace();
				if(citiesListID < 0){
					citiesListID = insertNewCitiesListRecord(d, name, cityName);
				}
			}
			break;
		case MOVIE:
			table = "MovieCitiesList";
			idColumn = "movieID";
			listIDColumn = "citiesListID";
			try {
				citiesListID = Integer.parseInt(query("select citiesListID from " + table + " where " + idColumn + " = " + targetID + " and cityID = " + cityID + ";", "citiesListID").get(0)[0]);
			} catch (Exception e) {
				//e.printStackTrace();
				if(citiesListID < 0){
					citiesListID = insertNewCitiesListRecord(d, name, cityName);
				}
			}
			break;
		case TEAM:
			table = "TeamCitiesList";
			idColumn = "teamID";
			listIDColumn = "teamCitiesListID";
			try {
				citiesListID = Integer.parseInt(query("select TeamCitiesList from " + table + " where " + idColumn + " = " + targetID + " and cityID = " + cityID + ";", "teamCitiesListID").get(0)[0]);
			} catch (Exception e) {
				e.printStackTrace();
				if(citiesListID < 0){
					citiesListID = insertNewCitiesListRecord(d, name, cityName);
				}
			}
			break;
		}
		int oldLikes = Integer.parseInt(query("select likes from " + table + " where " + listIDColumn + " = " + citiesListID + ";", "likes").get(0)[0]);
		update(table, "likes", "" + (oldLikes + likes), listIDColumn, "" + citiesListID);
		//query("update " + table + " set likes = (likes + " + likes + ") where cityID = " + cityID + " and " + idColumn + " = '" + targetID + "';" );
	}
	
	public boolean isAlreadyInDB(Domain d, String name){
		if(getDomainID(d, name) < 0)
			return false;
		return true;
	}
}
// -------------------------------------------------------------------------------|
