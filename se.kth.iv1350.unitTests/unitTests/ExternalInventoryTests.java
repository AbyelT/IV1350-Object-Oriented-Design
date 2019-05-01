package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.Controller;
import dBHandler.ExternalInventory;
import dBHandler.ItemDTO;
import model.Sale;
import org.junit.Assert;
import org.junit.Ignore;

public class ExternalInventoryTests {
	private Controller contr;
	private Sale sale;
	private ItemDTO fetchedItem;
	private ItemDTO CompareItem;
	
	@BeforeEach
	public void setUp() throws Exception {
		String rightIDtest = "111";
		String wrongIDtest = "122";
		CompareItem = new ItemDTO("Juice", "111" , 4, 22.90, 12.5);
		ExternalInventory eInventory = new ExternalInventory();
		fetchedItem = eInventory.checkItemID(rightIDtest, 1);
	}
	
	@Test
	public void fetchesCorrectItem() {
		boolean equals = true;
		boolean	SaleConfirm = fetchedItem.core
		
		Assert.assertEquals("The inventory fetched correct item", equals, SaleConfirm);

	}
	
	@Ignore
	@Test
	public void fetchesWrongItem() {
		boolean equals = false;
		boolean	SaleConfirm = fetchedItem.equals(CompareItem);
		
		Assert.assertEquals("The inventory fetched wrong item", equals, SaleConfirm);

	}
}


