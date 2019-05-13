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
import view.ErrorMessageHandler;
/**
 * Controller passes all calls from View with the right
 * methods to Model
 * @author Abyel Tesfay
 */

public class Controller {
	private CashRegister register;
	private ExternalInventory inventory;
	private ExternalAccounting accounting;
	private ErrorMessageHandler msgHandler;
	private LogHandler logger;
	private Sale sale;
	
	/**
	 * creates an Controller instance
	 */
	public Controller(CashRegister cashReg, ExternalInventory eInventory, ExternalAccounting eAccounting, 
			ErrorMessageHandler msgHandler, LogHandler logger) {
		this.register = cashReg;
		this.inventory = eInventory;
		this.accounting = eAccounting;
		this.msgHandler = msgHandler;
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
	 * @return the current Sale-instance
	 * @throws CannotFetchItemException if an InvalidItemException instance is caught
	 * @throws OperationFailedException if an DatabaseException instance is caught
	 */
	public Sale addItem(String ItemID, int quantity) throws CannotFetchItemException, OperationFailedException, Exception  {
		try { ItemDTO itemInFocus;
			itemInFocus = inventory.checkItemID(ItemID, quantity);
			sale.updateSale(itemInFocus);
		}
		catch (InvalidItemException e) {
			handleException(e.getMessage(),e);
			throw new CannotFetchItemException( e.getMessage(), e.getCause());
		}
		catch (DatabaseException e) {
			handleException("An error has occured \nduring connection,"
					+ " try again later", e);
			throw new OperationFailedException( e.getMessage(), e.getCause()); 
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
	 * @throws SaleNotPaidException 
	 */
	public double enterAmountPaid(int payment, SaleDTO completedSale) throws SaleNotPaidException, Exception {
		int amountLeft = 0;
		try {
			amountLeft = this.sale.payForSale(payment, completedSale.getTotalPrice() );
			register.increaseAmount(amountLeft);
			accounting.recordSale(completedSale);
		}
		catch (CashAmountLeftException e) {
			handleException("Please pay the total price, amount left: "
					+ amountLeft, e);
			throw new SaleNotPaidException();
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
		return this.sale.printRecipe(completedSale);
	}
	
	public Sale getSale() {
		return this.sale;
	}
	
	private void handleException(String uiMsg, Exception exc) { 
		msgHandler.showErrorMsg(uiMsg); 
		logger.logException(exc); 
	}
}
