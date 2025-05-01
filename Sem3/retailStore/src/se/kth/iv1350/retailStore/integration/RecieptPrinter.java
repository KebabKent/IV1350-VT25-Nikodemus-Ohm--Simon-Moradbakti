package se.kth.iv1350.retailStore.integration;

import se.kth.iv1350.retailStore.util.ItemListHandler;

import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

public class RecieptPrinter {

	/**
	 * Prints the receipt for a completed sale. The receipt includes:
	 * The time of the sale and when it ended
	 * A list of items sold, with details such as ID, name, quantity, price, VAT,
	 * and total price for each item
	 * The total price, VAT, VAT percentage, discounted price, and discount
	 * percentage
	 * The amount paid by the customer and the change given back
	 *
	 * @param saleDTO The sale data object containing all information needed to
	 *                print the receipt.
	 */
	public static void printReciept(SaleDTO saleDTO) {
		System.out.println("********** Receipt **********");

		System.out.println("Time of sale: " + saleDTO.getSaleTime());
		System.out.println("Time of sale end: " + saleDTO.getSaleEndTime());

		System.out.println();

		ItemListHandler.printItemList(saleDTO.getItemList());

		System.out.println();

		System.out.println("Total price: " + saleDTO.returnTotalPrice());
		System.out.println("Total VAT: " + saleDTO.returnTotalVAT());
		System.out.println("Total VAT percentage: " +
				saleDTO.returnTotalVATPercentage());
		System.out.println("Discounted price: " +
				saleDTO.returnDiscountedPrice());
		System.out.println("Discounted percentage: " +
				saleDTO.returnDiscountedPercentage());

		System.out.println();

		System.out.println("Amount paid: " +
				saleDTO.getAmountPaid().getAmount());
		System.out.println("Change: " + saleDTO.getChange().getAmount());
	}

}
