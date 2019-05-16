package unitTests;

import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import controller.CannotFetchItemException;
import controller.Controller;
import controller.OperationFailedException;
import controller.SaleNotCompleteException;
import dBHandler.*;
import model.*;
import org.junit.*;
/**
 * ControllerTest Tests all the functions of the 
 * class Controller 
 * @author Abyel Tesfay
 */

class ControllerTest {
	private Controller contr;
	private CashRegister rTest;
	private ExternalInventory invTest;
	private ExternalAccounting accTest;
	private Logger lHandler;
	private Sale testSale;
	private SaleDTO testSaleDTO;
	
	@BeforeEach
	public void setUp() throws Exception {
		prepareTests();
		contr = new Controller(rTest, invTest, accTest, lHandler); 
		contr.startNewSale();
		testSale = contr.getSale();
	}

	@Test 
	/*Assert that the object created during StartNewSale is an instance of Sale*/
	public void SaleIsCreated() {
		Assert.assertThat("The operation StartNewSale does not create a Sale-instance", 
				testSale, CoreMatchers.isA(Sale.class));
	}

	@Test
	/*Assert that the Sale-instance created by StartNewSale is not null*/
	public void SaleIsNotNull() {
		Assert.assertNotNull("The operation is null", testSale);
	}
	
	@Test
	/*Asserts that the sale is updated with the correct
	 *ItemDTO when correct ID and amount is entered*/
	public void correctItem() throws CannotFetchItemException, OperationFailedException {
		String correctID = "222";
		int amount = 6;
		
		try {
			contr.addItem(correctID, amount);
		} catch (Exception e) {
			//not going to happen
		}
		Assert.assertThat("An item is NOT added to the sale", testSale.getSoldItems().get(0), 
				CoreMatchers.isA(ItemDTO.class)) ;
	}
	
	@Test
	/*Asserts that an instance of SaleDTO is returned*/
	public void completedSaleIsReturned() {
		Assert.assertThat("This is not an instance of SaleDTO", 
				contr.indicateAllItemsRegistered(), CoreMatchers.isA(SaleDTO.class));
	}
	
	@Test
	/*Asserts that the returned SaleDTO instance is not null*/
	public void saleDTOisNotNull() {
		Assert.assertNotNull("This SaleDTO instance is null", 
				contr.indicateAllItemsRegistered());
	}
	
	@Test
	/*Asserts that change is returned when more 
	 * than enough is paid*/
	public void changeIsReturned() throws CannotFetchItemException, OperationFailedException, SaleNotCompleteException {
		int payment = 250;
		try {
			testSaleDTO = preparePayment();
			Assert.assertTrue("Change is not returned",contr.enterAmountPaid(payment, testSaleDTO) >= 0);
		} 
		catch (Exception e) {
			//not going to happen
		}
	}
	
	@Test
	/**Asserts that an instance of Recipe is created
	 */
	public void recipeIsPrinted() throws CannotFetchItemException, OperationFailedException {
		try {
			testSaleDTO = preparePayment();
		} 
		catch (Exception e) {
			//not going to happen
		}
		Assert.assertThat("An instance of Recipe is not created", 
				this.testSale.printRecipe(testSaleDTO), 
				CoreMatchers.isA(Receipe.class));
	}
	
	@Test
	/*Asserts that an exception is created if 
	 * not enough payment was received 
	 */
	//SEMINAR 4
	public void returnAmountRequested() throws Exception {
		int payment = 100;
		testSaleDTO = preparePayment();
		try {
			this.testSale.payForSale(payment, testSaleDTO.getTotalPrice() );
		}
		catch (CashAmountLeftException e) {
			Assert.assertThat("Amount is not left before completed sale", e,
					CoreMatchers.isA(CashAmountLeftException.class));
		}
	}
	
	
	
	//preparation for all tests
	private void prepareTests() throws IOException {
		this.rTest = new CashRegister();
		this.invTest = new ExternalInventory();
		this.accTest  = new ExternalAccounting();
		this.lHandler = new Logger();
	}
	
	/*a preparation specific for 
	returnAmountRequested and changeIsReturned
	*/
	private SaleDTO preparePayment() throws Exception {
		contr.startNewSale();
		contr.addItem("111", 3);
		contr.addItem("333", 2);
		return contr.indicateAllItemsRegistered();	
	}
}
