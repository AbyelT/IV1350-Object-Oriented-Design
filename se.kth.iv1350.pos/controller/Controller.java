package controller;

import dBHandler.DatabaseException;
import dBHandler.ExternalAccounting;
import dBHandler.ExternalInventory;
import dBHandler.InvalidItemException;
import dBHandler.ItemDTO;
import dBHandler.LogHandler;
import dBHandler.TotalRevenue;
import model.CashAmountLeftException;
import model.CashRegister;
import model.Receipe;
import model.Sale;
import model.SaleDTO;

/**
 * Controller passes all calls from View with the right
 * methods to Model
 * @author Abyel Tesfay, Daniel Nyeko
 */

public class Controller {
	private CashRegister register;
	private ExternalInventory inventory;
	private ExternalAccounting accounting;
	private LogHandler logger;
	private Sale sale;
	
	/**
	 * creates an Controller instance
	 */
	public Controller(CashRegister cashReg, ExternalInventory eInventory, ExternalAccounting eAccounting, 
			LogHandler logger) {
		this.register = cashReg;
		this.inventory = eInventory;
		this.accounting = eAccounting;
		this.logger = logger;
	}
	
	/**
	 * Starts a new sale by creating
	 * an Sale instance
	 */
	public void startNewSale() {
		this.sale = new Sale();
	}
	
	/**
	 * addItem adds a new item to the ongoing sale
	 * @param ItemID the given itemID by cashier
	 * @param quantity the amount of the same 
	 * item requested
	 * @return the current Sale instance
	 * @throws CannotFetchItemException if no item with the 
	 * Matching itemID is found
	 * @Throws OperationFailedException if an "unidentified" 
	 * error occurs during an operation
	 */
	public Sale addItem(String ItemID, int quantity) throws Exception  {
		try { ItemDTO itemInFocus;
			itemInFocus = inventory.checkItemID(ItemID, quantity);
			sale.updateSale(itemInFocus);
		}
		catch (InvalidItemException e) {
			throw new CannotFetchItemException( e.getMessage(), e.getCause());
		}
		catch (DatabaseException e) {
			throw new OperationFailedException( e.getCause()); 
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
	 * the amount required if the amount is not enough or the change
	 * if the sale is completed
	 * @param payment the amount cash given
	 * @return the amount of change the user receives
	 * @throws SaleNotPaidException if the ongoing
	 * sale has not been completely paid 
	 */
	public double enterAmountPaid(int payment, SaleDTO completedSale) throws Exception {
		int amountLeft = 0;
		try {
			amountLeft = this.sale.payForSale(payment, completedSale.getTotalPrice() );
			register.increaseAmount(amountLeft);
		}
		catch (CashAmountLeftException e) {
			throw new SaleNotCompleteException(e.getMessage(), e.getAmountLeft(), e.getCause());
		} 
		return amountLeft;
	}
	
	/**
	 * finishedSale record the completed sale in an
	 * external accounting system and returns an Recipe instance
	 * with a string describing the sale
	 * @param the completed sale as an SaleDTO
	 * @return an Recipe instance
	 */
	public Receipe printReciepe (SaleDTO completedSale){
		accounting.recordSale(completedSale);
		return this.sale.printRecipe(completedSale);
	}
	
	/**
	 * LogException sends the given exception to 
	 * an LogHandler instance 
	 * @param e the given exception
	 */
	public void LogException(Exception e) {
		this.logger.logException(e);
	}
	
	/**
	 * addTotalRevenueObserver sends the parameter from 
	 * View to an ExernalAccounting instance.
	 * @param observer an instance of TotalRevenue
	 */
	public void addTotalRevenueObserver(TotalRevenue observer) {
	      accounting.addObserver(observer);
	}
	
	public Sale getSale() {
		return this.sale;
	}
}
