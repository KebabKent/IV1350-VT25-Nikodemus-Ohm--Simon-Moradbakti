package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.List;

public class Payment {

    private AmountDTO totalPrice;
    private AmountDTO totalVAT;
    private AmountDTO totalVATPercentage;
    private AmountDTO discountedPrice;
    private float discountPercentage;

    private AmountDTO amountPaid;
    private AmountDTO change;

    public Payment() {
        this.totalPrice = new AmountDTO(0);
    }

    public void setTotalPrice(float totalPrice) { // kanske calculate total price. Loopar igenom alla items i sale
        this.totalPrice = new AmountDTO(totalPrice);
    }

    public AmountDTO calculateTotalPrice(List<ItemDTO> itemList) {
        float calculatedPrice = 0;
        float calculatedVAT = 0;
        int itemQuantity = 0;

        for (ItemDTO item : itemList) {
            calculatedPrice += item.getItemPrice() * item.getItemQuantity();
            calculatedVAT += item.getItemVAT() * item.getItemQuantity();
            itemQuantity += item.getItemQuantity();
        }

        this.totalPrice = new AmountDTO(calculatedPrice);
        this.totalVATPercentage = new AmountDTO(calculatedVAT / itemQuantity);
        this.totalVAT = new AmountDTO(calculatedPrice * (calculatedVAT / itemQuantity));

        return this.totalPrice;
    }

    public float returnTotalPrice() {
        return totalPrice.getAmount();
    }

    public float returnTotalVAT() {
        return totalVAT.getAmount();
    }

    public float returnTotalVATPercentage() {
        return totalVATPercentage.getAmount();
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

    public void registerAmountPaid(AmountDTO amount) {
        this.amountPaid = amount;
    }

    public void calculateChange() {
        if (discountedPrice == null) {
            this.change = new AmountDTO(amountPaid.getAmount() - totalPrice.getAmount());
            return;
        }
        this.change = new AmountDTO(amountPaid.getAmount() - discountedPrice.getAmount());
        return;
    }

    public AmountDTO getChange() {
        return change;
    }

    public AmountDTO getAmountPaid() {
        return amountPaid;
    }
}
