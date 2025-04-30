package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

public class CashRegister {

	private AmountDTO balance;

	public CashRegister() {
		this.balance = new AmountDTO(100000);
	}

	public void registerAmountPayed(SaleDTO saleInfo) {

		AmountDTO amountPaid = saleInfo.getAmountPaid();
		AmountDTO change = saleInfo.getChange();

		System.out.println("Cash register info:");
		System.out.println("Amount in (paid): " + amountPaid.getAmount());
		System.out.println("Amount out (change): " + change.getAmount());
		System.out.println("Current balance: " + this.balance.getAmount());

		this.balance = new AmountDTO(
				(this.balance.getAmount() +
						amountPaid.getAmount()) -
						change.getAmount());

		System.out.println("New balance: " + this.balance.getAmount());
	}

}
