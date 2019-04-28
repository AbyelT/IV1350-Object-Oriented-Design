package startUp; 

import controller.Controller;
import view.View;
/**
 * Performs the startup of the entire system, 
 * printer and database handlers
 */

public class Main {

	/**
	 * Starts the system
	 * @param args the system takes no parameters
	 */
	public static void main(String[] args) {
		Controller contr = new Controller();
		View view = new View(contr);
		view.startNewSale();
	}
}
