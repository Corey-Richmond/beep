package apiParsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * usage:
 * arg[0] = input file
 * arg[1] = output file
 */

public class blahParse {
	public static void main(String [] blargs) throws IOException {
		System.out.println("just parsin");
		String inGo="", outGo="";
		if(blargs.length<2){
			inGo = "twitter4j/outPut.txt";
			outGo = "twitter4j/twitterOutput.txt";
		}
		
		

	   File file = new File("twitterOutput.txt");
	      // creates the file
	   file.createNewFile();
	      // creates a FileWriter Object
	   FileWriter writer = new FileWriter(file); 
	      
		
		File f = new File(outGo);
    	ArrayList<String> al = new ArrayList<>();
    	Scanner sdc = new Scanner(new File(outGo));    	
    	int i=0;
    	while(sdc.hasNextLine()) {
    		al.add(sdc.nextLine());
    		i++;
    	}
		sdc.close();
    	String keywords [] = al.toArray(new String[0]);
		
		Scanner sc = new Scanner(new File(inGo));
		String s="";
		while(sc.hasNextLine()) {
			s = sc.nextLine();
			if(s.contains("Received:{")) {
				break;
			}
		}
		
		for(int ds=0; ds<keywords.length; ds++) {
			System.out.println(keywords[ds]);
		}
		
		String loc="", text="";
		boolean ttt = true;
		while(sc.hasNextLine()) {
			s = sc.nextLine();
			if(s.contains(",\"text\":")) {
				text = s.substring(s.indexOf(",\"text\":")+9, s.indexOf(",\"geo\"")-1);
				//System.out.println(t);
			} else { text = ""; }
			
			if(s.contains("{\"location\"")) {
				loc = s.substring(s.indexOf("{\"location\"")+13, s.indexOf(",\"default_profile\"")-1);
				//System.out.println(loc);
			} else { loc = ""; }
			for(int c=0; c<keywords.length; c++) {
				if(text.contains(keywords[c]) || loc.contains(keywords[c])) {
					writer.write(keywords[c]+ ":" + text + System.getProperty( "line.separator" ));
				}
			}
			
			
		}
		writer.flush();
		writer.close();
	}
}
