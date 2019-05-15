package model;
/*
 * CashAmountLeftException is thrown when the total
 * amount cash paid during an ongoing sale is less
 * then the total price of that sale
 * @author Abyel Tesfay
 */
public class CashAmountLeftException extends NullPointerException {
	private int amountLeft;
	/**
	 * creates an AmountLeftException exception that shows
	 * the amount left before the sale is completed
	 * @param amountLeft the amount left for the sale
	 */
	public CashAmountLeftException(int amountLeft)
	{
		super();
		this.amountLeft = amountLeft;
	}
	
	public int getAmountLeft() {
		return this.amountLeft;
	}
}
