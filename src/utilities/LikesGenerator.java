
package utilities;

import java.util.ArrayList;

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
}
