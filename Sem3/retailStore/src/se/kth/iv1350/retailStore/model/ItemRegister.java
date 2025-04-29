package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.util.CompareItemDTO;

import se.kth.iv1350.retailStore.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemRegister {

    private List<ItemDTO> itemList;

    public ItemRegister() {
        this.itemList = new ArrayList<>();
    }

    public Integer findItem(ItemDTO searchedItem) {
        return CompareItemDTO.searchItemDTOPosition(searchedItem, this.itemList);
    }

    public ItemDTO updateItemDTO(Integer foundItemPosition, int quantity) {

        ItemDTO foundItem = this.itemList.get(foundItemPosition);

        foundItem = CompareItemDTO.copyItemDTOInfo(
                foundItem,
                foundItem.getItemQuantity() + quantity);

        this.itemList.set(foundItemPosition, foundItem);
        printItemList();

        return foundItem;
    }

    public void addItem(ItemDTO item, int quantity) {
        item = CompareItemDTO.copyItemDTOInfo(
                item,
                quantity);

        this.itemList.add(item);
        printItemList();
    }

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    public void printItemList() {
        System.out.println("Item list:");
        int i = 0;
        for (ItemDTO item : itemList) {
            System.out.println("    Item: " + i++ + "\n" +
                    "       ID: " + item.getItemId() + "\n" +
                    "       Name: " + item.getItemName() + "\n" +
                    "       Quantity: " + item.getItemQuantity() + "\n" +
                    "       Item price: " + item.getItemPrice() + "\n" +
                    "       Price for quantity: " + (item.getItemPrice() * item.getItemQuantity()) + "\n");
        }
        System.out.println();
    }

}
