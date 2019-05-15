package controller;

/**
 * CannotFetchItemException is thrown when no item 
 * with the given itemID is found
 */
public class CannotFetchItemException extends Exception{
	
	/**
	 * Creates an CannotFetchItemException instance
	 * that contains a message and the case of the exception
	 * @param msg a message explaining the case
	 * @param e the cause of this exception
	 */
	public CannotFetchItemException(String msg, Throwable e) {
		super(msg, e);
	}
}
