package model;

/**
 * Printer prints recipes, the proof of
 * a completed purchase
 *	@author Abyel Tesfay
 */

public class Printer {

	/**
	 * printReciepe prints out the completed sale by returnning
	 * @param completedSale
	 */
	public static Receipe printRecipe(SaleDTO completedSale) {
		return new Receipe(completedSale);
	}
}
