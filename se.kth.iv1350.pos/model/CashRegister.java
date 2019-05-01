package model;

public class CashRegister {
	private int cashAmount;
	
	public CashRegister() {
	}
	
	private void increaseAmount(int payment) {
		this.cashAmount += payment;
	}
	
	public int enterPayment(int payment, Sale ongoingSale) {
		increaseAmount(payment);
		
		if( (payment - ongoingSale.getRunningTotal() ) == 0) {
			 return 0;
		}
		
		else if( (payment - ongoingSale.getRunningTotal() > 0)) {
			return (payment - ongoingSale.getRunningTotal() );
		}
		
		return 0;
	}
}

