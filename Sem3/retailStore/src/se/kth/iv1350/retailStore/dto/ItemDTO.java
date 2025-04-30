package se.kth.iv1350.retailStore.dto;

public class ItemDTO {

	private final String itemId;
	private final String itemName;
	private final Float itemPrice;
	private final Float itemVAT;
	private final String itemDescription;

	private final int quantity; // ta bort?

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

	public ItemDTO(
			ItemDTO itemToCopy,
			int quantity) {
		this.itemId = itemToCopy.getItemId();
		this.itemName = itemToCopy.getItemName();
		this.itemPrice = itemToCopy.getItemPrice();
		this.itemVAT = itemToCopy.getItemVAT();
		this.itemDescription = itemToCopy.getItemDescription();
		this.quantity = quantity;
	}

	public String getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public Float getItemPrice() {
		return itemPrice;
	}

	public Float getItemVAT() {
		return itemVAT;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public int getItemQuantity() {
		return quantity;
	}

}
