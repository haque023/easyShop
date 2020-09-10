package com.maserrajjo.database.model;

import java.io.Serializable;

public class Catagory implements Serializable {
    private int whichId;
    private String whichName;

    public Catagory(int whichId, String whichName) {
        this.whichId = whichId;
        this.whichName = whichName;
    }

    public Catagory() {
    }

    public int getWhichId() {
        return whichId;
    }

    public void setWhichId(int whichId) {
        this.whichId = whichId;
    }

    public String getWhichName() {
        return whichName;
    }

    public void setWhichName(String whichName) {
        this.whichName = whichName;
    }
}
