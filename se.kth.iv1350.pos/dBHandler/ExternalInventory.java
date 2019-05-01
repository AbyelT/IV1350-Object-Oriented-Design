package dBHandler;

import java.util.ArrayList;

/**
 * ExternalInventory contains information of
 * every available item and its quantity in the POS
 * @author Abyel Tesfay
 */

public class ExternalInventory {
	private ArrayList<ItemDTO> availableItems = new ArrayList<ItemDTO>();
	private static ItemDTO FoundItem;
	
	/*
	 * creates an ExternalInventory instance
	 */
	public ExternalInventory() {
		availableItems.add( new ItemDTO("Juice", "111" , 4, 22.90, 12.5));
		availableItems.add( new ItemDTO("Bread", "222", 9, 3.90, 6.25));
		availableItems.add( new ItemDTO("chocolate", "333" , 5, 19.90, 12.5));
		availableItems.add( new ItemDTO("soap", "444", 3, 24.90, 25));
	}

	/**
	 * Checks if the given itemID is valid and if there is enough items
	 * if so, the system will fetch that item and update the inventory depending
	 * on the amount requested
	 * @param ItemID The given item identifier
	 * @param quantity The amount currently requested
	 * @return An item with matching ID and enough quantity
	 * @throws NoItemFoundException An exception if there is not matching ID
	 */
	public ItemDTO checkItemID(String ItemID, int quantity) throws Exception {
		FoundItem = null;
		for (int i = 0; i < availableItems.size(); i++) {
			ItemDTO CurrentItem = availableItems.get(i);
			
			if(itemsAvailable(ItemID, CurrentItem)) {
				
				if(quantity > CurrentItem.getQuantity()) 
					throw new NotEnoughItemsException();
				FoundItem = new ItemDTO(CurrentItem.getName(), ItemID, quantity, 
						CurrentItem.getPrice() * quantity, CurrentItem.getVATrate());
				updateInventory(ItemID, quantity, CurrentItem, i);
			}
		}
		if(FoundItem == null)
			throw new NoItemFoundException();
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