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

	public String getSaleId() {
		return saleID;
	}

	public List<ItemDTO> getItemList() {
		return itemList;
	}

	public java.time.LocalTime getSaleTime() {
		return saleTime;
	}

	public java.time.LocalTime getSaleEndTime() {
		return saleEndTime;
	}

	public float returnTotalPrice() {
		return totalPrice;
	}

	public float returnTotalVAT() {
		return totalVAT;
	}

	public float returnTotalVATPercentage() {
		return totalVATPercentage;
	}

	public float returnDiscountedPrice() {
		return discountedPrice;
	}

	public float returnDiscountedPercentage() {
		return discountPercentage;
	}

	public AmountDTO getAmountPaid() {
		return amountPaid;
	}

	public AmountDTO getChange() {
		return change;
	}

}
