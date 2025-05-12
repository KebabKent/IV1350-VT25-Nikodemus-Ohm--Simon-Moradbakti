package se.kth.iv1350.retailStore.exceptions;

/**
 * This exception is thrown when an item is not found in the inventory.
 */
public class InventoryDatabaseException extends Exception {

    /**
     * Creates a new InventoryDatabaseException with a message.
     *
     * @param message The message.
     */
    public InventoryDatabaseException(String message) {
        super("Inventory register unreachable when: " +
                message);
    }
}
