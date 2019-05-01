package dBHandler;

import java.util.ArrayList;
import model.SaleDTO;

public class ExternalAccounting {
	private ArrayList<SaleDTO> SaleLog = new ArrayList<SaleDTO>();

	public ExternalAccounting() {
	}
	
	public void recordSale(SaleDTO completedSale) {
		SaleLog.add(completedSale);
	}
}


