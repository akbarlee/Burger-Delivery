package com.example.commercial.Model;

public class Product {
  private String image , title , product_id;

    public Product(String image, String title) {
        this.image = image;
        this.title = title;
    }



    public Product(String image, String title, String product_id) {
        this.image = image;
        this.title = title;
        this.product_id = product_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product() {

    }
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
