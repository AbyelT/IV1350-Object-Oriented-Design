package model;

import java.util.ArrayList;
import java.util.List;

import dBHandler.TotalRevenue;

/**
 * Printer prints recipes, the proof of
 * a completed purchase
 *	@author Abyel Tesfay
 */

public class Printer {
    private List<TotalRevenue> observers = new ArrayList<>();

	/**
	 * printReciepe prints out a recipe of the completed 
	 * sale by returning an Recipe instance
	 * @param completedSale
	 */
	public static Receipe printRecipe(SaleDTO completedSale) {
		return new Receipe(completedSale);
	}
}
