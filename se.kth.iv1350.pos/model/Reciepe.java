package model;

import dBHandler.ItemDTO;

/**
 * Reciepe proves the payment of the 
 * completed sale
 * @author Abyel Tesfay
 *
 */
public class Reciepe {
	private String reciepeText;
	
	/**
	 * creates an instance of Reciepe
	 * @param sale an datatype full of information
	 * from the completed sale
	 */
	public Reciepe(SaleDTO sale) {
		reciepeText = "\nAbyel's POS - Somewhere street 8\n" 
				+ "-----------------------------\n" ;
				printAllItems(sale);
		reciepeText	= "-----------------------------\nTotal price: " 
				+ sale.getTotalPrice() + "\nThanks for shopping!";		
	}
	
	public String getReciepe() {
		return this.reciepeText;
	}
	
	private void printAllItems(SaleDTO sale) {
		for (ItemDTO item : sale.getSoldItems()) {
			this.reciepeText += (item.getName() + ", " + item.getQuantity() + ", " + item.getPrice() + ", " + item.getVATrate()+ "\n");
		}
	}
	
	
}