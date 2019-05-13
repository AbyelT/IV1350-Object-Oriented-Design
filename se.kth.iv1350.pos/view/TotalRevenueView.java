/**
 * 
 */
package view;

import model.TotalRevenue;

/**
 * @author Abyel Tesfay
 *
 */
public class TotalRevenueView implements TotalRevenue{

	public void showTotalRevenue(int totalRevenue) {
		printSale(totalRevenue);
		
	}
	
	private void printSale(int totalRevenue) {
		System.out.println("-----------------------------");
		System.out.println("POS Total revenue: " + totalRevenue);
		System.out.println("-----------------------------");
	}

	
}
