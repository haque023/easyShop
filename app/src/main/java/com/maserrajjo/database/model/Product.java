package com.maserrajjo.database.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String whichId;
    private String price;
    private int whichCatagory;
    private String whichTitle;
    private String whichShortDescription;
    private String whichBigDescription;
    private String imageUrl;

    public Product() {
    }

    public Product(String whichId, String price, int whichCatagory, String whichTitle, String whichShortDescription, String whichBigDescription, String imageUrl) {
        this.whichId = whichId;
        this.price = price;
        this.whichCatagory = whichCatagory;
        this.whichTitle = whichTitle;
        this.whichShortDescription = whichShortDescription;
        this.whichBigDescription = whichBigDescription;
        this.imageUrl = imageUrl;
    }

    public String getWhichId() {
        return whichId;
    }

    public void setWhichId(String whichId) {
        this.whichId = whichId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getWhichCatagory() {
        return whichCatagory;
    }

    public void setWhichCatagory(int whichCatagory) {
        this.whichCatagory = whichCatagory;
    }

    public String getWhichTitle() {
        return whichTitle;
    }

    public void setWhichTitle(String whichTitle) {
        this.whichTitle = whichTitle;
    }

    public String getWhichShortDescription() {
        return whichShortDescription;
    }

    public void setWhichShortDescription(String whichShortDescription) {
        this.whichShortDescription = whichShortDescription;
    }

    public String getWhichBigDescription() {
        return whichBigDescription;
    }

    public void setWhichBigDescription(String whichBigDescription) {
        this.whichBigDescription = whichBigDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}