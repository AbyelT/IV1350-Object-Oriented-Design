package controller;

public class OperationFailedException extends Exception{

	public OperationFailedException(Throwable e) {
		super("An error has occurred during \n"
				+ "the operation, try again later", e);
	}
}
