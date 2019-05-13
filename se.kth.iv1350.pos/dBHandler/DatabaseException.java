package dBHandler;

/**
 * DatabaseException is thrown when an 
 * unidentified error occurs while the system
 * tries to connect to the database
 * @author Abyel Tesfay
 */
public class DatabaseException extends Exception {
	/**
	 * Creates an DatabaseException that informs the user
	 * that an error has occurred during attempted connections
	 * with the database
	 * @param message an string containing the explanation
	 */
	public DatabaseException(String message) {
		super(message);
	}
}