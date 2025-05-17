package se.kth.iv1350.retailStore.controller;

import se.kth.iv1350.retailStore.integration.databaseHandler.RegistryHandler;
import se.kth.iv1350.retailStore.integration.RecieptPrinter;

import se.kth.iv1350.retailStore.model.CashRegister;
import se.kth.iv1350.retailStore.model.Sale;
import se.kth.iv1350.retailStore.model.Payment;

import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

import se.kth.iv1350.retailStore.dto.AmountDTO;

import se.kth.iv1350.retailStore.exceptions.ItemHandlingException;
import se.kth.iv1350.retailStore.exceptions.OperationFailedException;
import se.kth.iv1350.retailStore.exceptions.DatabaseUnreachableException;

import se.kth.iv1350.retailStore.util.FileLogger;

import se.kth.iv1350.retailStore.util.TotalRevenueObserver;

/**
 * Creates a new instance of the controller.
 * Initializes the registry handler and a new cash register.
 */
public class Controller {
    private RegistryHandler creator;
    private CashRegister cashRegister;
    private Sale sale;
    private FileLogger logger;

    /**
     * Creates a new instance of the controller.
     * 
     * @param creator The registry handler to be used for item registration and
     *                discount fetching.
     * @param logger  The logger to be used for logging operations.
     */
    public Controller(RegistryHandler creator, FileLogger logger) {
        this.creator = creator;
        this.cashRegister = new CashRegister();
        this.logger = logger;
    }

    /**
     * Starts a new sale by creating a new Sale object.
     */
    public void newSale() {
        this.sale = new Sale(this.logger);
    }

    /**
     * Registers an item by finding and adding it to the sale.
     * 
     * @param searchedItem The item to be registered.
     * @return The found item.
     * @throws ItemHandlingException        If the item is not found in the
     * @throws DatabaseUnreachableException If the database is unreachable.
     */
    public ItemDTO registerItem(ItemDTO searchedItem) throws ItemHandlingException, DatabaseUnreachableException {
        ItemDTO foundItem = sale.registerItem(searchedItem, creator);
        return foundItem;
    }

    /**
     * This method retrieves the sale information if available.
     * It checks if the sale object is not null to avoid errors.
     * 
     * @return SaleDTO containing sale information, or null if no sale information
     *         is available.
     */
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

    /**
     * Adds a new observer to the cash register to monitor revenue changes.
     * 
     * @param newObserver The observer to be added.
     */
    public void addRevenueObserver(TotalRevenueObserver newObserver) {
        cashRegister.addRevenueObserver(newObserver);
    }

}
