package se.kth.iv1350.retailStore.view;

import se.kth.iv1350.retailStore.controller.Controller;

import se.kth.iv1350.retailStore.model.Payment;

import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;

public class View {
	public Controller controller;

	public View(Controller controller) {
		this.controller = controller;
	}

	public void sampleExecution() {
		controller.newSale();

		ItemDTO searchedItem = new ItemDTO(
				"001",
				null,
				null,
				null,
				null,
				1);

		ItemDTO foundItem = controller.registerItem(searchedItem);

		searchedItem = new ItemDTO(
				"003",
				null,
				null,
				null,
				null,
				1);

		foundItem = controller.registerItem(searchedItem);

		searchedItem = new ItemDTO(
				"001",
				null,
				null,
				null,
				null,
				100);

		foundItem = controller.registerItem(searchedItem);

		Payment price = controller.fetchDiscount("123");
		System.out.println("Total price: " + price.returnTotalPrice());
		System.out.println("Discounted price: " + price.returnDiscountedPrice());
		System.out.println("Discounted percentage: " + price.returnDiscountedPercentage());

	}
}
