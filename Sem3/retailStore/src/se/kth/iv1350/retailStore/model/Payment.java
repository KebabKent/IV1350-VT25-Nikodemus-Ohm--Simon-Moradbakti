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

    /**
     * Constructor that initializes the total price as zero.
     * The Payment class is used to calculate and store the total price, VAT, discounts, and change for a sale.
     */
    public Payment() {
        this.totalPrice = new AmountDTO(0);
    }

    /**
     * Sets the total price for the payment.
     * 
     * @param totalPrice The total price of the sale, passed as a float.
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = new AmountDTO(totalPrice);
    }

    /**
     * Calculates the total price, VAT, and VAT percentage for the items in the sale.
     * Loops through each item to calculate the total price, VAT, and average VAT percentage.
     * 
     * @param itemList The list of items in the sale.
     * @return The total price as an AmountDTO.
     * the for-Loop iterates through all items and calculate the total price, VAT, and item quantity
     */
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

    /**
     * Returns the total price of the sale.
     * 
     * @return The total price as a float.
     */
    public float returnTotalPrice() {
        return totalPrice.getAmount();
    }

    /**
     * Returns the total VAT for the sale.
     * 
     * @return The total VAT as a float.
     */
    public float returnTotalVAT() {
        return totalVAT.getAmount();
    }

    /**
     * Returns the total VAT percentage for the sale.
     * 
     * @return The VAT percentage as a float.
     */
    public float returnTotalVATPercentage() {
        return totalVATPercentage.getAmount();
    }

    /**
     * Sets the discount percentage for the sale.
     * 
     * @param discount The discount percentage as a float.
     */
    public void setDiscount(float discount) {
        this.discountPercentage = discount;
    }

    /**
     * Calculates the discounted price based on the total price and the discount percentage.
     * 
     * @return The discounted price as an AmountDTO.
     */
    public AmountDTO calculateDiscountedPrice() {
        this.discountedPrice = new AmountDTO(
                totalPrice.getAmount() * (1 - discountPercentage / 100));
        return discountedPrice;
    }

    /**
     * Returns the discounted price of the sale.
     * 
     * @return The discounted price as a float.
     */
    public float returnDiscountedPrice() {
        return discountedPrice.getAmount();
    }

    /**
     * Returns the discount percentage.
     * 
     * @return The discount percentage as a float.
     */
    public float returnDiscountedPercentage() {
        return discountPercentage;
    }

    /**
     * Registers the amount paid by the customer.
     * 
     * @param amount The amount paid as an AmountDTO.
     */
    public void registerAmountPaid(AmountDTO amount) {
        this.amountPaid = amount;
    }

    /**
     * Calculates the change to be returned to the customer.
     * The change is calculated based on the amount paid and either the total price or the discounted price.
     *
     * If there's no discount, calculate change based on the total price
     * If there's a discount, calculate change based on the discounted price
     */
    public void calculateChange() {
        if (discountedPrice == null) {
            this.change = new AmountDTO(amountPaid.getAmount() - totalPrice.getAmount());
            return;
        }
        this.change = new AmountDTO(amountPaid.getAmount() - discountedPrice.getAmount());
        return;
    }

    /**
     * Returns the change to be returned to the customer.
     * 
     * @return The change as an AmountDTO.
     */
    public AmountDTO getChange() {
        return change;
    }

    /**
     * Returns the amount paid by the customer.
     * 
     * @return The amount paid as an AmountDTO.
     */
    public AmountDTO getAmountPaid() {
        return amountPaid;
    }
}
