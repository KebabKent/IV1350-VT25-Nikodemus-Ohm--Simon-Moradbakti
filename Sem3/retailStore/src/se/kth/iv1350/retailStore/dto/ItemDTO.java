package se.kth.iv1350.retailStore.dto;

public class ItemDTO {

	private final String itemId;
	private final String itemName;
	private final Float itemPrice;
	private final Float itemVAT;
	private final String itemDescription;

	private final int quantity; // ta bort?

	/**
	 * Skapar ett nytt objekt som beskriver en vara med alla dess attribut.
	 * 
	 * @param itemId ID för varan.
	 * @param itemName Namn på varan.
	 * @param itemPrice Pris på varan exklusive moms.
	 * @param itemVAT Moms för varan.
	 * @param itemDescription Kort beskrivning av varan.
	 * @param quantity Antal av varan.
	 */
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

	/**
	 * Skapar en ny kopia av ett befintligt ItemDTO-objekt men med ett annat antal.
	 * 
	 * @param itemToCopy Det objekt som ska kopieras.
	 * @param quantity Antal som ska sättas för den nya kopian.
	 */
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

	/**
	 * Hämtar varans ID.
	 * 
	 * @return ID som en sträng.
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * Hämtar namnet på varan.
	 * 
	 * @return Namnet som en sträng.
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Hämtar priset för varan utan moms.
	 * 
	 * @return Priset som ett float-värde.
	 */
	public Float getItemPrice() {
		return itemPrice;
	}

	/**
	 * Hämtar momsen för varan.
	 * 
	 * @return Momssatsen som ett float-värde.
	 */
	public Float getItemVAT() {
		return itemVAT;
	}

	/**
	 * Hämtar beskrivningen av varan.
	 * 
	 * @return En kort beskrivning som en sträng.
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Hämtar hur många av varan som finns angivna.
	 * 
	 * @return Antalet som ett heltal.
	 */
	public int getItemQuantity() {
		return quantity;
	}

}
