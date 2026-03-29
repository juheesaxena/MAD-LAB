package com.example.food;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {

    TextView orderDetails;
    Button placeOrder;

    String order;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        orderDetails = findViewById(R.id.orderDetails);
        placeOrder = findViewById(R.id.placeOrder);

        // Receive data from MenuActivity
        Intent i = getIntent();
        order = i.getStringExtra("order");
        total = i.getIntExtra("total", 0);

        orderDetails.setText(order + "\nTotal = " + total);

        // Register context menu (long press)
        registerForContextMenu(orderDetails);

        placeOrder.setOnClickListener(v -> {

            if(orderDetails.getText().toString().equals("Order Cleared")) {
                Toast.makeText(this, "No items to place order", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent j = new Intent(CartActivity.this, BillActivity.class);
            j.putExtra("order", order);
            j.putExtra("total", total);
            startActivity(j);
        });
    }

    // CREATE CONTEXT MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Options");
        menu.add("Clear Order");
    }

    // HANDLE CONTEXT MENU CLICK
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getTitle().equals("Clear Order")) {

            orderDetails.setText("Order Cleared");
            total = 0;

            Toast.makeText(this, "Order Cleared", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}