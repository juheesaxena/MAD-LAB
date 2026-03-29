package com.example.food;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class BillActivity extends AppCompatActivity {

    TextView billDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        billDetails = findViewById(R.id.billDetails);

        // Receive data
        Intent i = getIntent();
        String order = i.getStringExtra("order");
        int total = i.getIntExtra("total", 0);

        // Display nicely
        String finalBill = "----- BILL -----\n\n" +
                order +
                "\n----------------\n" +
                "TOTAL = " + total;

        billDetails.setText(finalBill);

        Toast.makeText(this, "Order Placed Successfully!", Toast.LENGTH_SHORT).show();
    }
}
