package com.example.test;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private String productId;
    private String productName;
    private double price;
    private float rating;
    private int imageResource;
    private String description;
    private String category;
    private boolean isBestSeller;
    private int discount;
    private int quantity = 1; // Default quantity for cart

    public Product(String productId, String productName, double price, float rating, int imageResource, String description, String category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.rating = rating;
        this.imageResource = imageResource;
        this.description = description;
        this.category = category;
        this.isBestSeller = false;
        this.discount = 0;
    }

    // Getters and Setters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public float getRating() { return rating; }
    public int getImageResource() { return imageResource; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public boolean isBestSeller() { return isBestSeller; }
    public void setBestSeller(boolean bestSeller) { isBestSeller = bestSeller; }
    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
