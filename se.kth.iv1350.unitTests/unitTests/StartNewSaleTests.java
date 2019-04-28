package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.Controller;
import model.Sale;
import org.junit.Assert;

;class StartNewSaleTests {
	private Controller contr;
	private Sale testSale;


	@BeforeEach
	public void setUp() throws Exception {
		contr = new Controller(); 
		contr.StartNewSale();
		testSale = contr.GetSale();
	}

	@Test 
	public void equalsObject() {
		Object object = new Object();
		boolean equals = false;
		boolean	SaleConfirm = testSale.equals(object);
		
		Assert.assertEquals("The return in StartNewSale equals an Sale-instance", equals, SaleConfirm);
	}

	@Test
	public void saleIsNull() {
		Object object = null;
		boolean equals = false;
		boolean	NullConfirm = testSale.equals(object);
		
		Assert.assertEquals("Amount instance equal to null.", NullConfirm, equals); 
	}
	
}
