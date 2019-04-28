package model;

import controller.Controller;

 /**
  * CashRegister contains the current amount of
  * cash inside the register and handles change
  * @author Abyel Tesfay
  *
  */
public class CashRegister {
	private int cashAmount;
	private Controller contr; //behövs den?
	
	/**
	 * creates an CashRegister instance
	 * @param contr the controller instance 
	 */
	public CashRegister(Controller contr) {
		this.cashAmount = 0;
	}

}
