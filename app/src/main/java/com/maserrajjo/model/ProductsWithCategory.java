package com.maserrajjo.model;

import com.maserrajjo.database.model.Product;

import java.util.ArrayList;

public class ProductsWithCategory {
    String title;
    ArrayList<Product> productArrayList;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public ArrayList<Product> getItemsArrayList() {
        return productArrayList;
    }
    public void setItemsArrayList(ArrayList<Product> itemsArrayList) {
        this.productArrayList = itemsArrayList;
    }
}
