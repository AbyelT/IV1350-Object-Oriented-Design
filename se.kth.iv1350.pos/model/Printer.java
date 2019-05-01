package model;

/**
 * Printer prints recipes, the proof of
 * a completed purchase
 *	
 */

public class Printer {

	/**
	 * printReciepe prints out the completed sale by returnning
	 * @param completedSale
	 */
	public static Reciepe printRecipe(SaleDTO completedSale) {
		return new Reciepe(completedSale);
	}
}
