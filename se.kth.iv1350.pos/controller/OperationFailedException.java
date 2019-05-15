package controller;

/**
 * OperationFailedException is thrown when 
 * @author Abyel Tesfay
 *
 */
public class OperationFailedException extends Exception{

	public OperationFailedException(Throwable e) {
		super("An error has occurred during \n"
				+ "the operation, try again later", e);
	}
}
