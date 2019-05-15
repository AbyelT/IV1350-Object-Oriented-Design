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
	 * printReciepe prints out the completed sale by returnning
	 * @param completedSale
	 */
	public static Receipe printRecipe(SaleDTO completedSale) {
		return new Receipe(completedSale);
	}
	
	public void addObserver(TotalRevenue observer) {
        observers.add(observer);
    } 
}
