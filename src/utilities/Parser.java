package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class Parser implements ParserFacet{

	public boolean tokenize (String pathname, ArrayList<String> contents){
		
		if (pathname == null || contents == null){
			return false;
		}
		
		File file = new File(pathname);
		
		Scanner inputStream = null;
		
		try{
			inputStream = new Scanner(new FileInputStream(file));
		}
		
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		while (inputStream.hasNext())
			contents.add(inputStream.next());
		
		inputStream.close();
		
		return true;
	}
	
	public boolean lineRead (String pathname, ArrayList<String> contents){
		
		if (pathname == null || contents == null){
			return false;
		}
		
		File file = new File(pathname);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			//PrintWriter writer = new PrintWriter("movies_regex.txt", "UTF-8");
			
			while (reader.ready()){
				 String line = reader.readLine();
				 
				 if (line.length() > 0){
					 line = line.replaceAll("\\d*.\\W*\\d.\\d*\\W\\b", "");
					 line = line.replaceAll("\\W\\(.*", "");
					 contents.add(line);
					 //writer.println(line);
				 }
			}
			
			reader.close();
			//writer.close();
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	
	public boolean extractFBMovieLinks (String pathname, ArrayList<String> contents){
		
		if (pathname == null || contents == null){
			return false;
		}
		
		File file = new File(pathname);
		
		Scanner inputStream = null;
		
		try{
			inputStream = new Scanner(new FileInputStream(file));
			
			PrintWriter writer = new PrintWriter("./files/extracted_https.txt", "UTF-8");
			
			inputStream.useDelimiter("\"");
			
			while (inputStream.hasNext()){
				inputStream.findInLine("_7kf _8o _8s lfloat");
				inputStream.findInLine("\"");
				inputStream.findInLine("\"");
				String extractedString = inputStream.next();
				if(extractedString.contains("https://www.facebook.com/"))
					writer.println(extractedString);
			}
			
			//writer.println("Finished.");
			writer.close();
			inputStream.close();
		}
		
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
