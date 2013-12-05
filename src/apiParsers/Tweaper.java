package apiParsers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import database.MysqlPortal;
import facebook.FacebookClient.Domain;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Tweaper {

	public final static String SEPARATOR = "|";
	TwitterStream twitterStream;
	StatusListener listener;
	PrintWriter pw; 

	public Tweaper(){
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter("tweets", true)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		twitterStream = new TwitterStreamFactory().getInstance();
		listener = new StatusListener() {
			@Override
			public void onStatus(Status status) {
				try {
					String record = status.getText() + SEPARATOR + status.getPlace() + "\n";
					//System.out.println(record);
					pw.write(record);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText()+ " - "+status.getGeoLocation()+ status.getPlace());
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

		twitterStream.addListener(listener);
	}

	public Tweaper(String keyword, Domain d, MysqlPortal mysql){
		twitterStream = new TwitterStreamFactory().getInstance();
		listener = new SpecificListener(keyword, d, mysql);
		twitterStream.addListener(listener);
	}

	public void filterSample(String[] keywords){
		FilterQuery fq = new FilterQuery();
		fq.track(keywords);
		double[][] bb= {{-180, -90}, {180, 90}};
		fq.locations(bb);
		twitterStream.filter(fq);
	}

	public void overallSample(){
		FilterQuery filter = new FilterQuery();    
		double[][] bb= {{-180, -90}, {180, 90}};
		filter.locations(bb);
		twitterStream.filter(filter);
	}

	public static void main(String[] args){
		new Tweaper().filterSample(new String[]{"muse"});
	}

}
