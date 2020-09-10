package com.maserrajjo.model;

import java.io.Serializable;

public class Items implements Serializable {
    private String name;
    private String img;
    private String price;

    public Items(String name, String img, String price) {
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public Items() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
