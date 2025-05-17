package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.model.ItemRegister;
import se.kth.iv1350.retailStore.dto.AmountDTO;

/**
 * This class represents the discount register, which is responsible for
 * storing the details of any discounts applied to sales.
 */
class DiscountRegister {
	private static final DiscountRegister DISCOUNT_REGISTER = new DiscountRegister();

	/**
	 * Returns the singleton instance of DiscountRegister.
	 * 
	 * @param DiscountRegister
	 */
	public static DiscountRegister getDiscountRegister() {
		return DISCOUNT_REGISTER;
	}

	private DiscountRegister() {
	}

	/**
	 * Returns a discount based on the contents of the item register.
	 * This is a placeholder that always returns 10%.
	 *
	 * @param itemRegister The current item register containing sold items.
	 * @return A fixed discount percentage (10).
	 */
	float fetchDiscount(ItemRegister itemRegister) {
		return 10;
	}

	/**
	 * Returns a discount based on the total cost of the sale.
	 * This is a placeholder that always returns 15%.
	 *
	 * @param totalCost The total cost of the sale.
	 * @return A fixed discount percentage (15).
	 */
	float fetchDiscount(AmountDTO totalCost) {
		return 15;
	}

	/**
	 * Returns a discount based on the customer's ID.
	 * If the customer ID matches "123", a discount is given.
	 *
	 * @param customerId The ID of the customer.
	 * @return 10% if customer ID is "123", in other cases it is 0%.
	 */
	float fetchDiscount(String customerId) {
		if (customerId.equals("123")) {
			return 10;
		}

		return 0;
	}
}
