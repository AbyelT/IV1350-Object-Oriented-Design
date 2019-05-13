package controller;

public class SaleNotCompleteException extends Exception {
	
	public SaleNotCompleteException(String msg, int amountLeft, Throwable e) {
		super("Please pay the total price, amount left: "
				+ amountLeft, e);
	}

	public SaleNotCompleteException() {
		super();
	}
}
