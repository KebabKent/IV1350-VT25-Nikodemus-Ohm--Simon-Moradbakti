package se.kth.iv1350.retailStore.dto;

import java.util.List;

import se.kth.iv1350.retailStore.model.ItemRegister;
import se.kth.iv1350.retailStore.model.Payment;
import se.kth.iv1350.retailStore.model.Period;
import se.kth.iv1350.retailStore.model.Sale;

public class SaleDTO {
	private final String saleID;

	private final List<ItemDTO> itemList;

	private final java.time.LocalTime saleTime;
	private final java.time.LocalTime saleEndTime;

	private final float totalPrice;
	private final float totalVAT;
	private final float totalVATPercentage;
	private final float discountedPrice;
	private final float discountPercentage;

	private final AmountDTO amountPaid;
	private final AmountDTO change;

	/**
	 * Creates a SaleDTO object that holds all relevant information about a sale.
	 *
	 * @param saleId       Unique ID for this sale.
	 * @param itemRegister Contains the list of all sold items.
	 * @param salePeriod   Includes the time when the sale started and ended.
	 * @param payment      Contains calculated price and VAT information.
	 */
	public SaleDTO(String saleId, ItemRegister itemRegister, Period salePeriod, Payment payment) {
		this.saleID = saleId;

		this.itemList = itemRegister.getItemList();

		this.saleTime = salePeriod.getSaleTime();
		this.saleEndTime = salePeriod.getSaleEndTime();

		this.totalPrice = payment.returnTotalPrice();
		this.totalVAT = payment.returnTotalVAT();
		this.totalVATPercentage = payment.returnTotalVATPercentage();
		this.discountedPrice = payment.returnDiscountedPrice();
		this.discountPercentage = payment.returnDiscountedPercentage();

		this.amountPaid = null;
		this.change = null;
	}

	/**
	 * Creates a new SaleDTO that includes payment details like amount paid and change.
	 *
	 * @param saleDTO  The existing sale data without payment info.
	 * @param payment  The payment that was made by the customer.
	 */
	public SaleDTO(SaleDTO saleDTO, Payment payment) {
		this.saleID = saleDTO.getSaleId();

		this.itemList = saleDTO.getItemList();

		this.saleTime = saleDTO.getSaleTime();
		this.saleEndTime = saleDTO.getSaleEndTime();

		this.totalPrice = saleDTO.returnTotalPrice();
		this.totalVAT = saleDTO.returnTotalVAT();
		this.totalVATPercentage = saleDTO.returnTotalVATPercentage();
		this.discountedPrice = saleDTO.returnDiscountedPrice();
		this.discountPercentage = saleDTO.returnDiscountedPercentage();

		this.amountPaid = payment.getAmountPaid();
		this.change = payment.getChange();
	}

	/**
	 * Gets the ID of this sale.
	 *
	 * @return The sale ID.
	 */
	public String getSaleId() {
		return saleID;
	}

	/**
	 * Gets the list of items that were sold in this sale.
	 *
	 * @return A list of ItemDTO objects.
	 */
	public List<ItemDTO> getItemList() {
		return itemList;
	}

	/**
	 * Gets the time when the sale started.
	 *
	 * @return The start time of the sale.
	 */
	public java.time.LocalTime getSaleTime() {
		return saleTime;
	}

	/**
	 * Gets the time when the sale ended.
	 *
	 * @return The end time of the sale.
	 */
	public java.time.LocalTime getSaleEndTime() {
		return saleEndTime;
	}

	/**
	 * Gets the total price before any discounts.
	 *
	 * @return The total price.
	 */
	public float returnTotalPrice() {
		return totalPrice;
	}

	/**
	 * Gets the total VAT (in currency, not percentage).
	 *
	 * @return Total VAT amount.
	 */
	public float returnTotalVAT() {
		return totalVAT;
	}

	/**
	 * Gets the VAT percentage used in this sale.
	 *
	 * @return The VAT percentage.
	 */
	public float returnTotalVATPercentage() {
		return totalVATPercentage;
	}

	/**
	 * Gets the final price after any discounts are applied.
	 *
	 * @return The discounted price.
	 */
	public float returnDiscountedPrice() {
		return discountedPrice;
	}

	/**
	 * Gets the discount percentage that was applied.
	 *
	 * @return The discount percentage.
	 */
	public float returnDiscountedPercentage() {
		return discountPercentage;
	}

	/**
	 * Gets the amount that the customer paid.
	 *
	 * @return An AmountDTO with the amount paid.
	 */
	public AmountDTO getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Gets the amount of change returned to the customer.
	 *
	 * @return An AmountDTO with the change.
	 */
	public AmountDTO getChange() {
		return change;
	}

}
