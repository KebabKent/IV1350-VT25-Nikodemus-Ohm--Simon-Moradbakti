package se.kth.iv1350.retailStore.model;

public class Period {

    private java.time.LocalTime saleTime;
    private java.time.LocalTime saleEndTime;

    public Period() {
        this.saleTime = java.time.LocalTime.now();
        this.saleEndTime = null;
    }

    public void setEndTime() {
        this.saleEndTime = java.time.LocalTime.now();
    }

    public java.time.LocalTime getSaleTime() {
        return saleTime;
    }

    public java.time.LocalTime getSaleEndTime() {
        return saleEndTime;
    }
}
