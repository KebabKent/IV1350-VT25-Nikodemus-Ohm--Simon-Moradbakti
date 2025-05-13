package se.kth.iv1350.retailStore.integration.databaseHandler;

import se.kth.iv1350.retailStore.model.ItemRegister;
import se.kth.iv1350.retailStore.model.Payment;
import se.kth.iv1350.retailStore.model.Period;
import se.kth.iv1350.retailStore.util.ItemListHandler;
import se.kth.iv1350.retailStore.integration.RecieptPrinter;

import se.kth.iv1350.retailStore.dto.SaleDTO;
import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import se.kth.iv1350.retailStore.exceptions.ItemNotFoundException;
import se.kth.iv1350.retailStore.exceptions.ItemHandlingException;
import se.kth.iv1350.retailStore.exceptions.InventoryDatabaseException;
import se.kth.iv1350.retailStore.exceptions.DatabaseUnreachableException;
import se.kth.iv1350.retailStore.exceptions.OperationFailedException;

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

        List<ItemDTO> itemListCopy = this.itemRegister.getItemListCopy();

        payment.calculateTotalPrice(registeredItems);
        payment.setDiscount(10);
        payment.calculateDiscountedPrice();

        AmountDTO amountPaid = new AmountDTO(100);
        payment.registerAmountPaid(amountPaid);
        payment.calculateChange();

        Period salePeriodCopy = new Period(this.salePeriod);
        Payment paymentCopy = new Payment(this.payment);

        sale = new SaleDTO("100", itemListCopy, salePeriodCopy, paymentCopy);
    }

    @AfterEach
    public void tearDown() {
        inventoryRegister = null;
        fetchedItems = null;
        sale = null;
        searchedItem = null;
        expectedItem = null;
    }

    // Test for when searched item is null
    @Test
    public void testRetrieveItemInfoNull() {
        searchedItem = null;

        try {
            result = inventoryRegister.retrieveItemInfo(searchedItem);
            fail("Expected NullPointerException was not thrown.");
        } catch (ItemNotFoundException e) {
            fail("Expected NullPointerException was not thrown.");
        } catch (InventoryDatabaseException e) {
            fail("Expected NullPointerException was not thrown.");
        } catch (NullPointerException e) {

        }
    }

    // Test for when searched item doesn't exist in the list
    @Test
    public void testRetrieveItemInfoInstanceNotExist() {
        searchedItem = new ItemDTO(
                "004", // non-existing ID
                100);

        try {
            result = inventoryRegister.retrieveItemInfo(searchedItem);
            fail("Expected ItemNotFoundException was not thrown.");
        } catch (ItemNotFoundException e) {
            assertTrue(e.getMessage().contains(searchedItem.getItemId()),
                    "ItemNotFoundException message should contain the searched item ID.");

            assertTrue(e.getItemThatCouldNotBeFound().getItemId().equals(searchedItem.getItemId()),
                    "ItemNotFoundException should contain the searched item.");

        } catch (Exception e) {
            fail("Expected ItemNotFoundException was not thrown.");
        }
    }

    // Test for when searched item exists in the list (they must exist)
    @Test
    public void testRetrieveItemInfoInstanceExists() {
        searchedItem = new ItemDTO(
                "003", // existing item ID
                5000);

        expectedItem = fetchedItems.get(2);

        try {
            result = inventoryRegister.retrieveItemInfo(searchedItem);
            assertEquals(expectedItem, result, "Searching for an existing item should return the correct item.");
        } catch (Exception e) {
            fail("Expected correct item not ItemNotFoundException.");
        }
    }

    // Test for when searched item exists in the list (they must exist)
    @Test
    public void testRetrieveItemInfoExpectedDatabaseException() {
        searchedItem = new ItemDTO(
                "007", // existing item ID
                5000);

        try {
            result = inventoryRegister.retrieveItemInfo(searchedItem);
            fail("Expected correct item not InventoryDatabaseException.");
        } catch (InventoryDatabaseException e) {
            assertTrue(e.getMessage().contains("retrieving item info"),
                    "InventoryDatabaseException message should contain describing text.");
        } catch (Exception e) {
            fail("Expected correct item not InventoryDatabaseException.");
        }
    }

    // Test for updating register with existing quantities
    @Test
    public void testUpdateRegisterWithExistingQuantities() {
        itemRegister = new ItemRegister();
        ItemDTO item = itemRegister.addItem(fetchedItems.get(0), 5);
        itemRegister.addItem(fetchedItems.get(1), 2);
        itemRegister.addItem(fetchedItems.get(2), 1);
        itemRegister.updateItemQuantity(item, 5); // updating quantity of the first item
        registeredItems = itemRegister.getItemListCopy();

        setUpSale();
        inventoryRegister.updateRegister(sale);

        try {
            ItemDTO updatedItem = inventoryRegister.retrieveItemInfo(fetchedItems.get(0));
            assertEquals(1000 - 10, updatedItem.getItemQuantity(), "The item quantity should be updated correctly.");
        } catch (Exception e) {
            fail("Expected returned correct item.");
        }
    }

    // Test for updating register with non-existing quantities (negative values,
    // which can't exist in a store)
    @Test
    public void testUpdateRegisterWithNonExistingQuantities() {
        itemRegister = new ItemRegister();
        ItemDTO item = itemRegister.addItem(fetchedItems.get(0), 5);
        itemRegister.addItem(fetchedItems.get(1), 2);
        itemRegister.addItem(fetchedItems.get(2), 1);
        itemRegister.updateItemQuantity(item, -10); // negative quantity shouldn't be allowed
        registeredItems = itemRegister.getItemListCopy();

        setUpSale();

        assertFalse(inventoryRegister.updateRegister(sale),
                "Updating with non-existing quantities should return false.");
    }

    // Test for updating register with quantities which are bigger than the stock.
    // (as you can't sell more items than exists in stock)
    @Test
    public void testUpdateRegisterWithQuantitiesAboveAvailableQuantity() {
        itemRegister = new ItemRegister();
        ItemDTO item = itemRegister.addItem(fetchedItems.get(0), 5);
        itemRegister.addItem(fetchedItems.get(1), 2);
        itemRegister.addItem(fetchedItems.get(2), 1);
        itemRegister.updateItemQuantity(item, 3000); // too many items
        registeredItems = itemRegister.getItemListCopy();

        setUpSale();
        inventoryRegister.updateRegister(sale);

        assertFalse(inventoryRegister.updateRegister(sale),
                "Updating with quantities above avaiable quantity should return false."); // should be false!

    }

    // Test for updating register with a non-existent (Null in this case!) item
    @Test
    public void testUpdateRegisterWithNonExistantItem() {
        ItemDTO nonExistingItem = new ItemDTO("999", // non-existent item ID
                "NonExistantItem",
                0.0f,
                0.0f,
                "Non-existent item",
                0);

        itemRegister = new ItemRegister();
        ItemDTO item = itemRegister.addItem(fetchedItems.get(0), 5);
        itemRegister.addItem(fetchedItems.get(1), 2);
        itemRegister.addItem(fetchedItems.get(2), 1);
        itemRegister.addItem(nonExistingItem, 10); // add non-existent item
        itemRegister.updateItemQuantity(item, 5);
        registeredItems = itemRegister.getItemListCopy();

        setUpSale();
        inventoryRegister.updateRegister(sale);

        try {
            inventoryRegister.retrieveItemInfo(nonExistingItem);
        } catch (ItemNotFoundException e) {
            assertTrue(e.getMessage().contains(nonExistingItem.getItemId()),
                    "ItemNotFoundException message should contain the searched item ID.");

            assertTrue(e.getItemThatCouldNotBeFound().getItemId().equals(nonExistingItem.getItemId()),
                    "ItemNotFoundException should contain the searched item.");
        } catch (Exception e) {
            fail("Expected ItemNotFoundException.");
        }
    }

}
