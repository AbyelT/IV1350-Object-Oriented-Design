package model;

/**
 * TotalPayment indicates the amount cash that is paid,
 * the amount required for an complete transaction and
 * the change that is returned if given cash is more
 * than the total Required
 * @author Abyel Tesfay
 *
 */
public class TotalPayment {
	private int totalCash;
	private int totalRequired;
	private int change;
	
	/**
	 * Creates an TotalPayment instance
	 * @param totalRequired the amount cash required 
	 */
	public TotalPayment(int totalRequired) {
		this.totalRequired = totalRequired;
		}

	/**
	 * payForSale adds the amount cash being paid to the
	 * total amount cash in the instance and subtracts
	 * the total cash required for an complete transaction
	 * @param cash the cash currently being paid
	 */
	public void addPayment(int cash) {
		this.totalCash += cash;
		this.totalRequired -= cash;
		this.change = totalRequired;
	}
	
	public int getCash() {
		return this.totalCash;
	}
	
	public int getTotalRequired() {
		return this.totalRequired;
	}
	
	public int getChange() {
		return this.change;
	}
}
