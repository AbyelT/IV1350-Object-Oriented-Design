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
		ItemOfSameType()
		SoldItems.add(soldItem);
	}
	
	private void ItemOfSameType(ItemDTO soldItem) {
		for(int i = 0; i < this.SoldItems.size(); i++) {
			if(this.SoldItems.get(i) == soldItem) {
				this.SoldItems.set(i, (new ItemDTO(soldItem.getName(), soldItem.getItemID(), 
						(soldItem.getQuantity() + 1), soldItem.getPrice(), soldItem.getVATrate()) ));
			}
		}
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
	
}


