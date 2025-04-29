package se.kth.iv1350.retailStore.dto;

import se.kth.iv1350.retailStore.model.Sale;

public class SaleDTO {

	private java.time.LocalTime saleTime;

	private java.time.LocalTime saleEnd;

	private AmountDTO totalPrice;

	public SaleDTO(Sale sale) {
	}

}
