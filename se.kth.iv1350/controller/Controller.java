package controller;

import dBHandler.DiscountRules;
import dBHandler.ExternalAccounting;
import dBHandler.ExternalInventory;
import dBHandler.ItemDTO;
import dBHandler.NoItemFoundException;
import dBHandler.NotEnoughItemsException;
import model.CashRegister;
import model.Sale;

/**
 * Controller passes all calls from View with the right
 * methods to Model
 */

public class Controller {
	private DiscountRules discountRules;
	private CashRegister register;
	private ExternalInventory inventory;
	private ExternalAccounting accounting;
	private Sale sale;
	
	/**
	 * creates an Controller instance
	 */
	public Controller() {
	//	this.discountRules = new DiscountRules();
	//	this.register = new CashRegister();
		this.inventory = new ExternalInventory();
		this.accounting = new ExternalAccounting();
	}
	
	/**
	 * Starts an new sale
	 */
	public void StartNewSale() {
		Sale newSale = new Sale();
		this.sale = newSale;
	}
	
	/**
	 * Adds a new item to the ongoing sale
	 * @param ItemID the given itemID by cashier
	 * @param quantity 
	 * @return the current Sale-instance
	 * @throws Exception Exceptions occur if a wrong itemID is entered or 
	 * not enough items is available
	 */
	public Sale addItem(String ItemID, int quantity) {
		try {
		  ItemDTO CurrentItem;
		  CurrentItem = inventory.checkItemID(ItemID, quantity);
		    sale.updateSale(CurrentItem);
		}
		catch (NoItemFoundException e) {
			System.out.println("invalid ItemID");
			return null;
		}
		catch (NotEnoughItemsException e) {
			System.out.println("Not enough available items");
			return null;
		}
		catch (Exception e) {
			//i dont know why even
		}
		
		return sale;
	}
	
	/**
	 * getSale returns an instance of the class sale
	 * @return the current sale-instance
	 */
	public Sale getSale() {
		return this.sale;
	}

}
