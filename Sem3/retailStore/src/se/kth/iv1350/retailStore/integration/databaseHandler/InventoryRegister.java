package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.util.ItemListHandler;

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
		fetchedItems = new ArrayList<>();

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

		fetchedItems.add(apple);
		fetchedItems.add(milk);
		fetchedItems.add(bread);
	}

	/**
	 * Simulates fetching items from a database.
	 * This is a private method that creates a static list of items with hardcoded
	 * values.
	 *
	 * @return A list of available items.
	 */
	List<ItemDTO> fetchItemsFromDB() {
		return this.fetchedItems;
	}

	/**
	 * Simulates retrieving an item from the inventory that matches the searched
	 * item.
	 * 
	 * @param searchedItem The item being searched for.
	 * @return The item from the inventory that matches the search.
	 */
	ItemDTO retrieveItemInfo(ItemDTO searchedItem) {
		return ItemListHandler.searchItemDTOInstance(searchedItem, this.fetchedItems);
	}

	/**
	 * Updates the inventory register based on the sold items in the given sale.
	 * It subtracts the sold quantity from the stock. The update is currently not
	 * stored
	 * because new ItemDTOs are created but not saved back in the list.
	 * Prints the current state of the inventory after the sale.
	 *
	 * @param sale The sale that contains the sold items and their quantities.
	 * @return true if the update was successful, false otherwise.
	 */
	boolean updateRegister(SaleDTO sale) {
		for (ItemDTO item : sale.getItemList()) {
			for (int i = 0; i < this.fetchedItems.size(); i++) {
				if (item.getItemId().equals(fetchedItems.get(i).getItemId())) {
					fetchedItems.set(i, new ItemDTO(
							fetchedItems.get(i),
							fetchedItems.get(i).getItemQuantity() -
									item.getItemQuantity()));
				}
			}
		}

		System.out.println("*************Inventory register*************");
		ItemListHandler.printItemList(this.fetchedItems);
		return true;
	}
}
