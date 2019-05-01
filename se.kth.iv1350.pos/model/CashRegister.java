package model;

/**
 * CashRegister saves all 
 * @author Abyel Tesfay
 *
 */
public class CashRegister {
	private int cashAmount;
	
	public CashRegister() {
	}
	
	public void increaseAmount(int payment) {
		this.cashAmount += payment;
	}
}

