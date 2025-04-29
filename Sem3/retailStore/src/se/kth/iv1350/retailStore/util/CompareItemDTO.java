package se.kth.iv1350.retailStore.util;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class CompareItemDTO {

    public static ItemDTO searchItemDTOInstance(ItemDTO searchedItem, List<ItemDTO> itemList) {
        for (ItemDTO registeredItem : itemList) {
            if (compareItemId(registeredItem, searchedItem)) {
                return registeredItem;
            }
        }
        return null;
    }

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

    public static ItemDTO copyItemDTOInfo(ItemDTO itemToCopy, int quantity) {
        return new ItemDTO(
                itemToCopy.getItemId(),
                itemToCopy.getItemName(),
                itemToCopy.getItemPrice(),
                itemToCopy.getItemVAT(),
                itemToCopy.getItemDescription(),
                quantity);
    }
}
