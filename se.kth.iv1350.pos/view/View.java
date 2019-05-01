package view;

import java.util.*;
import controller.Controller;
import dBHandler.ItemDTO;
import model.Sale;
import model.SaleDTO;

/**
 * This class acts as an placeholder for the entire 
 * user interface, the terminal represents the interface
 * between the cashier and the system
 * @author Abyel Tesfay
 */

public class View {
	private Controller contr;
	private Scanner in;
	
	/**
	 * creates an View instance
	 * @param contr the controller instance
	 */
	public View(Controller contr) {
		this.contr = contr;
		this.in = new Scanner (System.in);
		in.useLocale (Locale.US);
	}
	
	/**
	 * starts a new sale, the user can choose to
	 * start new sales or turn off the program */
	public void programStart() {
		for(;;)
		{ 
			System.out.println("Select the desired operation \n"
					+ "1. StartNewSale: Starts a new sale \n"
					+ "2. Exit: Turn off the system \n");
			
			switch(in.nextLine()) {
				
				case "1.": {
					contr.startNewSale();
					for (;;)
					{
						System.out.println("\nEnter the itemID and quantity (ID, quantity)");
						String itemID = in.nextLine();
						String quantityString = in.nextLine();
						
						if ( itemID.equals("Endsale") || quantityString.equals("Endsale")) //privae method
							break;	
						
						/*Add items to the ongoing sale*/
						try { contr.addItem(itemID, Integer.valueOf(quantityString));
						}
						catch (NumberFormatException e) {
							System.out.println("\nOnly Integers allowed");
						}
						
						/*Print information about the ongoing sale*/
						System.out.printf("\nCurrent sale: \n-----------------------------" );
						printOutInformation(contr.getSale().getSoldItems());
						System.out.printf("\n-----------------------------\nRunning total: " + contr.getSale().getRunningTotal() 
								+ ", VATRate: " + contr.getSale().getRunningVAT() + "\n");	
					}
				}
				
				case "2.": {
					System.out.println("System shutting down...");
					System.exit(0);
				}
					break;
					
				default:
					System.out.println("Invalid command");
			}
			
			/*Prints out information about the sale one final time */
			SaleDTO finishedSale = contr.indicateAllItemsRegistered();
			System.out.printf("\nTotal Price: " + finishedSale.getTotalPrice() + "\n-----------------------------");
			printOutInformation(finishedSale.getSaleDTO().getSoldItems());
			
			System.out.printf("\n-----------------------------\nPay the required amount cash\n");
			/*A loop that forces you to pay for the goods before the sale is completed*/
			for(;;) {
				double change = contr.enterAmountPaid(Integer.valueOf(in.nextLine()), finishedSale);
				if(change > 0) {
					System.out.printf("\nChange: " + change + "\n");
					break;
				}		
			}
			
			/*Print out the recipe recieved from the Printer-instance*/
			String reciepe = contr.printReciepe(finishedSale).getReciepe();
			System.out.println(reciepe);	
			break;
		} 
	}
	
	/*Prints information about the sale*/
	private void printOutInformation(ArrayList<ItemDTO> itemList) {
		for(ItemDTO item : contr.getSale().getSoldItems()) {
			System.out.printf("\n" + item.getName() + ", " + item.getQuantity() 
				+ ", " + item.getPrice() + ", " + item.getVATrate() );
		}
	}
}
