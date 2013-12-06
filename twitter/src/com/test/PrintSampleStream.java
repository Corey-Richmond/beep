package com.test;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import twitter4j.*;

/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class PrintSampleStream {
    /**
     * Main entry of this application.
     *
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws TwitterException, IOException {
    	//String filename= args[0];
    	File f = new File(args[1]);
    	ArrayList<String> al = new ArrayList<>();
    	Scanner sc = new Scanner(new File(args[1]));    	
    	int i=0;
    	while(sc.hasNextLine()) {
    		al.add(sc.nextLine());
    		i++;
    	}
    	PrintStream ps = new PrintStream(new File(args[0]));
    	System.setOut(ps);
    	String keywords [] = al.toArray(new String[0]);
    	System.out.println(keywords.length);
        //final FileWriter fw = new FileWriter(filename,true); //the true will append the new data
        //fw.write("add a line\n");//appends the string to the file
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
            	//try {
            		
					//fw.write(status.getText() + "\n\n");//+ " "+ status.getText()+ "  "+ status.getPlace());
					
				//} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
                //System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText()+ " - "+status.getGeoLocation()+ status.getPlace());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {            }

            @Override
            public void onStallWarning(StallWarning warning) {            }

            @Override
            public void onException(Exception ex) {            }
        };
        
        FilterQuery fq = new FilterQuery();

        fq.track(keywords);
        twitterStream.addListener(listener);
        twitterStream.filter(fq);
       // twitterStream.sample();
    }
}