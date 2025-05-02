package se.kth.iv1350.retailStore.dto;

/**
 * This class represents a monetary amount.
 * It stores a single amount value (e.g., total price, payment amount, change)
 * as a float.
 */
public class AmountDTO {

    private final float amount;

    /**
     * Creates a new object that contains some amount.
     * 
     * @param amount The amount to be stored.
     */
    public AmountDTO(float amount) {
        this.amount = amount;
    }

    /**
     * Returns the stored amount.
     * 
     * @return The amount as a float value.
     */
    public float getAmount() {
        return amount;
    }

}
