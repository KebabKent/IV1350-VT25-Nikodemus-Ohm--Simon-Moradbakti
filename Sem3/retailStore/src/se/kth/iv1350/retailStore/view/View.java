package se.kth.iv1350.retailStore.view;

import se.kth.iv1350.retailStore.controller.Controller;

import se.kth.iv1350.retailStore.model.Payment;
import se.kth.iv1350.retailStore.model.ItemRegister;

import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

public class View {
    public Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Sample execution method to simulate a sale process.
     * Registers items, applies discounts, ends the sale, and processes payment.
     */
    public void sampleExecution() {
        controller.newSale();

        ItemDTO searchedItem = new ItemDTO(
                "001",
                null,
                null,
                null,
                null,
                1);

        ItemDTO foundItem = controller.registerItem(searchedItem);

        searchedItem = new ItemDTO(
                "003",
                null,
                null,
                null,
                null,
                1);

        foundItem = controller.registerItem(searchedItem);

        searchedItem = new ItemDTO(
                "001",
                null,
                null,
                null,
                null,
                100);

        foundItem = controller.registerItem(searchedItem);

        SaleDTO saleInfo = controller.fetchDiscount("123");
        System.out.println("Total price: " + saleInfo.returnTotalPrice());
        System.out.println("Discounted price: " + saleInfo.returnDiscountedPrice());
        System.out.println("Discounted percentage: " + saleInfo.returnDiscountedPercentage());

        searchedItem = new ItemDTO(
                "002",
                null,
                null,
                null,
                null,
                1337);

        foundItem = controller.registerItem(searchedItem);

        saleInfo = controller.endSale();
        System.out.println("Total price: " + saleInfo.returnTotalPrice());
        System.out.println("Discounted price: " + saleInfo.returnDiscountedPrice());
        System.out.println("Discounted percentage: " + saleInfo.returnDiscountedPercentage());

        AmountDTO paidAmount = new AmountDTO(25000);
        SaleDTO paymentInfo = controller.payForSale(paidAmount);

        System.out.println("Change as seen in view: " + paymentInfo.getChange().getAmount());
    }
}
