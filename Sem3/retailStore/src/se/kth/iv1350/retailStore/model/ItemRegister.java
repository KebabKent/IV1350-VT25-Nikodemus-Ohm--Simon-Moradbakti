package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import se.kth.iv1350.retailStore.util.CompareItemDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemRegister {

    private List<ItemDTO> itemList;

    public ItemRegister() {
        this.itemList = new ArrayList<>();
    }

    public void addItem(ItemDTO item) {
        itemList.add(item);
    }

    public ItemDTO findItem(ItemDTO searchedItem) {
        return CompareItemDTO.searchItemDto(searchedItem, itemList);
    }
}
