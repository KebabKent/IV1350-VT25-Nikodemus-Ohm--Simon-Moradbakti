package se.kth.iv1350.retailStore.util;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class CompareItemDTO {

    /**
     * Searches for a specific ItemDTO in the provided list by comparing their item IDs.
     * 
     * @param searchedItem The item to search for.
     * @param itemList The list of items to search through.
     * @return The ItemDTO if found, null if not found.
     */
    public static ItemDTO searchItemDTOInstance(ItemDTO searchedItem, List<ItemDTO> itemList) {
        for (ItemDTO registeredItem : itemList) {
            if (compareItemId(registeredItem, searchedItem)) {
                return registeredItem;
            }
        }
        return null;
    }

    /**
     * Searches for the position of a specific ItemDTO in the provided list by comparing their item IDs.
     * 
     * @param searchedItem The item to search for.
     * @param itemList The list of items to search through.
     * @return The index of the item if found, null if not found.
     */
    public static Integer searchItemDTOPosition(ItemDTO searchedItem, List<ItemDTO> itemList) {
        ItemDTO registeredItem;

        for (int i = 0; i < itemList.size(); i++) {
            registeredItem = itemList.get(i);
            if (compareItemId(registeredItem, searchedItem)) {
                return i;
            }
        }
        return null;
    }

 
    private static boolean compareItemId(ItemDTO registeredItem, ItemDTO searchedItem) {
        return registeredItem.getItemId().equals(searchedItem.getItemId());
    }
}
