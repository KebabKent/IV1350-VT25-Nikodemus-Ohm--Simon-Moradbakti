package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.RecieptPrinter;
import se.kth.iv1350.retailStore.integration.databaseHandler.RegistryHandler;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.List;

import se.kth.iv1350.retailStore.dto.AmountDTO;

public class Sale {
	private ItemRegister itemRegister;

	private Period salePeriod;

	private Payment payment;

	public Sale() {
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

	public Payment calculateDiscountedPrice(float discount) {
		this.payment.setDiscount(discount);

		List<ItemDTO> itemList = this.itemRegister.getItemList();
		this.payment.calculateTotalPrice(itemList);
		this.payment.calculateDiscountedPrice();

		return this.payment;

	}

	public void updateRegisters(RecieptPrinter printer) {

	}

}
