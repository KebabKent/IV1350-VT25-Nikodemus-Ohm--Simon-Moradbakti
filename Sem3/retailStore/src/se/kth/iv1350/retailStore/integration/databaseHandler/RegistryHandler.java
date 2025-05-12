package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.dto.SaleDTO;
import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;

import se.kth.iv1350.retailStore.exceptions.ItemNotFoundException;
import se.kth.iv1350.retailStore.exceptions.InventoryDatabaseException;

/**
 * This class is a central point for managing the other database
 * relatedregisters.
 */
public class RegistryHandler {

	private AccountingRegister accountingRegister;

	private InventoryRegister inventoryRegister;

	/**
	 * Creates a new RegistryHandler that manages both the accounting and inventory
	 * registers.
	 * This is the main entry point for interacting with the registers.
	 */
	public RegistryHandler() {
		this.accountingRegister = new AccountingRegister();
		this.inventoryRegister = new InventoryRegister();
	}

	/**
	 * Updates both the accounting and inventory registers with information from a
	 * completed sale.
	 * This ensures that the sale is logged in the accounting system and the
	 * inventory is updated.
	 *
	 * @param sale The sale that has been completed and needs to be recorded.
	 */
	public void updateRegisters(SaleDTO sale) {
		accountingRegister.updateRegister(sale);
		inventoryRegister.updateRegister(sale);
	}

	/**
	 * Retrieves information about a specific item from the inventory.
	 * It searches the inventory register to find the item based on the provided
	 * information.
	 *
	 * @param searchedItem The item to be searched in the inventory.
	 * @return The item found in the inventory, or null if not found.
	 * @throws ItemNotFoundException      If the item is not found in the inventory.
	 * @throws InventoryDatabaseException If there is an issue with the inventory
	 */
	public ItemDTO retrieveItemInfo(ItemDTO searchedItem) throws ItemNotFoundException, InventoryDatabaseException {

		ItemDTO foundItem = inventoryRegister.retrieveItemInfo(searchedItem);

		return foundItem;
	}

	/**
	 * Fetches the discount percentage for a customer based on their customer ID.
	 * If the customer is recognized (e.g., customer ID "123"), they receive a
	 * discount.
	 *
	 * @param customerId The ID of the customer for which the discount is to be
	 *                   fetched.
	 * @return The discount percentage for the given customer.
	 */
	public float fetchDiscount(String customerId) {
		float discount = DiscountRegister.fetchDiscount(customerId);

		return discount;
	}
}
