package model;

import java.util.ArrayList;

import dBHandler.ItemDTO;

/**
 * SaleDTO carries information about the ongoing sale like
 * the total price, the sold items etc.
 * @author Abyel Tesfay
 */

public class SaleDTO {
	private final int TotalPrice;
	private final ArrayList<ItemDTO> SoldItems;
	private final double TotalVAT;
	//private final double DiscountRate;
	
	/**
	 * Creates an DTO of the ongoing sale to be used by other packages
	 * @param sale An instance of the Sale-class, contains info about the current sale
	 */
	
	public SaleDTO(Sale sale) {
		this.TotalPrice = sale.getRunningTotal() ;
		this.SoldItems = sale.getSoldItems();
		this.TotalVAT = sale.getVATrate();
	}

	public int getTotalPrice() {
		return TotalPrice;
	}

	public ArrayList<ItemDTO> getSoldItems() {
		return SoldItems;
	}

	public double getTotalVAT() {
		return TotalVAT;
	}

	public SaleDTO getSaleDTO() {
		return this;
	}
}