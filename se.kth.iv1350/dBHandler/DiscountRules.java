package dBHandler;

import controller.Controller;

/**
 * DiscountRules determines if the customer recieves
 * a discount on the ongoing sale based on customerID, 
 * items, quantity and time
 */
public class DiscountRules {
	private Controller contr;
	
	
	public DiscountRules(Controller contr){
		this.contr = contr;
	}
}
