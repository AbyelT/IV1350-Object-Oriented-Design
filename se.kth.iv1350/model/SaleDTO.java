package model;

import dBHandler.ItemDTO;

/**
 * SaleDTO carries information about the ongoing sale like
 * the total price, the sold items etc.
 */

public class SaleDTO {
	private final int TotalPrice;
	private final ItemDTO[] SoldItems;
	private final int TotalVAT;
	private final double DiscountRate;
	
	/**
	 * Creates an DTO of the ongoing sale to be used by other packages
	 * @param sale An instance of the Sale-class, contains info about the current sale
	 */
	
	public SaleDTO(Sale sale) {
		this.TotalPrice = sale.getRunningPrice() ;
		this.SoldItems = sale.getSoldItems();
		this.TotalVAT = sale.getTotalVAT();
		this.DiscountRate = sale.DiscountRate();
	}

	public int getTotalPrice() {
		return TotalPrice;
	}

	public ItemDTO[] getSoldItems() {
		return SoldItems;
	}

	public int getTotalVAT() {
		return TotalVAT;
	}

	public double getDiscountRate() {
		return DiscountRate;
	}

	public SaleDTO getSaleDTO() {
		return this;
	}
	
	
}
