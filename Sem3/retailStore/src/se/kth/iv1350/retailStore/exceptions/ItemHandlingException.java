package se.kth.iv1350.retailStore.exceptions;

import se.kth.iv1350.retailStore.dto.ItemDTO;

/**
 * This exception is thrown when an item is not found in the inventory.
 * It extends the Exception class.
 */
public class ItemHandlingException extends Exception {
    /**
     * The item that could not be handled during the operation.
     */
    private ItemDTO itemThatCouldNotBeHandled;

    /**
     * Creates a new ItemHandlingException with a message and cause.
     *
     * @param itemThatCouldNotBeHandled The item that could not be handled.
     * @param cause                     The cause of the exception.
     */
    public ItemHandlingException(ItemDTO itemThatCouldNotBeHandled, Exception cause) {
        super("Item could not be handled, Item ID: " +
                itemThatCouldNotBeHandled.getItemId() +
                ". Item was not possible to handle.", cause);
        this.itemThatCouldNotBeHandled = itemThatCouldNotBeHandled;
    }

    /**
     * Gets the item that could not be handled.
     *
     * @return The item that could not be handled.
     */
    public ItemDTO getItemThatCouldNotBeHandled() {
        return itemThatCouldNotBeHandled;
    }
}
