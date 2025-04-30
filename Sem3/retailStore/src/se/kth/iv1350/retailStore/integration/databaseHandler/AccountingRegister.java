package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountingRegister {

	private List<SaleDTO> salesHistory;

	AccountingRegister() {
		this.salesHistory = new ArrayList<>();
	}

	void updateRegister(SaleDTO sale) {
		this.salesHistory.add(sale);

		System.out.println("*************Accounting history*************");
		for (SaleDTO saleDTO : this.salesHistory) {
			System.out.println("Sale ID: " + saleDTO.getSaleId());
		}
	}

}
