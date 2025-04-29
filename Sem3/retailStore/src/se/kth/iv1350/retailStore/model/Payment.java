package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.List;

public class Payment {

    private AmountDTO totalPrice;
    private AmountDTO discountedPrice;
    private float discountPercentage;

    public Payment() {
        this.totalPrice = new AmountDTO(0);
    }

    public void setTotalPrice(float totalPrice) { // kanske calculate total price. Loopar igenom alla items i sale
        this.totalPrice = new AmountDTO(totalPrice);
    }

    public AmountDTO calculateTotalPrice(List<ItemDTO> itemList) {
        float calculatedPrice = 0;
        for (ItemDTO item : itemList) {
            calculatedPrice += item.getItemPrice() * item.getItemQuantity();
        }

        totalPrice = new AmountDTO(calculatedPrice);

        return totalPrice;
    }

    public float returnTotalPrice() {
        return totalPrice.getAmount();
    }

    public AmountDTO payForSale(AmountDTO amount) {
        return null;
    }

    public void setDiscount(float discount) {
        this.discountPercentage = discount;
    }

    public AmountDTO calculateDiscountedPrice() {
        this.discountedPrice = new AmountDTO(
                totalPrice.getAmount() * (1 - discountPercentage / 100));
        return discountedPrice;
    }

    public float returnDiscountedPrice() {
        return discountedPrice.getAmount();
    }

    public float returnDiscountedPercentage() {
        return discountPercentage;
    }
}
