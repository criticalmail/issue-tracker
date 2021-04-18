package au.com.domain.issuetrackerdomain.service;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator 
{
	private DateTimeFormatter dateFormatter;

	public DateValidator(DateTimeFormatter dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	public boolean isValid(String dateStr) {
		try {
			this.dateFormatter.parse(dateStr);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}

} // End of class
