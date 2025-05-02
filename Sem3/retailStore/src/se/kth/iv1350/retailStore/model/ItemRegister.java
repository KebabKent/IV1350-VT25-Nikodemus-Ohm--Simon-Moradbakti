package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.util.ItemListHandler;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemRegister {

    private List<ItemDTO> itemList;

    /**
     * Initializes a new item register with an empty list of items.
     * This constructor creates an empty list of items that can later be filled with
     * objects.
     */
    public ItemRegister() {
        this.itemList = new ArrayList<>();
    }

    /**
     * Searches for an item in the item list and returns its position in the list.
     * 
     * @param searchedItem The item to search for in the register.
     * @return The position of the item in the list.
     */
    public Integer findItem(ItemDTO searchedItem) {
        return ItemListHandler.searchItemDTOPosition(searchedItem, this.itemList);
    }

    /**
     * Updates the quantity of an item in the item list based on its position.
     * A new itemDTO is created with the updated quantity, and the item list is
     * modified according to it.
     * 
     * @param foundItemPosition The position of the item in the list to update.
     * @param quantity          The quantity to add to the item.
     * @return The updated itemDTO.
     */
    public ItemDTO updateItemQuantity(Integer foundItemPosition, int quantity) {

        ItemDTO foundItem = this.itemList.get(foundItemPosition);

        foundItem = new ItemDTO(foundItem, foundItem.getItemQuantity() + quantity);

        this.itemList.set(foundItemPosition, foundItem);

        return foundItem;
    }

    /**
     * Adds a new item to the item list with the specified quantity.
     * 
     * @param item     The item to add to the register.
     * @param quantity The quantity of the item to add.
     * @return The added item.
     */
    public ItemDTO addItem(ItemDTO item, int quantity) {
        item = new ItemDTO(item, quantity);
        this.itemList.add(item);
        return item;
    }

    /**
     * Returns the current list of items in the register.
     * 
     * @return The list of item DTOs in the register.
     */
    public List<ItemDTO> getItemList() {
        return itemList;
    }
}
