package se.kth.iv1350.retailStore.controller;

import se.kth.iv1350.retailStore.integration.databaseHandler.RegistryHandler;
import se.kth.iv1350.retailStore.integration.RecieptPrinter;

import se.kth.iv1350.retailStore.model.CashRegister;
import se.kth.iv1350.retailStore.model.Sale;
import se.kth.iv1350.retailStore.model.Payment;

import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;
import se.kth.iv1350.retailStore.dto.AmountDTO;

public class Controller {
	public RegistryHandler creator;
	public CashRegister cashRegister;

	public Sale sale;

	public Controller(RegistryHandler creator) {
		this.creator = creator;
		this.cashRegister = new CashRegister();
	}

	public void newSale() {
		this.sale = new Sale();
	}

	public ItemDTO registerItem(ItemDTO searchedItem) {
		ItemDTO foundItem = sale.registerItem(searchedItem, creator);
		return foundItem;
	}

	public SaleDTO fetchDiscount(String customerId) {
		float discount = creator.fetchDiscount(customerId);

		SaleDTO saleInfo = sale.calculateDiscountedPrice(discount);

		return saleInfo;
	}

	public SaleDTO endSale() {
		return sale.endSale();
	}

	public SaleDTO payForSale(AmountDTO amount) {
		SaleDTO saleInfo = sale.payForSale(amount);

		cashRegister.registerAmountPayed(saleInfo);
		this.creator.updateRegisters(saleInfo);
		RecieptPrinter.printReciept(saleInfo);

		return saleInfo;
	}

}
