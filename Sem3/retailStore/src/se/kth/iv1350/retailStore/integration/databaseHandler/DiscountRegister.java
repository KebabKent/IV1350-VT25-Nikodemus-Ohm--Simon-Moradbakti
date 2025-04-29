package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.model.ItemRegister;

import se.kth.iv1350.retailStore.dto.AmountDTO;

public class DiscountRegister {

	static float fetchDiscount(ItemRegister itemRegister) {
		return 10;
	}

	static float fetchDiscount(AmountDTO totalCost) {
		return 15;
	}

	static float fetchDiscount(String customerId) {
		if (customerId.equals("123")) {
			return 10;
		}

		return 0;
	}

}
