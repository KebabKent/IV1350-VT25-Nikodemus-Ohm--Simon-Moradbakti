package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

public class CashRegister {

	private AmountDTO balance;

	/**
	 * Initializes a new cash register with a starting balance of 100000 units.
	 * The starting balance is set when the cash register is created.
	 */
	public CashRegister() {
		this.balance = new AmountDTO(100000);
	}

	/**
	 * Registers the amount paid by the customer and the change which is given back. 
	 * The cash register's balance is updated based on the amount paid and the change.
	 * This method prints details about the transaction, including the amount paid, the change and the current balance.
	 *
	 * @param saleInfo The sale data, containing the amount paid and change for the transaction.
         * "this.balance" Updates the cash register balance
	 */
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
