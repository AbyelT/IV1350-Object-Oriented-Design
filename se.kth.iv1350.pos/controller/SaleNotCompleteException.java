package controller;

/**
 * SaleNotCompleteException is thrown when the 
 * ongoing sale has not been fully paid
 * @author Abyel Tesfay
 */
public class SaleNotCompleteException extends Exception {
	
	/**
	 * Creates an SaleNotCompleteException instance 
	 * that indicates the requested amount before 
	 * the sale is complete
	 * @param msg a message 
	 * @param amountLeft the amount left for the sale
	 * @param e the cause of this exception
	 */
	public SaleNotCompleteException(String msg, int amountLeft, Throwable e) {
		super("Please pay the total price,\namount left: "
				+ amountLeft, e);
	}
}
