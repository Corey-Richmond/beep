package apiParsers;

import database.MysqlPortal;
import facebook.FacebookClient.Domain;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class SpecificListener implements StatusListener{

	MysqlPortal mysql;
	Domain domain;
	String target;
	
	public SpecificListener(String keyword, Domain d,  MysqlPortal p){
		mysql = p;
		target = keyword;
		domain = d;
	}
	
	@Override
	public void onStatus(Status status) {
		if(containsKeyword(status.getText(), target)){
			System.out.println(status.getText());
			String cityName = status.getPlace().getName();
			if(mysql.getCities().contains(cityName)){
				mysql.incrementLikes(domain, target, cityName, 1);
			}
		}
	}
	
	public boolean containsKeyword(String text, String keyword){
		keyword = keyword.toLowerCase();
		text = text.toLowerCase();
		String[] split = keyword.split(" ");
		int count = 0;
		for(String s : split){
			if(text.contains(s))
				count++;
		}
		int size = split.length;
		if(count > size / 2)
			return true;
		return false;
	}
	
	@Override
	public void onException(Exception arg0) {	
	}
	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {		
	}
	@Override
	public void onScrubGeo(long arg0, long arg1) {		
	}
	@Override
	public void onStallWarning(StallWarning arg0) {		
	}
	@Override
	public void onTrackLimitationNotice(int arg0) {		
	}

}
