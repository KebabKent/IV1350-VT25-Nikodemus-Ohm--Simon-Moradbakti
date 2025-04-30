package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.util.CompareItemDTO;

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

	ItemDTO retrieveItemInfo(ItemDTO searchedItem) {
		return CompareItemDTO.searchItemDTOInstance(searchedItem, this.fetchedItems);
	}

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

}
