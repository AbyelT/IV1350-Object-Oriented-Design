package view;

import java.util.*;
import controller.CannotFetchItemException;
import controller.Controller;
import controller.OperationFailedException;
import controller.SaleNotCompleteException;
import dBHandler.ItemDTO;
import model.SaleDTO;

/**
 * This class acts as an placeholder for the entire 
 * user interface, the terminal represents the interface
 * between the cashier and the system
 * @author Abyel Tesfay
 */

public class View {
	private Controller contr;
	private ErrorMessageHandler msgHandler;
	private Scanner in;
	
	/**
	 * creates an View instance
	 * @param contr the controller instance
	 */
	public View(Controller contr, ErrorMessageHandler msgHandler) {
		this.contr = contr;
		this.msgHandler = msgHandler;
		this.in = new Scanner (System.in);
		TotalRevenueView newView = new TotalRevenueView();
		contr.addTotalRevenueObserver(newView);
		in.useLocale (Locale.US);
	}
	
	/**
	 * starts a new sale, the user can choose to
	 * start new sales or turn off the program 
	 * @throws Exception if an unidentified error occurs
	 * all other exceptions are already caught
	 */
	public void programStart() throws Exception {
		for(;;)
		{ 
			System.out.println("\nSelect the desired operation \n"
					+ "1. StartNewSale: Starts a new sale \n"
					+ "2. Exit: Turn off the system \n");
			
			switch(in.nextLine()) {
				
				case "1.": {
					contr.startNewSale();
					ongoingSale();
					break;
				}
				case "2.": {
					System.out.println("System shutting down...");
					System.exit(0);
					break;
				}
				default: {
					System.out.println("Invalid command");
					System.exit(0);
				}
			}
			
			SaleDTO finishedSale = contr.indicateAllItemsRegistered();
			System.out.printf("\nTotal Price: " + finishedSale.getTotalPrice() + "\n-----------------------------");
			printOutInformation(finishedSale.getSaleDTO().getSoldItems());
			System.out.printf("\n-----------------------------\nPay the required amount cash\n");
			
			addPayment(finishedSale);
			String reciepe = contr.printReciepe(finishedSale).getReciepe();
			System.out.println(reciepe);	
		} 
	}

	private void ongoingSale() throws Exception {
		for (;;) {
			int amount = 0;
			String itemID = null;
			String quantityString = null;
			System.out.println("\nEnter the itemID and quantity (ID, quantity)");

			try {
			itemID = in.nextLine();
			if ( itemID.equals("Endsale")) 
				break;	
			quantityString = in.nextLine();
			amount = Integer.valueOf(quantityString);
			contr.addItem(itemID, amount);
			}
			catch(NumberFormatException e) {
				handleException("\nOnly Integers allowed", e);
			}
			catch(CannotFetchItemException e) {
				handleException(e.getMessage(),e);
			} 
			catch (OperationFailedException e) {
				handleException(e.getMessage(),e);						
			}
		
			System.out.printf("\nCurrent sale: \n-----------------------------" );
			printOutInformation(contr.getSale().getSoldItems());
			System.out.printf("\n-----------------------------\nRunning total: " + contr.getSale().getRunningTotal() 
					+ ", VATRate: " + contr.getSale().getRunningVAT() + "\n");	
		}
	}
	
	private void addPayment(SaleDTO finishedSale) throws Exception {
		double change = 0;
		for(;;) {
			try {
			change = contr.enterAmountPaid(Integer.valueOf(in.nextLine()), finishedSale);
			}
			catch (SaleNotCompleteException e) {
				handleException(e.getMessage(), e);
			} 
			catch (NumberFormatException e) {
				handleException("\nOnly Integers allowed", e);
			} 
			if(change >= 0) {
				System.out.printf("\nChange: " + change + "\n");
				break;
			}		
		}
	}
	
	private void printOutInformation(ArrayList<ItemDTO> itemList) {
		for(ItemDTO item : contr.getSale().getSoldItems()) {
			System.out.printf("\n" + item.getName() + ", " + item.getQuantity() 
				+ ", " + item.getPrice() + ", " + item.getVATrate() );
		}
	}
	
	private void handleException(String uiMsg, Exception exc) { 
		msgHandler.showErrorMsg(uiMsg); 
		contr.LogException(exc);
	}
}
