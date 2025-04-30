package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountingRegister {

	private List<SaleDTO> salesHistory;

	/**
	 * Creates a new accounting register that keeps track of all done sales.
	 * It stores them in a list which represents the accounting history.
	 */
	AccountingRegister() {
		this.salesHistory = new ArrayList<>();
	}

	/**
	 * Adds a completed sale to the accounting history.
	 * This simulates saving the sale to an external accounting system.
	 * Also prints all sale IDs stored so far (for testing/debugging).
	 *
	 * @param sale The sale to be recorded in the register.
	 */
	void updateRegister(SaleDTO sale) {
		this.salesHistory.add(sale);

		System.out.println("*************Accounting history*************");
		for (SaleDTO saleDTO : this.salesHistory) {
			System.out.println("Sale ID: " + saleDTO.getSaleId());
		}
	}

}
