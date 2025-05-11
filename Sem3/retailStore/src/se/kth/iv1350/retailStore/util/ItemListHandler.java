package se.kth.iv1350.retailStore.util;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * This class has custom util methods for handling lists of ItemDTO objects.
 * It has methods for searching for items in a list, printing items,
 * and printing an entire list of items.
 */

public class ItemListHandler {

    /**
     * Creates a new instance of ItemListHandler.
     * This instance does nothing.
     */
    public ItemListHandler() {
    }

    /**
     * Searches for a specific ItemDTO in the provided list by comparing their item
     * IDs.
     * 
     * @param searchedItem The item to search for.
     * @param itemList     The list of items to search through.
     * @return The ItemDTO if found, null if not found.
     */
    public static ItemDTO searchItemDTOInstance(ItemDTO searchedItem, List<ItemDTO> itemList) {
        for (ItemDTO registeredItem : itemList) {
            if (compareItemId(registeredItem, searchedItem)) {
                return registeredItem;
            }
        }
        return null;
    }

    private static boolean compareItemId(ItemDTO registeredItem, ItemDTO searchedItem) {
        return registeredItem.getItemId().equals(searchedItem.getItemId());
    }

    /**
     * 
     * prints a list of items to the console,
     * the index of a item is printed
     * 
     * @param itemList The list of items to print.
     * 
     */
    public static void printItemList(List<ItemDTO> itemList) {
        int i = 0;
        for (ItemDTO item : itemList) {
            System.out.println("    Item: " + i++);
            printItem(item);
            System.out.println();
        }
    }

    /**
     * 
     * Prints detailed information about a specific item to the console.
     * Includes ID, name, quantity, unit price, VAT, and total price for the
     * quantity.
     * 
     * @param item The item whose details should be printed.
     */
    public static void printItem(ItemDTO item) {
        System.out.println("       ID: " + item.getItemId() + "\n" +
                "       Name: " + item.getItemName() + "\n" +
                "       Quantity: " + item.getItemQuantity() + "\n" +
                "       Price: " + item.getItemPrice() + "\n" +
                "       VAT: " + item.getItemVAT() + "\n" +
                "       Price for quantity: " + (item.getItemPrice() * item.getItemQuantity()));
    }
}
