package se.kth.iv1350.retailStore.view;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.retailStore.controller.Controller;

import se.kth.iv1350.retailStore.model.Payment;
import se.kth.iv1350.retailStore.util.ItemListHandler;
import se.kth.iv1350.retailStore.model.ItemRegister;

import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;
import se.kth.iv1350.retailStore.exceptions.DatabaseUnreachableException;
import se.kth.iv1350.retailStore.exceptions.ItemHandlingException;
import se.kth.iv1350.retailStore.exceptions.OperationFailedException;

import se.kth.iv1350.retailStore.util.FileLogger;

/**
 * The View displays information to the user and interacts with the Controller.
 * It registers items, applies discounts, and processeces payments.
 */
public class View {

        private Controller controller;

        private FileLogger logger;

        /**
         * Constructs a new View object with the specified controller.
         * This constructor initializes the view and assigns the provided controller for
         * handling the logic
         * and communication between the model and the view.
         *
         * @param controller The controller that manages the interaction with the model
         *                   and controls the flow of data.
         */
        public View(Controller controller, FileLogger logger) {
                this.controller = controller;
                this.logger = logger;
        }

        /**
         * Sample execution method to simulate a sale process.
         * Registers items, applies discounts, ends the sale, and processes payment.
         */
        public void sampleExecution() {
                System.out.println("View: New Sale.");
                System.out.println();
                controller.newSale();

                List<ItemDTO> itemList = new ArrayList<>();
                ItemDTO searchedItem = new ItemDTO(
                                "001",
                                1);
                itemList.add(searchedItem);

                searchedItem = new ItemDTO(
                                "003",
                                1);
                itemList.add(searchedItem);

                searchedItem = new ItemDTO(
                                "004",
                                1);
                itemList.add(searchedItem);

                searchedItem = new ItemDTO(
                                "001",
                                100);
                itemList.add(searchedItem);

                searchedItem = new ItemDTO(
                                "002",
                                1337);
                itemList.add(searchedItem);

                searchedItem = new ItemDTO(
                                "007",
                                1337);
                itemList.add(searchedItem);

                searchedItem = new ItemDTO(
                                "007",
                                50);
                itemList.add(searchedItem);

                searchedItem = null;
                itemList.add(searchedItem);

                ItemDTO foundItem;
                SaleDTO saleInfo;

                int i = 0;
                while (true) {
                        searchedItem = itemList.get(i);
                        System.out.println("View: Registering item " + (i + 1));

                        try {
                                foundItem = controller.registerItem(searchedItem);

                                saleInfo = controller.getSaleInfo();
                                ItemListHandler.printItem(foundItem);
                                printPaymentInfo(saleInfo);
                        } catch (ItemHandlingException ItmHandlExc) {
                                ErrorMessageHandler.showErrorMsg(
                                                "Item could not be registered. Please try again.");
                        } catch (DatabaseUnreachableException DbUnreachExc) {
                                ErrorMessageHandler.showErrorMsg(
                                                "Database could not be connected. Please try again later.");
                        } catch (Exception exc) {
                                logger.log(exc);
                                ErrorMessageHandler.showErrorMsg(
                                                "Unexpected error, something went wrong. Please try again.");
                        }

                        System.out.println();

                        i++;
                        if (i >= itemList.size()) {
                                break;
                        }
                }

                // Ending sale
                System.out.println("View: Ending sale");
                saleInfo = controller.endSale();
                printPaymentInfo(saleInfo);
                System.out.println();

                // Fetching discount
                System.out.println("View: Fetching discount for customer ID 123");
                saleInfo = controller.fetchDiscount("123");
                printPaymentInfo(saleInfo);
                System.out.println();

                // Processing payment
                System.out.println("View: Processing payment");
                AmountDTO paidAmount = new AmountDTO(25000);
                SaleDTO paymentInfo = controller.payForSale(paidAmount);

                System.out.println("Change as seen in view: " + paymentInfo.getChange().getAmount());
        }

        /**
         * Prints the payment information including total price, discounted price,
         * and discounted percentage.
         * 
         * @param saleInfo The SaleDTO containing the payment information.
         */
        public void printPaymentInfo(SaleDTO saleInfo) {
                System.out.println("Total price: " + saleInfo.returnTotalPrice());
                System.out.println("Discounted price: " + saleInfo.returnDiscountedPrice());
                System.out.println("Discounted percentage: " + saleInfo.returnDiscountedPercentage());
        }
}
