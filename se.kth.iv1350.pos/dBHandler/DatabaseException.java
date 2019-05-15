package dBHandler;

/**
 * DatabaseException is thrown when an 
 * unidentified error occurs while the system
 * tries to connect to the database
 * @author Abyel Tesfay, Daniel Nyeko
 */
public class DatabaseException extends RuntimeException {
	/**
	 * Creates an DatabaseException 
	 */
	public DatabaseException() {
		super();
	}
}