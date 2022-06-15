package com.example.commercial;

public class Product {
 String Img , Title ;

    public Product(String image, String title) {
        this.Img = image;
        this.Title = title;
    }

    public String getImage() {
        return Img;
    }

    public void setImage(String image) {
        this.Img = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public Product() {

    }
}
