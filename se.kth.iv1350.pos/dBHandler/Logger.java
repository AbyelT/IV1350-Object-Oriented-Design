package dBHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Logger handles all incoming exceptions
 * @author Abyel Tesfay
 */
public class Logger {
	 private static final String SaleLog = "SaleError-log.txt";
	 private PrintWriter logFile;
	 
	 /**
	  * Creates an Logger instance 
	  * @throws IOException
	  */
	 public Logger() throws IOException {
		  this.logFile = new PrintWriter( 
				  new FileWriter(SaleLog), true);
		}
	 
	 /**
	  * Creates an string containing the case
	  * of exception and then prints it out in
	  * a text file 
	  * @param exception
	  */
	 public void logException(Exception cause) { 
		 String logMessage = null;
		 logMessage = "Exception warning: ";
		 logMessage += cause.getMessage(); 
		 logFile.println(logMessage); 
		 cause.printStackTrace(this.logFile); 
	} 
}
