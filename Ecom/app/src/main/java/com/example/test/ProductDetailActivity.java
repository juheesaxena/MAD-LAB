package com.example.test;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.snackbar.Snackbar;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Toolbar toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Product product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            ImageView ivImage = findViewById(R.id.ivDetailImage);
            TextView tvName = findViewById(R.id.tvDetailName);
            TextView tvPrice = findViewById(R.id.tvDetailPrice);
            TextView tvDescription = findViewById(R.id.tvDetailDescription);
            RatingBar ratingBar = findViewById(R.id.detailRatingBar);
            Spinner spinnerQty = findViewById(R.id.spinnerQty);
            Button btnAddToCart = findViewById(R.id.btnDetailAddToCart);
            Button btnWishlist = findViewById(R.id.btnDetailWishlist);

            ivImage.setImageResource(product.getImageResource());
            tvName.setText(product.getProductName());
            tvPrice.setText(String.format("$%.2f", product.getPrice()));
            tvDescription.setText(product.getDescription());
            ratingBar.setRating(product.getRating());

            btnAddToCart.setOnClickListener(v -> {
                int quantity = Integer.parseInt(spinnerQty.getSelectedItem().toString());
                product.setQuantity(quantity);
                DataManager.addToCart(product);
                Snackbar.make(v, product.getProductName() + " added to cart", Snackbar.LENGTH_SHORT).show();
            });

            btnWishlist.setOnClickListener(v -> {
                DataManager.addToWishlist(product);
                Toast.makeText(this, "Added to Wishlist", Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
