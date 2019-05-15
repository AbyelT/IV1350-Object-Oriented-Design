package model;

/**
 * CashRegister keeps track of the 
 * total amount cash in the register
 * received from all payments
 * @author Abyel Tesfay
 *
 */
public class CashRegister {
	private int cashAmount;
	
	/**
	 * Creates an CashRegister instance
	 */
	public CashRegister() {
	}
	
	/**
	 * increaseAmount increases the total amount
	 * cash in the register
	 * @param payment a paymen in cash
	 */
	public void increaseAmount(int payment) {
		this.cashAmount += payment;
	}
}

