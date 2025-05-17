package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.util.ItemListHandler;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The ItemRegister class manages a list of items.
 * It allows adding new items, updating item quantities, and searching for items
 * in the list.
 */
public class ItemRegister implements Cloneable {

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
     * Searches for an item in the item list and returns its pointer in the list.
     * 
     * @param searchedItem The item to search for in the register.
     * @return The pointer of the item in the list.
     */
    public ItemDTO findItem(ItemDTO searchedItem) {
        return ItemListHandler.searchItemDTOInstance(searchedItem, this.itemList);
    }

    /**
     * Updates the quantity of an item in the item list based on its pointer.
     * A new itemDTO is created with the updated quantity, and the item list is
     * modified according to it.
     * 
     * @param foundItem The pointer of the item in the list to update.
     * @param quantity  The quantity to add to the item.
     * @return The updated itemDTO.
     */
    public ItemDTO updateItemQuantity(ItemDTO foundItem, int quantity) {
        foundItem = new ItemDTO(foundItem, foundItem.getItemQuantity() + quantity);
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
     * Creates a clone of the current item register and returns it.
     * 
     * @return clonedItemRegister is then returned.
     * 
     */
    @Override
    public ItemRegister clone() {
        ItemRegister clonedItemRegister = new ItemRegister();

        for (ItemDTO item : this.itemList) {
            clonedItemRegister.itemList.add(new ItemDTO(item));
        }
        return clonedItemRegister;
    }

    /**
     * Returns the item list in the register.
     * 
     * @return The registers item list.
     */
    public List<ItemDTO> getItemList() {
        return this.itemList;
    }
}
