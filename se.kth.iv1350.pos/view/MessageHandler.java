package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * MessageHandler shows messages to the user
 * depending on the type of exceptions that occurr
 * @author Abyel Tesfay
 */
public class MessageHandler {

	/**
	 * showErrorMsg creates a StringBuilder that is gradually
	 * Built into an message showing the date, time, and an
	 * explanation of the occurring error
	 * @param msg the message containing an explanation of the error
	 */
	public void ShowExceptionMessage(String msg) { 
		String message = null;
		message = createTime(); 
		message += ", ERROR: " + msg; 
	 	System.out.println(message + "\n"); 
	 } 
	 
	 private String createTime() { 
		LocalDateTime now = LocalDateTime.now(); 
	    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		return now.format(formatter);
	}
}
