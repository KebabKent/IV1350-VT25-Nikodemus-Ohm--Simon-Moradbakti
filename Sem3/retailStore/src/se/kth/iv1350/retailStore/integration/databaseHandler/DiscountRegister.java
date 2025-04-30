package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.model.ItemRegister;
import se.kth.iv1350.retailStore.dto.AmountDTO;

public class DiscountRegister {

	/**
	 * Returns a discount based on the contents of the item register.
	 * This is a placeholder that always returns 10%.
	 *
	 * @param itemRegister The current item register containing sold items.
	 * @return A fixed discount percentage (10).
	 */
	static float fetchDiscount(ItemRegister itemRegister) {
		return 10;
	}

	/**
	 * Returns a discount based on the total cost of the sale.
	 * This is a placeholder that always returns 15%.
	 *
	 * @param totalCost The total cost of the sale.
	 * @return A fixed discount percentage (15).
	 */
	static float fetchDiscount(AmountDTO totalCost) {
		return 15;
	}

	/**
	 * Returns a discount based on the customer's ID.
	 * If the customer ID matches "123", a discount is given.
	 *
	 * @param customerId The ID of the customer.
	 * @return 10% if customer ID is "123", otherwise 0%.
	 */
	static float fetchDiscount(String customerId) {
		if (customerId.equals("123")) {
			return 10;
		}

		return 0;
	}
}
