package se.kth.iv1350.retailStore.util;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class CompareItemDTO {

    public static ItemDTO searchItemDto(ItemDTO searchedItem, List<ItemDTO> itemList) {
        for (ItemDTO registeredItem : itemList) {
            if (compareItemId(registeredItem, searchedItem)) {
                return registeredItem;
            }
        }
        return null;
    }

    private static boolean compareItemId(ItemDTO registeredItem, ItemDTO searchedItem) {
        return registeredItem.getItemId().equals(searchedItem.getItemId());
    }
}
