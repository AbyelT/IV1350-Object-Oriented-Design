package dBHandler;

import java.util.ArrayList;

/**
 * ExternalInventory contains information of
 * every available item and its quantity in the POS
 * @author Abyel Tesfay
 */

public class ExternalInventory {
	ArrayList<ItemDTO> AvailableItems = new ArrayList<ItemDTO>();
	private static ItemDTO FoundItem;
	
	/*
	 * creates an ExternalInventory instance
	 */
	public ExternalInventory() {
		AvailableItems.add( new ItemDTO("Juice", "111" , 4, 22.90, 12.5) );
		AvailableItems.add( new ItemDTO("Bread", "222",9, 3.90, 6.25) );
		AvailableItems.add( new ItemDTO("chocolate", "333" ,5, 19.90, 12.5) );
		AvailableItems.add( new ItemDTO("soap", "444", 3, 24.90, 25) );
	}

	/**
	 * Checks if the given itemID is valid, if so 
	 * then if there is enough items
	 * @param ItemID The given item identifier
	 * @param quantity The amount currently requested
	 * @return An item with matching ID and enough quantity
	 * @throws NoItemFoundException An exception if there is not matching ID
	 */
	public ItemDTO checkItemID(String ItemID, int quantity) throws NoItemFoundException {
		FoundItem = null;

		for (ItemDTO CurrentItem : AvailableItems) {
			if(ItemID.equals(CurrentItem.getItemID() ) && quantity <= CurrentItem.getQuantity() )  //private method here
				FoundItem = CurrentItem;
		}
		
		if(FoundItem == null)
			throw new NoItemFoundException();
		
		return FoundItem;
	}
	
}

