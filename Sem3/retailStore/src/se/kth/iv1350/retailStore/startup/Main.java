package se.kth.iv1350.retailStore.startup;

import se.kth.iv1350.retailStore.view.View;
import se.kth.iv1350.retailStore.controller.Controller;
import se.kth.iv1350.retailStore.integration.databaseHandler.RegistryHandler;

/**
 * The main class that initialices the Retail Store
 * application.
 * It startes the necessary components and starts the sample execution.
 */
public class Main {

	/**
	 * The entry point of the program. Initializes the necessary components and
	 * starts the sample execution.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the Retail Store!");

		RegistryHandler creator = new RegistryHandler();
		Controller controller = new Controller(creator);
		View view = new View(controller);

		view.sampleExecution();
	}

	/**
	 * Creates a new instance of the Main class.
	 */
	public Main() {
	}
}
