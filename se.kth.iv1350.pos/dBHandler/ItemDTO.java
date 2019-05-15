package dBHandler;

/**
 * ItemDTO contains information about an item, for example name,
 * quantity, price and VAT rate.
 * @author Abyel Tesfay
 */

public final class ItemDTO {
	private final String Name;
	private final String ItemID;
	private final int Quantity;
	private final double Price;
	private final double VATrate;
	
	/**
	 * Creates a new instance of an item
	 * @param Name The name of the item
	 * @param Quantity The amount of same item bought
	 * @param Price The cost for the item
	 * @param VATrate The VAT rate of the item
	 */
	public ItemDTO(String Name,String ItemID ,int Quantity, double Price, double VATrate) {
		this.Name = Name;
		this.Quantity = Quantity;
		this.Price = Price;
		this.VATrate = VATrate;
		this.ItemID = ItemID;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public int getQuantity() {
		return this.Quantity;
	}
	
	public double getPrice() {
		return this.Price;
	}
	
	public double getVATrate() {
		return this.VATrate;
	}
	
	public String getItemID() {
		return this.ItemID;
	}
	
	
	
}
