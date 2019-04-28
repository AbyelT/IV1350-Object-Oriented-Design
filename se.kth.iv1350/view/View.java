package view;

import java.util.*;
import java.util.Locale;
import java.util.Scanner;

import controller.Controller;

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
				case "StartNewSale": {
					contr.StartNewSale();
					
					//Additem
					for (;;)
					{
						//lägg till diffractor, 
						System.out.println("\nEnter the itemID and quantity (ID, quantity)");
						
						String itemID = in.nextLine();
						if ( itemID.equals("EndSale") ) //private metod
							break;
						
						int quantity = in.nextInt();
						contr.addItem(itemID, quantity);
						
						System.out.printf("Current sale: \n"
								+ contr.getSale().getRunningTotal() + ", " + contr.getSale().getSoldItems().get(0).getName());
					}
				}
				
				case "Exit":
					break;
			
				//gör en knapp så att man kan backa (t.ex break vid en for-loop)
				case "SaleLog":
					
				case "InvLog":
					
				default:
					System.out.println("Invalid command");
			}
			
		}
	}
}
