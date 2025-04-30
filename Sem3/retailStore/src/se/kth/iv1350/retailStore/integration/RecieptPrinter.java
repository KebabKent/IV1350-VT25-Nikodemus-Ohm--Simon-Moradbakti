package se.kth.iv1350.retailStore.integration;

import se.kth.iv1350.retailStore.model.ItemRegister;
import se.kth.iv1350.retailStore.dto.ItemDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

public class RecieptPrinter {

	public static void printReciept(SaleDTO saleDTO) {
		System.out.println("********** Receipt **********");

		System.out.println("Time of sale: " + saleDTO.getSaleTime());
		System.out.println("Time of sale end: " + saleDTO.getSaleEndTime());

		System.out.println();

		System.out.println("Item list: \n" + saleDTO.getItemList().toString());

		int i = 0;
		for (ItemDTO item : saleDTO.getItemList()) {
			System.out.println("    Item: " + i++ + "\n" +
					"       ID: " + item.getItemId() + "\n" +
					"       Name: " + item.getItemName() + "\n" +
					"       Quantity: " + item.getItemQuantity() + "\n" +
					"       Price: " + item.getItemPrice() + "\n" +
					"       VAT: " + item.getItemVAT() + "\n" +
					"       Price for quantity: " + (item.getItemPrice() * item.getItemQuantity()) + "\n");
		}
		System.out.println();

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
