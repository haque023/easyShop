package com.maserrajjo.model;

import java.util.ArrayList;

public class ItemsWithCategory {
    String title;
    ArrayList<Items> itemsArrayList;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public ArrayList<Items> getItemsArrayList() {
        return itemsArrayList;
    }
    public void setItemsArrayList(ArrayList<Items> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
    }
}
