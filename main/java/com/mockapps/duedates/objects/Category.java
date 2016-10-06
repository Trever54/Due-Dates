package com.mockapps.duedates.objects;

import android.graphics.Color;

import java.io.Serializable;

public class Category implements Serializable {

    private String categoryName;
    private int bgColor;
    private int textColor;

    public Category(String categoryName, int bgColor, int textColor) {
        this.categoryName = categoryName;
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setBgColor(int color) {
        this.bgColor = color;
    }

    public void setTextColor(int color) {
        this.textColor = color;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getBgColor() {
        return this.bgColor;
    }

    public int getTextColor() {
        return this.textColor;
    }

}
