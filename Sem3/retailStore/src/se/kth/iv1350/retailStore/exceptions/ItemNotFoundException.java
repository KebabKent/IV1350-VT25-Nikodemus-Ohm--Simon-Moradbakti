package se.kth.iv1350.retailStore.exceptions;

import se.kth.iv1350.retailStore.dto.ItemDTO;

/**
 * This exception is thrown when an item is not found in the inventory.
 * It extends the Exception class.
 */
public class ItemNotFoundException extends Exception {
    /**
     * The item that could not be handled during the operation.
     */
    private ItemDTO itemThatCouldNotBeFound;

    /**
     * Creates a new ItemNotFoundException with a message and cause.
     *
     * @param itemThatCouldNotBeFound The item that could not be found.
     */
    public ItemNotFoundException(ItemDTO itemThatCouldNotBeFound) {
        super("Item not found, Item ID: " +
                itemThatCouldNotBeFound.getItemId() +
                ". Item does not exist in the item list.");
        this.itemThatCouldNotBeFound = itemThatCouldNotBeFound;
    }

    /**
     * Gets the item that could not be found.
     *
     * @return The item that could not be found.
     */
    public ItemDTO getItemThatCouldNotBeFound() {
        return itemThatCouldNotBeFound;
    }
}
