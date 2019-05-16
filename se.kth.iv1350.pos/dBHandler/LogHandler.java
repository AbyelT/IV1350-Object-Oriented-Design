package dBHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * LogHandler is responsible for the log
 * @author Abyel Tesfay
 */
public class LogHandler {
	private String developerMessage;
	
	 /**
	  * Creates an instance of LogHandler
	  * @throws IOException
	  */
	 public LogHandler() throws IOException {
		}
	 
	 /**
	  * Prints out the full information
	  * about the received exception 
	  * @param exception the given exception
	  */
	 public void logException(Exception exception) { 
		 
		 System.out.println("'Exception has been logged, "
		 		+ "please send this to developer'");
		 developerMessage = createTime();
		 developerMessage += ", Exception was thrown: ";
		 developerMessage += exception.getMessage();
		 System.out.println(this.developerMessage + "\n");
		 exception.printStackTrace();
	} 
	
	private String createTime() { 
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM); 
		return now.format(formatter); 
	}
	
	
}
