package com.pluralsight.store;

public class Product {
    //Instance variables for product
    private String sku;
    private String productName;
    private double price;
    private String productDepartment;

    //Constructor for product
    public Product(String sku, String productName, double price, String productDepartment) {
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.productDepartment = productDepartment;
    }

    //Getters for product
    public String getSku() {
        return sku;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getProductDepartment() {
        return productDepartment;
    }
}
