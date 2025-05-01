package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.model.ItemRegister;
import se.kth.iv1350.retailStore.model.Payment;
import se.kth.iv1350.retailStore.model.Period;

import se.kth.iv1350.retailStore.integration.RecieptPrinter;

import se.kth.iv1350.retailStore.dto.SaleDTO;
import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class InventoryRegisterTest {
    private InventoryRegister inventoryRegister;
    List<ItemDTO> fetchedItems;
    List<ItemDTO> registeredItems;

    private SaleDTO sale;
    private ItemRegister itemRegister;
    private Period salePeriod;
    private Payment payment;

    private ItemDTO searchedItem;
    private ItemDTO expectedItem;
    private ItemDTO result;

    @BeforeEach
    public void setUp() {
        inventoryRegister = new InventoryRegister();
        fetchedItems = inventoryRegister.fetchItemsFromDB();
    }

    public void setUpSale() {
        salePeriod = new Period();
        salePeriod.setEndTime();

        payment = new Payment();
        payment.calculateTotalPrice(registeredItems);
        payment.setDiscount(10);
        payment.calculateDiscountedPrice();

        AmountDTO amountPaid = new AmountDTO(100);
        payment.registerAmountPaid(amountPaid);
        payment.calculateChange();

        sale = new SaleDTO("100", itemRegister, salePeriod, payment);
    }

    @AfterEach
    public void tearDown() {
        inventoryRegister = null;
        fetchedItems = null;
        sale = null;
        searchedItem = null;
        expectedItem = null;
    }

    @Test
    public void testRetrieveItemInfoNull() {
        searchedItem = null;
        result = inventoryRegister.retrieveItemInfo(searchedItem);
        assertNull(result, "Searching for null should return null.");
    }

    @Test
    public void testRetrieveItemInfoInstanceNotExist() {
        searchedItem = new ItemDTO(
                "004",
                100);

        result = inventoryRegister.retrieveItemInfo(searchedItem);
        ;
        assertNull(result, "Searching for an item that does not exist should return null.");
    }

    @Test
    public void testRetrieveItemInfoInstanceExists() {
        searchedItem = new ItemDTO(
                "003",
                5000);

        expectedItem = fetchedItems.get(2);

        result = inventoryRegister.retrieveItemInfo(searchedItem);
        ;
        assertEquals(expectedItem, result, "Searching for an existing item should return the correct item.");
    }

    @Test
    public void testUpdateRegisterWithExistingQuantities() {
        itemRegister = new ItemRegister();
        itemRegister.addItem(fetchedItems.get(0), 5);
        itemRegister.addItem(fetchedItems.get(1), 2);
        itemRegister.addItem(fetchedItems.get(2), 1);
        itemRegister.updateItemQuantity(0, 5);
        registeredItems = itemRegister.getItemList();

        setUpSale();
        inventoryRegister.updateRegister(sale);

        ItemDTO updatedItem = inventoryRegister.retrieveItemInfo(fetchedItems.get(0));
        assertEquals(1000 - 10, updatedItem.getItemQuantity(), "The item quantity should be updated correctly.");
    }

    @Test
    public void testUpdateRegisterWithNonExistingQuantities() {
        itemRegister = new ItemRegister();
        itemRegister.addItem(fetchedItems.get(0), 5);
        itemRegister.addItem(fetchedItems.get(1), 2);
        itemRegister.addItem(fetchedItems.get(2), 1);
        itemRegister.updateItemQuantity(0, -10);
        registeredItems = itemRegister.getItemList();

        setUpSale();

        assertFalse(inventoryRegister.updateRegister(sale),
                "Updating with non-existing quantities should return false.");
    }

    @Test
    public void testUpdateRegisterWithQuantitiesAboveAvailableQuantity() {
        itemRegister = new ItemRegister();
        itemRegister.addItem(fetchedItems.get(0), 5);
        itemRegister.addItem(fetchedItems.get(1), 2);
        itemRegister.addItem(fetchedItems.get(2), 1);
        itemRegister.updateItemQuantity(0, 3000);
        registeredItems = itemRegister.getItemList();

        setUpSale();
        inventoryRegister.updateRegister(sale);

        assertFalse(inventoryRegister.updateRegister(sale),
                "Updating with a quantity above available stock should return false.");
    }

    @Test
    public void testUpdateRegisterWithNonExistantItem() {

        ItemDTO nonExistingItem = new ItemDTO("999",
                "NonExistantItem",
                0.0f,
                0.0f,
                "Non-existent item",
                0);

        itemRegister = new ItemRegister();
        itemRegister.addItem(fetchedItems.get(0), 5);
        itemRegister.addItem(fetchedItems.get(1), 2);
        itemRegister.addItem(fetchedItems.get(2), 1);
        itemRegister.addItem(nonExistingItem, 10);
        itemRegister.updateItemQuantity(0, 5);
        registeredItems = itemRegister.getItemList();

        setUpSale();
        inventoryRegister.updateRegister(sale);

        expectedItem = null;
        result = inventoryRegister.retrieveItemInfo(nonExistingItem);
        assertEquals(expectedItem, result, "Trying to update non-existent item should do nothing.");
    }

}
