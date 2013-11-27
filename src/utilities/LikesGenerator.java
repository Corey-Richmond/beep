
package utilities;

import java.util.ArrayList;
import java.util.Random;

import quicktime.std.movies.MovieController;

import database.MysqlPortal;

public class LikesGenerator implements LikesFacet{
	
	public void generateLikes (int numberOfLikes, ArrayList<Integer> likes){
		
		// Finds the number of ways to divide the likes
		int likesToGenerate = 50;
		
		for ( int x = 0 ; x < likesToGenerate; x += 1 ){
			
			double rand = Math.random();
			
			// If rand is greater then threshold and less then an amount, generate new rand
			//while (rand > (1.0/likesToGenerate) || rand > (1.0/((likesToGenerate*.5)+likesToGenerate)))
			while (rand > (1.0/likesToGenerate))
				rand = Math.random();
			
			likes.add((int)(numberOfLikes*rand));
		}
		
		
	}
	
	
	public void connectMoviesAndCities(){
		MysqlPortal msp = new MysqlPortal();
		int movieCount = Integer.parseInt(msp.query("select count(*) from movie", "count(*)").get(0));
		int cityCount  = Integer.parseInt(msp.query("select count(*) from city" , "count(*)").get(0));
		
		Random generator = new Random();
		int randomCity;
		System.out.println(movieCount +" , " + cityCount );
		for(int i=1; i < movieCount; ++i){
			for (int j=1; j<6; ++j){
				randomCity = generator.nextInt(cityCount) + 1;
				msp.insert( "NULL, "+randomCity+", "+i+", "+j , "MovieCitiesList");
			}
		}
		
	}
}
