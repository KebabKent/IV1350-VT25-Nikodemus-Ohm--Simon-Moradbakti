package se.kth.iv1350.retailStore.controller;

import se.kth.iv1350.retailStore.integration.databaseHandler.RegistryHandler;
import se.kth.iv1350.retailStore.integration.RecieptPrinter;

import se.kth.iv1350.retailStore.model.CashRegister;
import se.kth.iv1350.retailStore.model.Sale;
import se.kth.iv1350.retailStore.model.Payment;

import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.AmountDTO;

public class Controller {
	public RegistryHandler creator;
	public RecieptPrinter printer;

	public CashRegister cashRegister;

	public Sale sale;

	public Controller(RegistryHandler creator, RecieptPrinter printer) {
		this.creator = creator;
		this.printer = printer;

		this.cashRegister = new CashRegister();
	}

	public void newSale() {
		this.sale = new Sale();
	}

	public ItemDTO registerItem(ItemDTO searchedItem) {
		ItemDTO foundItem = sale.registerItem(searchedItem, creator);
		return foundItem;
	}

	public Payment fetchDiscount(String customerId) {
		float discount = creator.fetchDiscount(customerId);

		Payment totalCost = sale.calculateDiscountedPrice(discount);

		return totalCost;
	}

	public AmountDTO endSale() {
		return null;
	}

	public AmountDTO payForSale(AmountDTO amount) {
		return null;
	}

}
