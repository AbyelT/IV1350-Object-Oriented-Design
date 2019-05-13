package model;
/*
 * AmountLeftException is thrown when the total 
 * price of the sale has not been paid
 * @author Abyel Tesfay
 */
public class CashAmountLeftException extends NullPointerException {
	private int amountLeft;
	/**
	 * creates an AmountLeftException exception that shows
	 * the amount left before the sale is completed
	 * @param amountLeft
	 */
	public CashAmountLeftException(int amountLeft)
	{
		super("The total cash paid is less than the amount required ");
		this.amountLeft = amountLeft;
	}
	
	public int getAmountLeft() {
		return this.amountLeft;
	}
}
