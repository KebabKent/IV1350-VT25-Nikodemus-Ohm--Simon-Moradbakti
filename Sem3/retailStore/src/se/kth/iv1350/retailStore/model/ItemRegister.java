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

        foundItem = new ItemDTO(foundItem, foundItem.getItemQuantity() + quantity);

        this.itemList.set(foundItemPosition, foundItem);
        printItemList();

        return foundItem;
    }

    public void addItem(ItemDTO item, int quantity) {
        item = new ItemDTO(item, quantity);

        this.itemList.add(item);
        printItemList();
    }

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    public void printItemList() {
        System.out.println("Item list:");
        printItemList(this.itemList);
    }

    public static void printItemList(List<ItemDTO> itemList) {
        int i = 0;
        for (ItemDTO item : itemList) {
            System.out.println("    Item: " + i++ + "\n" +
                    "       ID: " + item.getItemId() + "\n" +
                    "       Name: " + item.getItemName() + "\n" +
                    "       Quantity: " + item.getItemQuantity() + "\n" +
                    "       Price: " + item.getItemPrice() + "\n" +
                    "       VAT: " + item.getItemVAT() + "\n" +
                    "       Price for quantity: " + (item.getItemPrice() * item.getItemQuantity()) + "\n");
        }
        System.out.println();
    }

}
