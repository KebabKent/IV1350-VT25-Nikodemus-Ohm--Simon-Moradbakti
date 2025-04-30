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
	 * Skapar ett SaleDTO-objekt som innehåller information om försäljningen.
	 * Används t.ex. för kvitto eller loggning innan betalning har gjorts.
	 * 
	 * @param saleId       Unikt ID för försäljningen.
	 * @param itemRegister Innehåller listan med sålda varor.
	 * @param salePeriod   Tidpunkter för när försäljningen startade och avslutades.
	 * @param payment      Innehåller betalningsinformation (delvis används här).
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
	 * Skapar en ny version av SaleDTO efter att betalning har skett.
	 * Lägger till info om betalt belopp och växel.
	 * 
	 * @param saleDTO Tidigare försäljningsinfo utan betalningsdata.
	 * @param payment Objekt som innehåller betalningsdetaljer.
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
	 * Returnerar försäljningens ID.
	 * 
	 * @return ID som en sträng.
	 */
	public String getSaleId() {
		return saleID;
	}

	/**
	 * Returnerar listan med sålda varor.
	 * 
	 * @return En lista med ItemDTO-objekt.
	 */
	public List<ItemDTO> getItemList() {
		return itemList;
	}

	/**
	 * Returnerar tiden då försäljningen började.
	 * 
	 * @return Starttid för försäljningen.
	 */
	public java.time.LocalTime getSaleTime() {
		return saleTime;
	}

	/**
	 * Returnerar tiden då försäljningen avslutades.
	 * 
	 * @return Sluttid för försäljningen.
	 */
	public java.time.LocalTime getSaleEndTime() {
		return saleEndTime;
	}

	/**
	 * Returnerar totalpriset för försäljningen före rabatt.
	 * 
	 * @return Totalpriset som float.
	 */
	public float returnTotalPrice() {
		return totalPrice;
	}

	/**
	 * Returnerar total moms i kronor för försäljningen.
	 * 
	 * @return Momsen som float.
	 */
	public float returnTotalVAT() {
		return totalVAT;
	}

	/**
	 * Returnerar momssatsen i procent.
	 * 
	 * @return Momssats som float.
	 */
	public float returnTotalVATPercentage() {
		return totalVATPercentage;
	}

	/**
	 * Returnerar priset efter eventuell rabatt har dragits av.
	 * 
	 * @return Pris efter rabatt.
	 */
	public float returnDiscountedPrice() {
		return discountedPrice;
	}

	/**
	 * Returnerar hur stor rabatt som har getts i procent.
	 * 
	 * @return Rabattsats som float.
	 */
	public float returnDiscountedPercentage() {
		return discountPercentage;
	}

	/**
	 * Returnerar summan som kunden betalade.
	 * 
	 * @return AmountDTO med betalt belopp.
	 */
	public AmountDTO getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Returnerar hur mycket växel kunden fick tillbaka.
	 * 
	 * @return AmountDTO med växelbelopp.
	 */
	public AmountDTO getChange() {
		return change;
	}

}
