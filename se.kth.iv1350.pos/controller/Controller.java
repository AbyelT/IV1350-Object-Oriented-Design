package controller;

import dBHandler.DatabaseException;
import dBHandler.ExternalAccounting;
import dBHandler.ExternalInventory;
import dBHandler.InvalidItemException;
import dBHandler.ItemDTO;
import dBHandler.LogHandler;
import model.CashAmountLeftException;
import model.CashRegister;
import model.Receipe;
import model.Sale;
import model.SaleDTO;
import model.TotalRevenue;
import view.ErrorMessageHandler;
import view.View;
/**
 * Controller passes all calls from View with the right
 * methods to Model
 * @author Abyel Tesfay
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
	 * @throws CannotFetchItemException
	 * @Throws OperationFailedException
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
	 * the amount required if not enough or the change
	 * @param payment the amount cash given
	 * @return the amount of change or requested payment left
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
	 * finishedSale returns an instance Recipe
	 * with an string describing the sale
	 * @param the completed sale as an DTO
	 * @return an instance of Recipe
	 */
	public Receipe printReciepe (SaleDTO completedSale){
		accounting.recordSale(completedSale);
		return this.sale.printRecipe(completedSale);
	}
	
	public void LogException(Exception e) {
		this.logger.logException(e);
	}
	
	public Sale getSale() {
		return this.sale;
	}
	
	public void addTotalRevenueObserver(TotalRevenue instance) {
	      accounting.addObserver(instance);
	}
}
