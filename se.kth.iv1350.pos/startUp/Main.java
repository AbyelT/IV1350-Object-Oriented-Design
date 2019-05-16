package startUp; 

import java.io.IOException;

import controller.Controller;
import dBHandler.ExternalAccounting;
import dBHandler.ExternalInventory;
import dBHandler.LogHandler;
import model.CashRegister;
import view.MessageHandler;
import view.View;
/**
 * Performs the startup of the entire system, 
 * ErrorMessageHandler and LogHandler included
 * @author Abyel Tesfay
 */
public class Main {
	/**
	 * Starts the system
	 * @param args the system takes no parameters
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		CashRegister cashReg = new CashRegister();
		ExternalInventory eInventory = new ExternalInventory();
		ExternalAccounting eAccounting = new ExternalAccounting();
		MessageHandler msgHandler = new MessageHandler();
		LogHandler logger = new LogHandler();
		
		Controller contr = new Controller(cashReg, eInventory, eAccounting, logger);
		View view = new View(contr, msgHandler);
		view.programStart();
	}
}