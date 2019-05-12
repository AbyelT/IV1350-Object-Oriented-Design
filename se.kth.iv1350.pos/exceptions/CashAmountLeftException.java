package exceptions;
/*
 * AmountLeftException is thrown when the total 
 * price of the sale has not been paid
 * @author Abyel Tesfay
 */
public class CashAmountLeftException extends NullPointerException
{
	/**
	 * creates an AmountLeftException exception that shows
	 * the amount left before the sale is completed
	 * @param amountLeft
	 */
	public CashAmountLeftException(int amountLeft)
	{
		super("Amount left before transaction is completed: "
				+ amountLeft);
	}
}
