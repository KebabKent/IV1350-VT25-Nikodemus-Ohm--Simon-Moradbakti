package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.dto.AmountDTO;

public class Payment {

    private AmountDTO totalPrice;

    public Payment() {
        this.totalPrice = new AmountDTO(0);
    }

    public void setTotalPrice(float totalPrice) { // kanske calculate total price. Loopar igenom alla items i sale
        this.totalPrice = new AmountDTO(totalPrice);
    }

    public float returnTotalPrice() {
        return totalPrice.getAmount();
    }

    public AmountDTO payForSale(AmountDTO amount) {
        return null;
    }

    public void setDiscountedPrice(float discountedPrice) {

    }
}
