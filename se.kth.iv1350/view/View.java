package view;

import java.util.*;
import java.util.Locale;
import java.util.Scanner;

import controller.Controller;
import dBHandler.ItemDTO;
import model.Sale;

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
	 * starts a new sale 
	 */
	
	public void startNewSale() {
		for(;;)
		{ 
			System.out.println("Select the desired operation \n"
					+ "1. StartNewSale: Starts a new sale \n"
					+ "2. Exit: Turn off the system \n"
					+ "3. SaleLog: Checks history of all sales \n"
					+ "4. InvLog: Checks all items currently registered in the system ");
			
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
						
						contr.addItem(itemID, Integer.valueOf(quantityString));
						
						System.out.printf("\nCurrent sale: \n");
						for( int i = 0; i < contr.getSale().getSoldItems().size()  ;i++)  {
							System.out.printf( contr.getSale().getSoldItems().get(i).getName() + ", " 
									+ contr.getSale().getSoldItems().get(i).getQuantity() + ", " 
									+ contr.getSale().getRunningTotal() + " \n" );
						System.out.printf("Running total: " + contr.getSale().getRunningTotal() +
								", VATRate: " + contr.getSale().getRunningVAT());

						}
					}
				}
				
				case "2.":
					break;
			
				//gör en knapp så att man kan backa (t.ex break vid en for-loop)
				case "3.":
					
				case "4.":
					
				default:
					System.out.println("Invalid command");
			}
			
		}
	}
}

//if ( itemID.equals("EndSale") ) //private metod
//	break;