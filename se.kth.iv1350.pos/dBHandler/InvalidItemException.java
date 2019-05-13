package dBHandler;

/**
 * ExternalInventoryException is thrown when an
 * business logic error occurs during operations
 * Within an ExternalInventory instance 
 * @author Abyel Tesfay
 *
 */
public class InvalidItemException extends Exception {
	//private String IDnotFound;
	
	/**
	 * Creates an ExternalInventoryException instance explaining that
	 * the given itemID was not found in the database
	 * @param WrongItemID the given ItemID
	 */
	public InvalidItemException(String itemID) {
		super("No matching itemID was found in inventory database, Given ID: " + itemID);
		//this.IDnotFound = itemID;
	}

	/**
	 * returns the id not found
	 */
	/*public String getIDnotFound() {
		return this.IDnotFound;
	}
	*/
	
	/*
	 * public ExternalInventoryException(String ItemName, int requestedAmount, int quantity) {
		super("Not enough quantity of the requested item \""
				+ ItemName + "\", Amount left: " + quantity);
	}
	 */
}
