package controller;

public class SaleNotPaidException extends Exception {
	
	public SaleNotPaidException(String msg, Throwable e) {
		super(msg, e);
	}

	public SaleNotPaidException() {
		super();
	}
}
