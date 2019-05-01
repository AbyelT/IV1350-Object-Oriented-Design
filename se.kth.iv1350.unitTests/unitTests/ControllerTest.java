package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import controller.Controller;
import dBHandler.*;
import model.*;
import org.junit.*;
/**
 * ControllerTest Tests all the functions of the 
 * class Controller 
 * @author Abyel Tesfay
 *
 */

class ControllerTest {
	private Controller contr;
	private DiscountRules dRulesTest;
	private CashRegister rTest;
	private ExternalInventory invTest;
	private ExternalAccounting accTest;
	private Sale testSale;
	private SaleDTO testSaleDTO;
	
	@BeforeEach
	public void setUp() throws Exception {
		prepareTests();
		contr = new Controller(dRulesTest, rTest, invTest, accTest); 
		contr.StartNewSale();
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
	public void correctItem() {
		String correctID = "222";
		int amount = 6;
		
		contr.addItem(correctID, amount);
		Assert.assertThat("An item is NOT added to the sale", testSale.getSoldItems().get(0), 
				CoreMatchers.isA(ItemDTO.class)) ;
	}
	
	@Test
	/*Assert that an exception is return if no item
	 * with the given itemID is found*/
	public void wrongItemIDGiven() {
		String wrongID = "413";
		int amount = 10;
		
		try {
			contr.addItem(wrongID, amount);
		}
		catch (NoItemFoundException e) {
			Assert.assertThat("correct item identfier", e, CoreMatchers.isA(NoItemFoundException.class)); 
		}
	}
	
	@Test
	/*Assert that an exception is returned if there
	 * is not enough items requested in the inventory*/
	public void insufficientAmountInInventory() {
		String wrongID = "444";
		int amount = 6;
		try {
			contr.addItem(wrongID, amount);
		}
		catch (NotEnoughItemsException e) {
			Assert.assertThat("Enough items in inventory", e,
					CoreMatchers.isA(NotEnoughItemsException.class)); 
		}
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
	public void changeIsReturned() {
		int payment = 250;
		testSaleDTO = preparePayment();
		Assert.assertTrue("Change is not returned",contr.enterAmountPaid(payment, testSaleDTO) >= 0);
	}
	
	@Test
	/*Asserts that an exception is created if 
	 * not enough payment was received 
	 */
	public void returnAmountRequested() {
		int payment = 100;
		testSaleDTO = preparePayment();
		try {
			contr.enterAmountPaid(payment, testSaleDTO);
		}
		catch (AmountLeftException e) {
			Assert.assertThat("Amount is not left before completed sale", e,
					CoreMatchers.isA(AmountLeftException.class));
		}
	}
	
	@Test
	/**Asserts that an instance of Recipe is created
	 */
	public void recipeIsPrinted() {
		testSaleDTO = preparePayment();
		Assert.assertThat("An instance of Recipe is not created", 
				this.testSale.printRecipe(testSaleDTO), 
				CoreMatchers.isA(Reciepe.class));
	}
	
	@Ignore
	//an early test that i created
	public void saleIsNull() {
		Object object = null;
		boolean equals = false;
		boolean	NullConfirm = testSale.equals(object);
		
		Assert.assertEquals("Amount instance equal to null.", NullConfirm, equals); 
	}
	
	//preparation for all tests
	private void prepareTests() {
		this.dRulesTest = new DiscountRules();
		this.rTest = new CashRegister();
		this.invTest = new ExternalInventory();
		this.accTest  = new ExternalAccounting();
	}
	
	/*n preparation specific for 
	returnAmountRequested and changeIsReturned
	*/
	private SaleDTO preparePayment() {
		contr.addItem("111", 3);
		contr.addItem("333", 2);
		return contr.indicateAllItemsRegistered();	
	}
}
