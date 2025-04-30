package se.kth.iv1350.retailStore.startup;

import se.kth.iv1350.retailStore.view.View;
import se.kth.iv1350.retailStore.controller.Controller;
import se.kth.iv1350.retailStore.integration.databaseHandler.RegistryHandler;

/**
 * Main class to start the program.
 * This class is responsible for creating the necessary objects and starting the
 * program.
 * 
 */

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to the Retail Store!");

		RegistryHandler creator = new RegistryHandler();
		Controller controller = new Controller(creator);
		View view = new View(controller);

		view.sampleExecution();
	}

}
