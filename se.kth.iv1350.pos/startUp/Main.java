package startUp; 

import java.io.IOException;

import controller.Controller;
import dBHandler.ExternalAccounting;
import dBHandler.ExternalInventory;
import dBHandler.LogHandler;
import model.CashRegister;
import view.ErrorMessageHandler;
import view.TotalRevenueView;
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
	 * @throws IOException if the loghandler does not work as intended
	 */
	public static void main(String[] args) throws IOException {
		CashRegister cashReg = new CashRegister();
		ExternalInventory eInventory = new ExternalInventory();
		ExternalAccounting eAccounting = new ExternalAccounting();
		ErrorMessageHandler msgHandler = new ErrorMessageHandler();
		LogHandler logger = new LogHandler();
		
		
		Controller contr = new Controller(cashReg, eInventory, eAccounting, logger);
		View view = new View(contr, msgHandler);
		TotalRevenueView newView = new TotalRevenueView();
		contr.addTotalRevenueObserver(newView);
		view.programStart();
	}
}