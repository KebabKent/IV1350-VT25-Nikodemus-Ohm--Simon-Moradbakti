package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.dto.SaleDTO;
import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;

public class RegistryHandler {
	private AccountingRegister accountingRegister;
	private InventoryRegister inventoryRegister;

	public RegistryHandler() {
		this.accountingRegister = new AccountingRegister();
		this.inventoryRegister = new InventoryRegister();
	}

	public void updateRegisters(SaleDTO sale) {
		accountingRegister.updateRegister(sale);
		inventoryRegister.updateRegister(sale);
	}

	public ItemDTO retrieveItemInfo(ItemDTO searchedItem) {

		ItemDTO foundItem = inventoryRegister.retrieveItemInfo(searchedItem);

		return foundItem;
	}

	public float fetchDiscount(String customerId) {
		float discount = DiscountRegister.fetchDiscount(customerId);

		return discount;
	}

}
