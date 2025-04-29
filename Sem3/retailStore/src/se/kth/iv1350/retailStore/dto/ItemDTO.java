package se.kth.iv1350.retailStore.dto;

public class ItemDTO {

	private String itemId;
	private String itemName;
	private Float itemPrice;
	private Float itemVAT;
	private String itemDescription;

	private int quantity; // ta bort?

	public ItemDTO(
			String itemId,
			String itemName,
			Float itemPrice,
			Float itemVAT,
			String itemDescription,
			int quantity) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemVAT = itemVAT;
		this.itemDescription = itemDescription;

		this.quantity = quantity;
	}

	public String getItemId() {
		return itemId;
	}

}
