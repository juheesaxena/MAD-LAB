package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnWishlist = findViewById(R.id.btnWishlistProfile);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnOrderHistory = findViewById(R.id.btnOrderHistory);

        btnWishlist.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, WishlistActivity.class));
        });

        btnOrderHistory.setOnClickListener(v -> {
            Toast.makeText(this, "Order History coming soon!", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
            // In a real app, clear session here. Redirecting to start:
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
