package dBHandler;

/**
 * ExternalAccounting acts as an database for all
 * completed sales
 * @author Abyel Tesfay
 */
import java.util.ArrayList;
import model.SaleDTO;

public class ExternalAccounting {
	private ArrayList<SaleDTO> SaleLog = new ArrayList<SaleDTO>();

	/**
	 * creates an instance of ExternalAccounting
	 */
	public ExternalAccounting() {
	}
	
	/**
	 * recordSale adds an SaleDTO to its
	 * database
	 * @param completedSale an SaleDTO instance
	 */
	public void recordSale(SaleDTO completedSale) {
		SaleLog.add(completedSale);
	}
}


