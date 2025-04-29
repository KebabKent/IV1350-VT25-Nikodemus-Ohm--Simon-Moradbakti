package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.RecieptPrinter;
import se.kth.iv1350.retailStore.integration.databaseHandler.RegistryHandler;

import se.kth.iv1350.retailStore.model.ItemRegister;
import se.kth.iv1350.retailStore.model.Payment;
import se.kth.iv1350.retailStore.model.Period;

import se.kth.iv1350.retailStore.dto.ItemDTO;

public class Sale {
	// Hantera Payment.java

	private ItemRegister itemRegister;

	private Period salePeriod;

	private Payment payment;

	public Sale() {
		this.itemRegister = new ItemRegister();
		this.payment = new Payment();
		this.salePeriod = new Period(); // set time of sale
	}

	public void registerItem(ItemDTO foundItem) {
		// Fortsätt här imorgon
	}

	public void updateRegisters(RecieptPrinter printer) {

	}

	public boolean itemExists(ItemDTO searchedItem) {
		ItemDTO foundItem = itemRegister.findItem(searchedItem);
		if (foundItem != null) {
			return true;
		}
		return false;
	}

}
