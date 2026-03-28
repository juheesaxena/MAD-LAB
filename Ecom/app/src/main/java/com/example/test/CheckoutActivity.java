package com.example.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        EditText etName = findViewById(R.id.etCheckoutName);
        EditText etAddress = findViewById(R.id.etCheckoutAddress);
        EditText etPhone = findViewById(R.id.etCheckoutPhone);
        TextView tvTotal = findViewById(R.id.tvCheckoutTotal);
        Button btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        tvTotal.setText(String.format("Total Amount: $%.2f", DataManager.getCartTotal()));

        btnPlaceOrder.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String address = etAddress.getText().toString();
            String phone = etPhone.getText().toString();

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Order Placed Successfully!", Toast.LENGTH_LONG).show();
                DataManager.cartList.clear();
                finish();
            }
        });
    }
}
