package controller;

import dBHandler.DiscountRules;
import dBHandler.ExternalAccounting;
import dBHandler.ExternalInventory;
import dBHandler.ItemDTO;
import dBHandler.NoItemFoundException;
import dBHandler.NotEnoughItemsException;
import model.AmountLeftException;
import model.CashRegister;
import model.Reciepe;
import model.Sale;
import model.SaleDTO;
import model.TotalPayment;
/**
 * Controller passes all calls from View with the right
 * methods to Model
 */

public class Controller {
	private CashRegister register;
	private ExternalInventory inventory;
	private ExternalAccounting accounting;
	private Sale sale;
	
	/**
	 * creates an Controller instance
	 */
	public Controller(CashRegister cashReg, ExternalInventory eInventory, ExternalAccounting eAccounting) {
		this.register = cashReg;
		this.inventory = eInventory;
		this.accounting = eAccounting;
	}
	
	/**
	 * Starts a new sale by creating
	 * an Sale instance
	 */
	public void StartNewSale() {
		this.sale = new Sale();
	}
	
	/**
	 * addItem adds a new item to the ongoing sale
	 * @param ItemID the given itemID by cashier
	 * @param quantity 
	 * @return the current Sale-instance
	 * @throws Exception exceptions occur if a wrong itemID is entered or 
	 * not enough items is available
	 */
	public Sale addItem(String ItemID, int quantity) {
		try { ItemDTO itemInFocus;
			itemInFocus = inventory.checkItemID(ItemID, quantity);
			sale.updateSale(itemInFocus);
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
			//This exception will not occur
		}
		return sale;
	}
	
	/**
	 * indicateAllItemsRegistered returns an instance 
	 * of SaleDTO when all items are registered
	 * @return an SaleDTO instance
	 */
	public SaleDTO indicateAllItemsRegistered() {
		return new SaleDTO(this.sale);
	}
	
	/**
	 * enterAmountPaid enters the amount cash paid and returns 
	 * the amount required if not enough or the change
	 * @param payment the amount cash given
	 * @return the amount of change or requested payment left
	 */
	public double enterAmountPaid(int payment, SaleDTO completedSale) {
		int amountLeft = 0;
		try {
			amountLeft = this.sale.payForSale(payment, completedSale.getTotalPrice() );
			register.increaseAmount(amountLeft);
			accounting.recordSale(completedSale);
		}
		catch (AmountLeftException e) {
			System.out.println("\nAmount left: " + this.sale.getPayment().getTotalRequired() );
		}
		return amountLeft;
	}
	
	/**
	 * finishedSale returns an instance Recipe
	 * with an string describing the sale
	 * @param the completed sale as an DTO
	 * @return an instance of Recipe
	 */
	public Reciepe printReciepe (SaleDTO completedSale){
		return this.sale.printRecipe(completedSale);
	}
	
	public Sale getSale() {
		return this.sale;
	}
}
