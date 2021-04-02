package au.com.domain.issuetrackerdomain.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import au.com.domain.issuetrackerdomain.model.Issue;

public class CreatedComparator implements Comparator<Issue> {

	@Override
	public int compare(Issue o1, Issue o2) {
		if ( this.convertDateToUnix(o1.getCreated()) <= this.convertDateToUnix(o2.getCreated()))
			return 1;
		else if ( this.convertDateToUnix(o1.getCreated()) >= this.convertDateToUnix(o2.getCreated()))
			return -1;
		else
			return 0;
	}
	
	// Convert Date to Unix Time
	private long convertDateToUnix(String dateString) {
		try {
			if (dateString != null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
				Date date = dateFormat.parse(dateString);
				return (long) date.getTime()/1000;
			}
		} catch (Exception e) {
			System.out.println("Err -> " + e.getMessage());
		}
		return 0;
	}
	
} // End of class
