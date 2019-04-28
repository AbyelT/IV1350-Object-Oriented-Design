package model;

import dBHandler.ItemDTO;
import java.util.ArrayList;

/**
 * Sale is an instance of the ongoing sale
 *
 */
public class Sale {
	private int RunningTotal;
	private int RunningVAT;
	private ArrayList<ItemDTO> SoldItems;
	private double VATrate;
	
	/**
	 * Creates an instance with a new array
	 */
	
	public Sale() {
		SoldItems = new ArrayList<ItemDTO>();
	}
	
	public void updateSale(ItemDTO soldItem) {
		SoldItems.add(soldItem);
	}

	public int getRunningTotal() {
		return this.RunningTotal;
	}
	
	public int getRunningVAT() {
		return this.RunningVAT;
	}
	
	public ArrayList<ItemDTO> getSoldItems() {
		return this.SoldItems;
	}
	
	public double VATrate() {
		return this.VATrate;
	}
	
	public void UpdateSale(ItemDTO SoldItem) {
		SoldItems.add(SoldItem);
	}
}


