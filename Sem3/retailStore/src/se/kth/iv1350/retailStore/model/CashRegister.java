package se.kth.iv1350.retailStore.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.retailStore.dto.AmountDTO;
import se.kth.iv1350.retailStore.dto.SaleDTO;

import se.kth.iv1350.retailStore.util.TotalRevenueFileOutput;
import se.kth.iv1350.retailStore.util.TotalRevenueObserver;

/**
 * Cash register keeps track of the current balance in the in store cash
 * regiser. It can register the amount paid by the customer and the change
 * given back.
 * The cash register is initialized with a starting balance of 100000 units.
 */
public class CashRegister {
	private AmountDTO balance;

	private List<TotalRevenueObserver> revenueObservers;

	/**
	 * Initializes a new cash register with a starting balance of 100000 units.
	 * The starting balance is set when the cash register is created.
	 */
	public CashRegister() {
		this.balance = new AmountDTO(100000);

		revenueObservers = new ArrayList<>();

		addRevenueObserver(new TotalRevenueFileOutput());

	}

	/**
	 * Adds a new observer to the list of observers that will be notified when
	 * revenue changes.
	 *
	 * @param observer The observer to be added.
	 */
	public void addRevenueObserver(TotalRevenueObserver observer) {
		revenueObservers.add(observer);
	}

	/**
	 * Registers the amount paid by the customer and the change which is given back.
	 * The cash register's balance is updated based on the amount paid and the
	 * change.
	 * This method prints details about the transaction, including the amount paid,
	 * the change and the current balance.
	 *
	 * @param saleInfo The sale data, containing the amount paid and change for the
	 *                 transaction.
	 *                 "this.balance" Updates the cash register balance
	 */
	public void registerAmountPayed(SaleDTO saleInfo) {

		AmountDTO amountPaid = saleInfo.getAmountPaid();
		AmountDTO change = saleInfo.getChange();

		System.out.println("*************Cash register*************");
		System.out.println("Amount in (paid): " + amountPaid.getAmount());
		System.out.println("Amount out (change): " + change.getAmount());
		System.out.println("Current balance: " + this.balance.getAmount());

		float totalEarning = amountPaid.getAmount() - change.getAmount();

		this.balance = new AmountDTO(
				this.balance.getAmount() +
						totalEarning);

		System.out.println("New balance: " + this.balance.getAmount());
		System.out.println();

		AmountDTO revenueFromSale = new AmountDTO(totalEarning);
		notifyRevenueObservers(revenueFromSale, saleInfo.getSaleTime());
	}

	private void notifyRevenueObservers(AmountDTO newRevenue, LocalTime saleTime) {
		for (TotalRevenueObserver observer : revenueObservers) {
			observer.newRevenue(newRevenue, saleTime);
		}
	}

}
