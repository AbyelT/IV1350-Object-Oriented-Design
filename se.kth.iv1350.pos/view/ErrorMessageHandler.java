package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * ErrorMessageHandler shows error messages to the user
 * depending on the type of error
 * @author Abyel Tesfay
 */
public class ErrorMessageHandler {

	/**
	 * showErrorMsg creates a StringBuilder that is gradually
	 * Built into an message showing the date, time, and an
	 * explanation of the occurring error
	 * @param msg the message containing an explanation of the error
	 */
	public void showErrorMsg(String msg) { 
		StringBuilder errorMsgBuilder = new StringBuilder();
		errorMsgBuilder.append(createTime()); 
		errorMsgBuilder.append(", ERROR: "); 
	 	errorMsgBuilder.append(msg); 
	 	System.out.println(errorMsgBuilder); 
	 } 
	 
	 private String createTime() { 
		LocalDateTime now = LocalDateTime.now(); 
	    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		return now.format(formatter);
	}
}
