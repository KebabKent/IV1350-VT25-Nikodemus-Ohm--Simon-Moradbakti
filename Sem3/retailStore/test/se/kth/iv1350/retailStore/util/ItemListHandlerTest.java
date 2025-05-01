package se.kth.iv1350.retailStore.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemListHandlerTest {
    private List<ItemDTO> storedItems;
    private ItemDTO searchedItem;
    private ItemDTO expectedItem;
    private Integer expectedPosition;
    private ItemDTO result;
    private Integer resultPosition;

    @BeforeEach
    public void setUp() {
        storedItems = new ArrayList<>();

        ItemDTO apple = new ItemDTO(
                "001",
                "Apple",
                5.0f,
                0.12f,
                "Fresh red apple",
                1000);

        ItemDTO milk = new ItemDTO(
                "002",
                "Milk",
                15.0f,
                0.12f,
                "1-liter organic milk",
                2000);

        ItemDTO bread = new ItemDTO(
                "003",
                "Bread",
                25.0f,
                0.12f,
                "Whole grain bread loaf",
                5000);

        storedItems.add(apple);
        storedItems.add(milk);
        storedItems.add(bread);
    }

    @AfterEach
    public void tearDown() {
        storedItems = null;
    }

    // Test for searchItemDTOInctance method
    @Test
    public void testSearchItemDTOInstanceNull() {
        searchedItem = null;
        result = ItemListHandler.searchItemDTOInstance(searchedItem, storedItems);
        assertNull(result, "Searching for null should return null.");
    }

    @Test
    public void testSearchItemDTOInstanceNotExist() {
        searchedItem = new ItemDTO(
                "004",
                100);

        result = ItemListHandler.searchItemDTOInstance(searchedItem, storedItems);
        assertNull(result, "Searching for an item that does not exist should return null.");
    }

    @Test
    public void testSearchItemDTOInstanceExists() {
        searchedItem = new ItemDTO(
                "003",
                5000);

        expectedItem = storedItems.get(2);

        result = ItemListHandler.searchItemDTOInstance(searchedItem, storedItems);
        assertEquals(expectedItem, result, "Searching for an existing item should return the correct item.");
    }

    // Test for searchItemDTOPosition method
    @Test
    public void testSearchItemDTOPositionNull() {
        searchedItem = null;
        resultPosition = ItemListHandler.searchItemDTOPosition(searchedItem, storedItems);
        assertNull(resultPosition, "Searching for null should return null.");
    }

    @Test
    public void testSearchItemDTOPositionNotExist() {
        searchedItem = new ItemDTO(
                "004",
                100);

        resultPosition = ItemListHandler.searchItemDTOPosition(searchedItem, storedItems);
        assertNull(resultPosition, "Searching for an item that does not exist should return null.");
    }

    @Test
    public void testSearchItemDTOPositionExists() {
        searchedItem = new ItemDTO(
                "003",
                5000);

        expectedPosition = 2;

        resultPosition = ItemListHandler.searchItemDTOPosition(searchedItem, storedItems);
        assertEquals(expectedPosition, resultPosition,
                "Searching for an existing item should return the items index in the list.");
    }

    @Test
    public void testPrintItemListNull() {
        storedItems = null;
        assertThrows(NullPointerException.class, () -> {
            ItemListHandler.printItemList(storedItems);
        }, "Printing a null list should throw a NullPointerException.");
    }

    // @Test
    // public void testPrintItemListIllegalInput() {

    // List<Object> illegalInput = new ArrayList<>();
    // illegalInput.add(new Object());
    // illegalInput.add(new Object());
    // illegalInput.add(new Object());

    // assertThrows(IllegalArgumentException.class, () -> {
    // ItemListHandler.printItemList(illegalInput);
    // }, "Printing a null list should throw a .");

    // }

    @Test
    public void testPrintItemList() {
        ItemListHandler.printItemList(storedItems);
    }

}
