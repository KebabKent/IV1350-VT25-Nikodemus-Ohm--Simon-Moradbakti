package se.kth.iv1350.retailStore.dto;

/**
 * Represents an item with its attributes and quantity.
 * This class is used to transfer item data between different layers of the
 * application.
 */
public class ItemDTO {

    private final String itemId;

    private final String itemName;

    private final Float itemPrice;

    private final Float itemVAT;

    private final String itemDescription;

    private final int quantity;

    /**
     * 
     * Creates a new object with only @param itemId and @param quantity,
     * the rest are set as null, as they are typically not used for searching
     * 
     * @param itemId   The ID of the item.
     * @param quantity The quantity of the item.
     */
    public ItemDTO(String itemId, int quantity) {
        this.itemId = itemId;
        this.itemName = null;
        this.itemPrice = null;
        this.itemVAT = null;
        this.itemDescription = null;
        this.quantity = quantity;
    }

    /**
     * Creates a new object that describes an item with all its attributes.
     * 
     * @param itemId          The ID of the item.
     * @param itemName        The name of the item.
     * @param itemPrice       The price of the item excluding VAT.
     * @param itemVAT         The VAT of the item.
     * @param itemDescription A short description of the item.
     * @param quantity        The quantity of the item.
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
     * Creates a new copy of an existing ItemDTO object with a different quantity.
     * 
     * @param itemToCopy The object to be copied.
     * @param quantity   The quantity to be set for the new copy.
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
     * Creates a new copy of an existing ItemDTO object.
     * 
     * @param itemToCopy The object to be copied.
     */
    public ItemDTO(
            ItemDTO itemToCopy) {
        this.itemId = itemToCopy.getItemId();
        this.itemName = itemToCopy.getItemName();
        this.itemPrice = itemToCopy.getItemPrice();
        this.itemVAT = itemToCopy.getItemVAT();
        this.itemDescription = itemToCopy.getItemDescription();
        this.quantity = itemToCopy.getItemQuantity();
    }

    /**
     * Retrieves the item's ID.
     * 
     * @return The ID as a string.
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Retrieves the name of the item.
     * 
     * @return The name as a string.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Retrieves the price of the item excluding VAT.
     * 
     * @return The price as a float value.
     */
    public Float getItemPrice() {
        return itemPrice;
    }

    /**
     * Retrieves the VAT of the item.
     * 
     * @return The VAT rate as a float value.
     */
    public Float getItemVAT() {
        return itemVAT;
    }

    /**
     * Retrieves the description of the item.
     * 
     * @return A short description as a string.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Retrieves how many of the item are specified.
     * 
     * @return The quantity as an integer.
     */
    public int getItemQuantity() {
        return quantity;
    }
}