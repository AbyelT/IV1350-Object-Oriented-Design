package view;

import java.util.*;
import java.util.Locale;
import java.util.Scanner;

import controller.Controller;
import dBHandler.ItemDTO;
import model.Sale;
import model.SaleDTO;

/**
 * This class acts as an placeholder for the entire 
 * user interface
 * 
 * the terminal represents the interface between the
 * cashier and the system
 */

public class View {
	private Controller contr;
	private int itemID;
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
	 * start new sales or turn off the program
	 * 
	 */
	
	public void startNewSale() {
		for(;;)
		{ 
			System.out.println("Select the desired operation \n"
					+ "1. StartNewSale: Starts a new sale \n"
					+ "2. Exit: Turn off the system \n");
			
			String Command = in.nextLine();

			switch(Command) {
				
				//StartNewSale
				case "1.": {
					contr.StartNewSale();
					
					//Additem
					for (;;)
					{
						System.out.println("\nEnter the itemID and quantity (ID, quantity)");
					
						String itemID = in.nextLine();
						String quantityString = in.nextLine();
				
						if ( itemID.equals("Endsale") ) 
							break;	
						try {
							contr.addItem(itemID, Integer.valueOf(quantityString));
						}
						catch (NumberFormatException e) {
							System.out.println("\nOnly Integers allowed");
						}
						Sale ongoingSale = contr.getSale();
						System.out.printf("\nCurrent sale: \n-----------------------------" );
						for( ItemDTO CurrentItem : ongoingSale.getSoldItems() )  {
							System.out.printf("\n" + CurrentItem.getName() + ", " + CurrentItem.getQuantity() + ", "
								+ CurrentItem.getPrice() + ", " + CurrentItem.getVATrate() );
							
						}
						System.out.printf("\n-----------------------------\nRunning total: " + contr.getSale().getRunningTotal() 
								+ ", VATRate: " + contr.getSale().getRunningVAT() + "\n");
					}
				}
				
				case "2.":
					break;
					
				default:
					System.out.println("Invalid command");
			}
			
			SaleDTO finishedSale = contr.indicateAllItemsRegistered();
			System.out.printf("\nTotal Price: " + finishedSale.getTotalPrice() + "\n-----------------------------");
			for(ItemDTO CurrentItem : finishedSale.getSoldItems()) {
				System.out.printf("\n" + CurrentItem.getName() + ", " + CurrentItem.getQuantity() + ", "
						+ CurrentItem.getPrice() + ", " + CurrentItem.getVATrate() );
			}
			
			System.out.printf("\n-----------------------------" 
					+ "\nPay the required amount cash\n");
			for(;;) {
				double change = contr.enterAmountPaid(Integer.valueOf(in.nextLine()), finishedSale);
				if(change >= 0) {
					System.out.printf("\nChange: " + change + "\n");
					break;
				}
					
			}
			
			String reciepe = contr.printReciepe(finishedSale).getReciepe();
			System.out.println(reciepe);	
			break;
		} 
		
	}
}

//int i = 0; i < contr.getSale().getSoldItems().size()  ;i++
//contr.getSale().getSoldItems().get(i).getName() + ", " 
//+ contr.getSale().getSoldItems().get(i).getQuantity() + " \n" )


//private metod
//	break;