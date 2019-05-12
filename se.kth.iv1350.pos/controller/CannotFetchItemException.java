package controller;

public class CannotFetchItemException extends Exception{
	
	public CannotFetchItemException(String msg, Throwable e) {
		super(msg, e);
	}
}
