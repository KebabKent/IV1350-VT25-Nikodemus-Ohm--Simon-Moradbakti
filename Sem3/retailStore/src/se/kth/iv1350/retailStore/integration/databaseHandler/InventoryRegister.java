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
				0.15f,
				"Whole grain bread loaf",
				5000);

		fetchedItems.add(apple);
		fetchedItems.add(milk);
		fetchedItems.add(bread);
	}

	/**
	 * Simulates fetching items from a database.
	 * Returns the static list of available items.
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
	 * Simulates updating the inventory by reducing stock quantities based on the
	 * sale.
	 * The update is stored in memory but not written to any external database.
	 * Prints the updated inventory state.
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
