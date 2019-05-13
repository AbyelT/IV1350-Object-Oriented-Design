package dBHandler;

import java.util.ArrayList;

/**
 * ExternalInventory contains information of
 * every available item and its quantity in the POS
 * @author Abyel Tesfay
 */

public class ExternalInventory {
	private ArrayList<ItemDTO> availableItems = new ArrayList<ItemDTO>();
	
	/*
	 * creates an ExternalInventory instance
	 */
	public ExternalInventory() {
		availableItems.add( new ItemDTO("Juice", "111" , 20, 22.90, 12.5));
		availableItems.add( new ItemDTO("Bread", "222", 47, 3.90, 6.25));
		availableItems.add( new ItemDTO("chocolate", "333" , 22, 19.90, 12.5));
		availableItems.add( new ItemDTO("soap", "444", 35, 24.90, 25));
	}

	/**
	 * Checks if the given itemID is valid and if there is enough items
	 * if so, the system will fetch that item and update the inventory depending
	 * on the amount requested
	 * @param ItemID The given item identifier
	 * @param quantity The amount currently requested
	 * @return An ItemDTO with matching ID and enough quantity
	 * @throws InvalidItemException if there is no ItemID in the 
	 * inventory that matches the given ItemID 
	 * @throws DatabaseException as an simulation if the connection 
	 * to database would fail
	 */
	public ItemDTO checkItemID(String ItemID, int requestedAmount) throws Exception {
		ItemDTO FoundItem = null;
		
		if(ItemID.equals("000"))
			throw new DatabaseException("ERROR: Connection to database lost");
		
		for (int i = 0; i < availableItems.size(); i++) {
			ItemDTO CurrentItem = availableItems.get(i);
			
			if(itemsAvailable(ItemID, CurrentItem)) {
				FoundItem = new ItemDTO(CurrentItem.getName(), ItemID, requestedAmount, 
						CurrentItem.getPrice() * requestedAmount, CurrentItem.getVATrate());
				updateInventory(ItemID, requestedAmount, CurrentItem, i);
				
			}
		}
		if(FoundItem == null)
			throw new InvalidItemException(ItemID);
		return FoundItem;
	}
	
	private boolean itemsAvailable(String ItemID, ItemDTO CurrentItem) {
		return ItemID.equals(CurrentItem.getItemID());
	}
	
	private void updateInventory(String ItemID, int quantity, ItemDTO CurrentItem ,int index) {
		this.availableItems.set(index, (new ItemDTO(CurrentItem.getName(), ItemID, 
				(CurrentItem.getQuantity() - quantity), CurrentItem.getPrice(), CurrentItem.getVATrate()) ));
	}
}
