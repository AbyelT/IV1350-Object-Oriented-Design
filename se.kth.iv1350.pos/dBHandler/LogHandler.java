package dBHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * LogHandler is responsible for the log
 * @author Leif Lindbäck
 */
public class LogHandler {
	 private static final String LOG_FILE_NAME = "SaleError-log.txt";
	 private PrintWriter logFile;
	 
	 /**
	  * Creates an instance of LogHandler
	  * @throws IOException
	  */
	 public LogHandler() throws IOException {
		  this.logFile = new PrintWriter( 
				  new FileWriter(LOG_FILE_NAME), true);
		}
	 
	 /**
	  * Updates the PrintWriter with the case of exception
	  * and then prints it out
	  * @param exception
	  */
	 public void logException(Exception exception) { 
		
		 Logger.log
	} 
	
	private String createTime() { 
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM); 
		return now.format(formatter); 
	}
	
	/*
	 StringBuilder logMsgBuilder = new StringBuilder(); 
	 logMsgBuilder.append(createTime()); 
	 logMsgBuilder.append(", Exception was thrown: "); 
	 logMsgBuilder.append(exception.getMessage()); 
	 logFile.println(logMsgBuilder); 
	 exception.printStackTrace(this.logFile); 
	 */
}
