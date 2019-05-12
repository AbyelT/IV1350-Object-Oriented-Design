package controller;

public class OperationFailedException extends Exception{

	public OperationFailedException(String msg, Throwable e) {
		super(msg, e);
	}
}
