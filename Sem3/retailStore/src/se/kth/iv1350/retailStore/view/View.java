package se.kth.iv1350.retailStore.view;

import se.kth.iv1350.retailStore.controller.Controller;

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

	}
}
