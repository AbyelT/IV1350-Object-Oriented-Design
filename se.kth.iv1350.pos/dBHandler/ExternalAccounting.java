package dBHandler;

/**
 * ExternalAccounting acts as an database for all
 * completed sales
 * @author Abyel Tesfay
 */
import java.util.ArrayList;
import model.SaleDTO;
import model.TotalRevenue;

public class ExternalAccounting {
	private ArrayList<SaleDTO> SaleLog = new ArrayList<SaleDTO>();
	private ArrayList<TotalRevenue> allObservers = new ArrayList<TotalRevenue>(); 
 
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
		notifyObserver();
	}
	
	public void addObserver(TotalRevenue observer) {
		allObservers.add(observer);
	}
	
	public void notifyObserver() {
		for (TotalRevenue observer : allObservers) {
            observer.showTotalRevenue(getTotalRevenue());
        }
	}
	
	private int getTotalRevenue() {
		int totalRevenue = 0;
		for (SaleDTO loggedSale : SaleLog) {
			totalRevenue += loggedSale.getTotalPrice();
		}
		return totalRevenue;
	}
}


