package com.example.commercial.Model;

public class Category {
    private String  category_title;
    private int category_image , category_id;

    public Category(int category_image,  String category_title , int category_id) {
        this.category_title = category_title;
        this.category_id = category_id;
        this.category_image = category_image;
    }

    public Category(int category_image, String category_title) {
        this.category_image = category_image;
        this.category_title = category_title;
    }

    public int getCategory_image() {
        return category_image;
    }

    public void setCategory_image(int category_image) {
        this.category_image = category_image;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}