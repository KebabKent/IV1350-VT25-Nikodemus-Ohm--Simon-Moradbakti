package se.kth.iv1350.retailStore.exceptions;

import se.kth.iv1350.retailStore.dto.ItemDTO;

/**
 * This exception is thrown when the database is unreachable.
 * It indicates that the system cannot access the database for some reason.
 */
public class OperationFailedException extends Exception {

    /**
     * Constructs a new OperationFailedException with a message
     * and cause.
     *
     * @param message The message.
     * @param cause   The cause of the exception.
     */
    public OperationFailedException(String message, Exception cause) {
        super("Operation failed because: " +
                message, cause);
    }
}
