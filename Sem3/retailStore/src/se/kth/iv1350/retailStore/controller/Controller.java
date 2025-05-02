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

    /**
     * Starts a new sale by creating a new Sale object.
     */
    public void newSale() {
        this.sale = new Sale();
    }

    /**
     * Registers an item by finding and adding it to the sale.
     * 
     * @param searchedItem The item to be registered.
     * @return The found item.
     */
    public ItemDTO registerItem(ItemDTO searchedItem) {
        ItemDTO foundItem = sale.registerItem(searchedItem, creator);
        return foundItem;
    }

    public SaleDTO getSaleInfo() {
        return sale.getSaleInfo();
    }

    /**
     * Fetches the discount for a given customer and applies it to the sale.
     * 
     * @param customerId The customer ID to fetch the discount for.
     * @return The updated SaleDTO with the applied discount.
     */
    public SaleDTO fetchDiscount(String customerId) {
        float discount = creator.fetchDiscount(customerId);

        SaleDTO saleInfo = sale.calculateDiscountedPrice(discount);

        return saleInfo;
    }

    /**
     * Ends the sale and prepares the SaleDTO for final processing.
     * 
     * @return The final SaleDTO with all sale details.
     */
    public SaleDTO endSale() {
        return sale.endSale();
    }

    /**
     * Processes the payment for the sale and updates the cash register and other
     * registers.
     * Prints the receipt after the payment is completed.
     * 
     * @param amount The amount paid by the customer.
     * @return The SaleDTO with payment information.
     */
    public SaleDTO payForSale(AmountDTO amount) {
        SaleDTO saleInfo = sale.payForSale(amount);

        cashRegister.registerAmountPayed(saleInfo);
        this.creator.updateRegisters(saleInfo);
        RecieptPrinter.printReciept(saleInfo);

        return saleInfo;
    }

}
