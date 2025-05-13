package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.model.ItemRegister;
import se.kth.iv1350.retailStore.model.Payment;
import se.kth.iv1350.retailStore.model.Period;
import se.kth.iv1350.retailStore.util.ItemListHandler;
import se.kth.iv1350.retailStore.util.FileLogger;
import se.kth.iv1350.retailStore.integration.RecieptPrinter;
import se.kth.iv1350.retailStore.integration.databaseHandler.RegistryHandler;

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

public class SaleTest {
    private Sale sale;

    private RegistryHandler registryHandler;

    private ItemDTO searchedItem;
    private ItemDTO expectedItem;
    private ItemDTO result;

    @BeforeEach
    public void setUp() {
        registryHandler = new RegistryHandler();
        sale = new Sale(new FileLogger());
        searchedItem = new ItemDTO("001", 1);
    }

    @AfterEach
    public void tearDown() {
        sale = null;
        registryHandler = null;
        searchedItem = null;
        expectedItem = null;
        result = null;
    }

    // Test for when searched item doesn't exist in the list
    @Test
    public void testRegisterItemInstanceNotExist() {
        searchedItem = new ItemDTO(
                "004", // non-existing ID
                100);
        ;

        try {
            sale.registerItem(searchedItem, registryHandler);
            fail("Expected ItemHandlingException was not thrown.");
        } catch (ItemHandlingException e) {
            assertTrue(e.getMessage().contains(searchedItem.getItemId()),
                    "ItemHandlingException message should contain the searched item ID.");

            assertTrue(e.getItemThatCouldNotBeHandled().getItemId().equals(searchedItem.getItemId()),
                    "ItemHandlingException should contain the searched item.");

        } catch (Exception e) {
            fail("Expected ItemHandlingException was not thrown.");
        }
    }

    // Test for when searched item is null
    @Test
    public void testRegisterItemNull() {
        searchedItem = null;

        try {
            sale.registerItem(searchedItem, registryHandler);
            fail("Expected ItemHandlingException was not thrown.");
        } catch (NullPointerException e) {

        } catch (Exception e) {
            fail("Expected ItemHandlingException was not thrown.");
        }
    }

    // Test for when searched item exists in the list (they must exist)
    @Test
    public void testRegisterItemInstanceExists() {
        searchedItem = new ItemDTO(
                "003", // existing item ID
                5000);

        try {
            expectedItem = registryHandler.retrieveItemInfo(searchedItem);

            result = sale.registerItem(searchedItem, registryHandler);

            assertTrue(expectedItem.getItemId().equals(result.getItemId()),
                    "Searching for an existing item should return the correct item.");
        } catch (Exception e) {
            fail("Expected correct item not ItemNotFoundException.");
        }
    }

    @Test
    public void testRetrieveItemInfoExpectedDatabaseException() {
        searchedItem = new ItemDTO(
                "007", // existing item ID
                5000);

        try {
            result = sale.registerItem(searchedItem, registryHandler);

            fail("Expected correct item not DatabaseUnreachableException.");
        } catch (DatabaseUnreachableException e) {
            assertTrue(e.getMessage().contains("registering item"),
                    "DatabaseUnreachableException message should contain describing text.");
        } catch (Exception e) {
            fail("Expected correct item not DatabaseUnreachableException.");
        }
    }
}
