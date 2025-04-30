package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.util.CompareItemDTO;

import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class InventoryRegister {
	private List<ItemDTO> fetchedItems;

	/**
	 * Creates a new inventory register and loads item data from the "database".
	 * This is a simulation and just fetches a static list of the items.
	 */
	InventoryRegister() {
		this.fetchedItems = fetchItemsFromDB();
	}

	/**
	 * Simulates retrieving an item from the inventory that matches the searched item.
	 * 
	 * @param searchedItem The item being searched for.
	 * @return The item from the inventory that matches the search.
	 */
	ItemDTO retrieveItemInfo(ItemDTO searchedItem) {
		return CompareItemDTO.searchItemDTOInstance(searchedItem, this.fetchedItems);
	}

	/**
	 * Updates the inventory register based on the sold items in the given sale.
	 * It subtracts the sold quantity from the stock. The update is currently not stored
	 * because new ItemDTOs are created but not saved back in the list.
	 * Prints the current state of the inventory after the sale.
	 *
	 * @param sale The sale that contains the sold items and their quantities.
	 */
	void updateRegister(SaleDTO sale) {
		for (ItemDTO item : sale.getItemList()) {
			for (ItemDTO fetchedItem : this.fetchedItems) {
				if (item.getItemId().equals(fetchedItem.getItemId())) {
					fetchedItem = new ItemDTO(fetchedItem, fetchedItem.getItemQuantity() - item.getItemQuantity());
				}
			}
		}

		System.out.println("*************Inventory register*************");
		int i = 0;
		for (ItemDTO item : this.fetchedItems) {
			System.out.println("    Item: " + i++ + "\n" +
					"       ID: " + item.getItemId() + "\n" +
					"       Name: " + item.getItemName() + "\n" +
					"       Quantity: " + item.getItemQuantity() + "\n");
		}
	}

	/**
	 * Simulates fetching items from a database.
	 * This is a private method that creates a static list of items with hardcoded values.
	 *
	 * @return A list of available items.
	 */
	private List<ItemDTO> fetchItemsFromDB() {
		List<ItemDTO> databaseItems = new ArrayList<>();

		ItemDTO apple = new ItemDTO(
				"001",
				"Apple",
				5.0f,
				0.12f,
				"Fresh red apple",
				1000);

		ItemDTO milk = new ItemDTO(
				"002",
				"Milk",
				15.0f,
				0.12f,
				"1-liter organic milk",
				2000);

		ItemDTO bread = new ItemDTO(
				"003",
				"Bread",
				25.0f,
				0.12f,
				"Whole grain bread loaf",
				5000);

		databaseItems.add(apple);
		databaseItems.add(milk);
		databaseItems.add(bread);

		return databaseItems;
	}
}
