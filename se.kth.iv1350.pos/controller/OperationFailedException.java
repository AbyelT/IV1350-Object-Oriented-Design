package controller;

/**
 * OperationFailedException is thrown when 
 * an "unspecified error" has occurred 
 * @author Abyel Tesfay, Daniel Nyeko
 */
public class OperationFailedException extends Exception{

	/**
	 * Creates an OperationFailedException instance that
	 * contains a message that explains the exception and it's
	 * cause
	 * @param e the cause of this exception
	 */
	public OperationFailedException(Throwable e) {
		super("An error has occurred during \n"
				+ "the operation, try again later", e);
	}
}
