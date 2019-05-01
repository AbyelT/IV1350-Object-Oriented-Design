package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.Controller;
import dBHandler.ExternalInventory;
import dBHandler.ItemDTO;
import dBHandler.NoItemFoundException;
import dBHandler.NotEnoughItemsException;
import model.Sale;
import org.junit.Assert;
import org.junit.Ignore;

public class ExternalInventoryTests {
	private Controller contr;
	private ExternalInventory invTest;
	private ItemDTO fetchedItem;
	private ItemDTO CompareItem;
	
	@BeforeEach
	public void setUp() throws Exception {
		CompareItem = new ItemDTO("Juice", "111" , 4, 22.90, 12.5);
		invTest = new ExternalInventory();
	}
	
	@Test
	/*Asserts that the correct item is fetched
	 * with the given, correct itemID
	 */
	public void fetchesCorrectItem() {
		String rightIDtest = "111";
		try {
			fetchedItem = invTest.checkItemID(rightIDtest, 1);
		} 
		catch (Exception e) {
			
		}
		boolean compare = fetchedItem.getItemID().equals(CompareItem.getItemID());
		Assert.assertTrue("The inventory fetched correct item", compare);
	}
	
	@Test
	/*Asserts that an exception is created when wrong 
	 * itemID is entered*/
	public void fetchesWrongItem() {
		String wrongIDtest = "122";
		try {
			fetchedItem = invTest.checkItemID(wrongIDtest, 1);
		} 
		catch (NoItemFoundException e) {
			Assert.assertThat("The inventory found no item", 
					e, CoreMatchers.isA(NoItemFoundException.class)); 
		}
		catch(NotEnoughItemsException e) {
		} 
		catch (Exception e) {
			//This exception will never occur
		}
	}
	
	/*Asserts that an exception is created if 
	 * there is not enough items in inventory*/
	@Test
	public void NotEnoughItemsInInv() {
		String rightIDtest = "111";
		try {
				fetchedItem = invTest.checkItemID(rightIDtest, 999);
		} 
		catch (NoItemFoundException e) {
		}
		catch(NotEnoughItemsException e) {
			Assert.assertThat("Not enough items in the inventory", 
					e, CoreMatchers.isA(NotEnoughItemsException.class)); 
		} 
		catch (Exception e) {
			//This exception will never occur
		}
	}
}


