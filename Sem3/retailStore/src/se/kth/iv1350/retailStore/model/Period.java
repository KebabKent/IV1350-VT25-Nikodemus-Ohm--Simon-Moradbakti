package se.kth.iv1350.retailStore.model;

/**
 * The Period class holds the time period of a sale.
 */
public class Period {

    private java.time.LocalTime saleTime;
    private java.time.LocalTime saleEndTime;

    /**
     * Constructor that initializes the sale time to the current time.
     */
    public Period() {
        this.saleTime = java.time.LocalTime.now();
        this.saleEndTime = null;
    }

    /**
     * Sets the sale end time to the current time.
     */
    public void setEndTime() {
        this.saleEndTime = java.time.LocalTime.now();
    }

    /**
     * Returns the sale start time.
     * 
     * @return The sale start time as a LocalTime object.
     */
    public java.time.LocalTime getSaleTime() {
        return saleTime;
    }

    /**
     * Returns the sale end time.
     * 
     * @return The sale end time as a LocalTime object or null if not set.
     */
    public java.time.LocalTime getSaleEndTime() {
        return saleEndTime;
    }
}
