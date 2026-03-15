package com.example.test;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static List<Product> productList = new ArrayList<>();
    public static List<Product> cartList = new ArrayList<>();
    public static List<Product> wishlistList = new ArrayList<>();
    
    public static final String[] categories = {"Electronics", "Clothing", "Shoes", "Books", "Home Appliances"};

    static {
        // Electronics (6 products)
        productList.add(new Product("E1", "Smartphone X", 799.99, 4.5f, android.R.drawable.ic_menu_call, "Latest smartphone with OLED display.", "Electronics"));
        productList.get(0).setBestSeller(true);
        productList.add(new Product("E2", "Laptop Pro", 1299.99, 4.8f, android.R.drawable.ic_menu_gallery, "Powerful laptop for professionals.", "Electronics"));
        productList.add(new Product("E3", "Smart Watch", 199.99, 4.3f, android.R.drawable.ic_menu_recent_history, "Stay connected on your wrist.", "Electronics"));
        productList.add(new Product("E4", "Bluetooth Headphones", 149.99, 4.6f, android.R.drawable.ic_lock_silent_mode_off, "Noise cancelling over-ear headphones.", "Electronics"));
        productList.add(new Product("E5", "Tablet Air", 499.00, 4.4f, android.R.drawable.ic_menu_slideshow, "Light and powerful tablet for creativity.", "Electronics"));
        productList.add(new Product("E6", "Gaming Console", 499.99, 4.7f, android.R.drawable.ic_menu_compass, "Next-gen gaming experience.", "Electronics"));
        
        // Clothing (6 products)
        productList.add(new Product("C1", "Denim Jacket", 59.99, 4.2f, android.R.drawable.ic_menu_view, "Stylish blue denim jacket.", "Clothing"));
        productList.add(new Product("C2", "Cotton T-Shirt", 19.99, 4.0f, android.R.drawable.ic_menu_edit, "Comfortable everyday wear.", "Clothing"));
        productList.add(new Product("C3", "Winter Hoodie", 45.00, 4.5f, android.R.drawable.ic_menu_my_calendar, "Warm hoodie for chilly days.", "Clothing"));
        productList.add(new Product("C4", "Formal Shirt", 35.00, 4.1f, android.R.drawable.ic_menu_save, "Perfect for office and events.", "Clothing"));
        productList.add(new Product("C5", "Summer Dress", 55.00, 4.4f, android.R.drawable.ic_menu_camera, "Flowy dress for sunny weather.", "Clothing"));
        productList.add(new Product("C6", "Sport Shorts", 25.00, 4.0f, android.R.drawable.ic_menu_directions, "Breathable fabric for workouts.", "Clothing"));

        // Shoes (6 products)
        productList.add(new Product("S1", "Running Shoes", 89.99, 4.6f, android.R.drawable.ic_menu_directions, "Lightweight shoes for athletes.", "Shoes"));
        productList.add(new Product("S2", "Leather Boots", 110.00, 4.5f, android.R.drawable.ic_menu_myplaces, "Durable and stylish boots.", "Shoes"));
        productList.add(new Product("S3", "Casual Sneakers", 65.00, 4.3f, android.R.drawable.ic_menu_agenda, "Comfortable everyday sneakers.", "Shoes"));
        productList.add(new Product("S4", "Formal Loafers", 95.00, 4.2f, android.R.drawable.ic_menu_send, "Elegant shoes for special occasions.", "Shoes"));
        productList.add(new Product("S5", "Hiking Shoes", 125.00, 4.7f, android.R.drawable.ic_menu_compass, "Grip and support for rugged trails.", "Shoes"));
        productList.add(new Product("S6", "Flip Flops", 15.00, 4.0f, android.R.drawable.ic_menu_day, "Easy wear for beach days.", "Shoes"));

        // Books (6 products)
        productList.add(new Product("B1", "Android Dev Guide", 49.99, 4.9f, android.R.drawable.ic_menu_info_details, "Comprehensive guide to Android.", "Books"));
        productList.add(new Product("B2", "Java Programming", 55.00, 4.8f, android.R.drawable.ic_menu_sort_alphabetically, "Master Java with this deep dive.", "Books"));
        productList.add(new Product("B3", "Mystery Novel", 15.00, 4.4f, android.R.drawable.ic_menu_search, "A thrilling whodunit story.", "Books"));
        productList.add(new Product("B4", "Cooking Basics", 29.00, 4.6f, android.R.drawable.ic_menu_today, "Essential recipes for beginners.", "Books"));
        productList.add(new Product("B5", "Science Fiction", 18.50, 4.5f, android.R.drawable.ic_menu_slideshow, "Journey into the unknown universe.", "Books"));
        productList.add(new Product("B6", "History of Tech", 32.00, 4.7f, android.R.drawable.ic_menu_recent_history, "The story of modern computing.", "Books"));

        // Home Appliances (6 products)
        productList.add(new Product("H1", "Air Fryer", 120.00, 4.7f, android.R.drawable.ic_menu_camera, "Healthy cooking made easy.", "Home Appliances"));
        productList.add(new Product("H2", "Coffee Maker", 85.00, 4.6f, android.R.drawable.ic_menu_recent_history, "Brew the perfect cup every morning.", "Home Appliances"));
        productList.add(new Product("H3", "Vacuum Cleaner", 150.00, 4.4f, android.R.drawable.ic_menu_manage, "Keep your home spotless effortlessly.", "Home Appliances"));
        productList.add(new Product("H4", "Microwave Oven", 200.00, 4.5f, android.R.drawable.ic_menu_month, "Quick and efficient heating.", "Home Appliances"));
        productList.add(new Product("H5", "Electric Kettle", 30.00, 4.3f, android.R.drawable.ic_menu_add, "Boil water in minutes.", "Home Appliances"));
        productList.add(new Product("H6", "Toaster", 25.00, 4.2f, android.R.drawable.ic_menu_day, "Perfectly browned bread every time.", "Home Appliances"));
    }

    public static List<Product> getProductsByCategory(String category) {
        List<Product> filtered = new ArrayList<>();
        for (Product p : productList) {
            if (p.getCategory().equals(category)) {
                filtered.add(p);
            }
        }
        return filtered;
    }

    public static void addToCart(Product product) {
        // If product already in cart, increment quantity instead of adding new entry
        int index = -1;
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getProductId().equals(product.getProductId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Product p = cartList.get(index);
            p.setQuantity(p.getQuantity() + product.getQuantity());
        } else {
            // Create a copy of the product to avoid shared quantity state between detail and cart
            Product newProduct = new Product(product.getProductId(), product.getProductName(), product.getPrice(), 
                    product.getRating(), product.getImageResource(), product.getDescription(), product.getCategory());
            newProduct.setQuantity(product.getQuantity());
            cartList.add(newProduct);
        }
        // Reset original product quantity to default for next use
        product.setQuantity(1);
    }

    public static void addToWishlist(Product product) {
        boolean exists = false;
        for (Product p : wishlistList) {
            if (p.getProductId().equals(product.getProductId())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            wishlistList.add(product);
        }
    }

    public static double getCartTotal() {
        double total = 0;
        for (Product p : cartList) {
            total += p.getPrice() * p.getQuantity();
        }
        return total;
    }
}
