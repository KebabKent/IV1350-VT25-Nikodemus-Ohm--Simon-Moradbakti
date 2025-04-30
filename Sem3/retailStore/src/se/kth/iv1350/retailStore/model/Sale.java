package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.RecieptPrinter;
import se.kth.iv1350.retailStore.integration.databaseHandler.RegistryHandler;

import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

import java.util.List;
import java.util.Random;

import se.kth.iv1350.retailStore.dto.AmountDTO;

public class Sale {
	private String saleId;

	private ItemRegister itemRegister;

	private Period salePeriod;

	private Payment payment;

	private SaleDTO saleInfo;

	public Sale() {
		Random random = new Random();
		this.saleId = random.nextInt(1000000, 9999999) + "";

		this.itemRegister = new ItemRegister();
		this.payment = new Payment();
		this.salePeriod = new Period(); // set time of sale
	}

	/**
	 * Registers an item for the sale. If the item exists, it updates the quantity.
	 * If it does not exist, it adds the item to the register.
	 * 
	 * @param searchedItem The item to register.
	 * @param creator The registry handler used to retrieve item info.
	 * @return The registered item.
	 */
	public ItemDTO registerItem(ItemDTO searchedItem, RegistryHandler creator) {
		Integer foundItemPosition = itemRegister.findItem(searchedItem);
		if (foundItemPosition != null) {
			return itemRegister.updateItemDTO(foundItemPosition, searchedItem.getItemQuantity());
		}

		ItemDTO foundItem = creator.retrieveItemInfo(searchedItem);
		this.itemRegister.addItem(foundItem, searchedItem.getItemQuantity());
		return foundItem;
	}

	/**
	 * Applies a discount to the sale and returns updated sale information.
	 * 
	 * @param discount The discount percentage to apply.
	 * @return The updated sale information.
	 */
	public SaleDTO calculateDiscountedPrice(float discount) {
		this.payment.setDiscount(discount);
		return getSaleDTO();
	}

	/**
	 * Ends the sale and returns the final sale information.
	 * 
	 * @return The final sale information.
	 */
	public SaleDTO endSale() {
		return getSaleDTO();
	}

	/**
	 * Registers the amount paid for the sale and calculates the change.
	 * 
	 * @param amount The amount paid for the sale.
	 * @return The updated sale information including the amount paid and change.
	 */
	public SaleDTO payForSale(AmountDTO amount) {
		this.payment.registerAmountPaid(amount);
		this.payment.calculateChange();

		this.saleInfo = new SaleDTO(this.saleInfo, this.payment);

		return this.saleInfo;
	}

	/**
	 * Calculates the total price, applies any discounts, and finalizes the sale information.
	 * 
	 * @return The updated sale information with the total price, discount, and final details.
	 */
	public SaleDTO getSaleDTO() {
		this.payment.calculateTotalPrice(itemRegister.getItemList());
		this.payment.calculateDiscountedPrice();
		this.salePeriod.setEndTime();

		this.saleInfo = new SaleDTO(saleId, this.itemRegister, this.salePeriod, this.payment);

		return this.saleInfo;
	}

}
