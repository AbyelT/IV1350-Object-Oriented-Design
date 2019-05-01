package startUp; 

import controller.Controller;
import dBHandler.DiscountRules;
import dBHandler.ExternalAccounting;
import dBHandler.ExternalInventory;
import model.CashRegister;
import view.View;
/**
 * Performs the startup of the entire system, 
 * printer and database handlers
 * @author Abyel Tesfay
 */
public class Main {
	/**
	 * Starts the system
	 * @param args the system takes no parameters
	 */
	public static void main(String[] args) {
		CashRegister cashReg = new CashRegister();
		ExternalInventory eInventory = new ExternalInventory();
		ExternalAccounting eAccounting = new ExternalAccounting();
		Controller contr = new Controller(cashReg, eInventory, eAccounting);
		
		View view = new View(contr);
		view.programStart();
	}
}