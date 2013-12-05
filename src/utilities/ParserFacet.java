/* Author: Donald Siuchninski
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

package utilities;

import java.util.ArrayList;

public interface ParserFacet {
	
	// Tokenizes input
	public boolean tokenize ( String pathname,				//[in] Relative pathname 
						   	  ArrayList<String> contents);	//[out] List of all file contents
	
	// Reads line by line
	public boolean lineRead ( String filename,				//[in] Relative pathname 
							  ArrayList<String> contents);	//[out] List of all file contents
	
	public boolean extractFBMovieLinks (String filename,
										ArrayList<String> contents);
	
}
