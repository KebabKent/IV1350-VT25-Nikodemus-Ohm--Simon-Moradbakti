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

	public ItemDTO registerItem(ItemDTO searchedItem, RegistryHandler creator) {
		Integer foundItemPosition = itemRegister.findItem(searchedItem);
		if (foundItemPosition != null) {
			return itemRegister.updateItemDTO(foundItemPosition, searchedItem.getItemQuantity());
		}

		ItemDTO foundItem = creator.retrieveItemInfo(searchedItem);
		this.itemRegister.addItem(foundItem, searchedItem.getItemQuantity());
		return foundItem;
	}

	public SaleDTO calculateDiscountedPrice(float discount) {
		this.payment.setDiscount(discount);
		return getSaleDTO();
	}

	public SaleDTO endSale() {
		return getSaleDTO();
	}

	public SaleDTO payForSale(AmountDTO amount) {
		this.payment.registerAmountPaid(amount);
		this.payment.calculateChange();

		this.saleInfo = new SaleDTO(this.saleInfo, this.payment);

		return this.saleInfo;
	}

	public SaleDTO getSaleDTO() {
		this.payment.calculateTotalPrice(itemRegister.getItemList());
		this.payment.calculateDiscountedPrice();
		this.salePeriod.setEndTime();

		this.saleInfo = new SaleDTO(saleId, this.itemRegister, this.salePeriod, this.payment);

		return this.saleInfo;
	}

}
