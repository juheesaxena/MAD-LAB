package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    CheckBox pizza, burger, sandwich;
    Spinner drinks;
    Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        pizza = findViewById(R.id.pizza);
        burger = findViewById(R.id.burger);
        sandwich = findViewById(R.id.sandwich);
        drinks = findViewById(R.id.drinks);
        addToCart = findViewById(R.id.addToCart);

        // Spinner items
        String[] drinkItems = {"Select Drink", "Coffee - 50", "Coke - 40"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, drinkItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drinks.setAdapter(adapter);

        addToCart.setOnClickListener(v -> {

            int total = 0;
            String order = "";

            if(pizza.isChecked()) {
                order += "Pizza = 100\n";
                total += 100;
            }

            if(burger.isChecked()) {
                order += "Burger = 80\n";
                total += 80;
            }

            if(sandwich.isChecked()) {
                order += "Sandwich = 70\n";
                total += 70;
            }

            String selectedDrink = drinks.getSelectedItem().toString();

            if(selectedDrink.equals("Coffee - 50")) {
                order += "Coffee = 50\n";
                total += 50;
            }
            else if(selectedDrink.equals("Coke - 40")) {
                order += "Coke = 40\n";
                total += 40;
            }

            // No item selected check
            if(order.isEmpty()) {
                Toast.makeText(this, "Select at least one item", Toast.LENGTH_SHORT).show();
                return;
            }

            // Send data to CartActivity
            Intent i = new Intent(MenuActivity.this, CartActivity.class);
            i.putExtra("order", order);
            i.putExtra("total", total);

            startActivity(i);
        });
    }
}