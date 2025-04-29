package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.integration.databaseHandler.AccountingRegister;
import se.kth.iv1350.retailStore.integration.databaseHandler.DiscountRegister;
import se.kth.iv1350.retailStore.integration.databaseHandler.InventoryRegister;

import se.kth.iv1350.retailStore.dto.SaleDTO;
import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;

public class RegistryHandler {
	private AccountingRegister accountingRegister;
	private DiscountRegister discountRegister;
	private InventoryRegister inventoryRegister;

	public RegistryHandler() {
		this.accountingRegister = new AccountingRegister();
		this.discountRegister = new DiscountRegister();
		this.inventoryRegister = new InventoryRegister();
	}

	public void updateRegisters(SaleDTO sale) {

	}

	public AmountDTO fetchDiscountedPrice(String customerId) {
		return null;
	}

	public ItemDTO retrieveItemInfo(ItemDTO searchedItem) {

		ItemDTO foundItem = inventoryRegister.retrieveItemInfo(searchedItem);

		return foundItem;
	}

}
