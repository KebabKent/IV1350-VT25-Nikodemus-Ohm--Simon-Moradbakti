package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class InventoryRegister {
	private List<ItemDTO> fetchedItems;

	InventoryRegister() {
		this.fetchedItems = fetchItemsFromDB();
	}

	private List<ItemDTO> fetchItemsFromDB() {
		List<ItemDTO> databaseItems = new ArrayList<>();

		ItemDTO apple = new ItemDTO(
				"001",
				"Apple",
				5.0f,
				0.12f,
				"Fresh red apple",
				10);

		ItemDTO milk = new ItemDTO(
				"002",
				"Milk",
				15.0f,
				0.12f,
				"1-liter organic milk",
				20);

		ItemDTO bread = new ItemDTO(
				"003",
				"Bread",
				25.0f,
				0.12f,
				"Whole grain bread loaf",
				5);

		databaseItems.add(apple);
		databaseItems.add(milk);
		databaseItems.add(bread);

		return databaseItems;
	}

	ItemDTO retrieveItemInfo(ItemDTO searchedItem) {
		for (ItemDTO databaseItem : this.fetchedItems) {
			if (compareItem(databaseItem, searchedItem)) {
				return databaseItem;
			}
		}
		return null;
	}

	private boolean compareItem(ItemDTO registeredItem, ItemDTO searchedItem) {
		return registeredItem.getItemId().equals(searchedItem.getItemId());
	}

	void updateRegister(SaleDTO sale) {

	}

}
