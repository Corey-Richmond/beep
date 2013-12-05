/* Authors: Corey Richmond, Felix Rodriguez
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */
package driver;

import java.util.ArrayList;

import utilities.LikesGenerator;
import utilities.Parser;
import utilities.zipUtil;

import database.MysqlPortal;
import facebook.FacebookClient;
import facebook.FacebookClient.Domain;


public class DatabasePopulator {

	public void populateDatabase(){
		//*****************STARTS DATABASE POPULATION*******************************
		MysqlPortal p = new MysqlPortal();
		p.createDataBase();
		
		// Read contents from file
		ArrayList<String> contents = new ArrayList<String>();
		Parser parser = new Parser();
//		parser.extractFBMovieLinks("./files/raw_facebook.txt", contents);
		
		
		
		// Grab movie info and populate database
		FacebookClient getter = new FacebookClient();
		getter.getData("./files/felix.txt", "./files/json_movies.txt", Domain.MOVIE);
		getter.getData("./files/felix/Data/athletes.txt", "./files/json_athletes.txt", Domain.ATHLETE);
		getter.getData("./files/felix/Data/bandsURL.txt", "./files/json_bands.txt", Domain.ARTIST);
		getter.getData("./files/felix/Data/showsURLs.txt", "./files/json_shows.txt", Domain.MOVIE);
		getter.getData("./files/felix/Data/teams.txt", "./files/json_teams.txt", Domain.TEAM);


		try{
			parser.parseCities();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		try{
			parser.parseMovieGenres();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		try{
			parser.parseMusicGenres();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		try{
			parser.parseArtists();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		try{
			parser.parseTheatres();
		} catch (Exception e){
			e.printStackTrace();
		}
		try {
			parser.parseMusicVenues();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		LikesGenerator like = new LikesGenerator();
		like.connectMoviesAndCities();
		
		//*****************ENDS DATABASE POPULATION*******************************

	}
	
	public static void main(String[]args){
		new DatabasePopulator().populateDatabase();
	}
	
}
