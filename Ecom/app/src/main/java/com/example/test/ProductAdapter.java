package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    private final Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getProductName());
        holder.tvPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.ratingBar.setRating(product.getRating());
        holder.ivProduct.setImageResource(product.getImageResource());

        if (product.isBestSeller()) {
            holder.tvBadge.setVisibility(View.VISIBLE);
        } else {
            holder.tvBadge.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product", product);
            context.startActivity(intent);
        });

        holder.btnAddToCart.setOnClickListener(v -> {
            DataManager.addToCart(product);
            Snackbar.make(v, product.getProductName() + " added to cart", Snackbar.LENGTH_SHORT)
                    .setAction("Undo", view -> DataManager.cartList.remove(product)).show();
        });

        holder.btnWishlist.setOnClickListener(v -> {
            DataManager.addToWishlist(product);
            Toast.makeText(context, "Added to Wishlist", Toast.LENGTH_SHORT).show();
        });

        holder.btnMore.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, v);
            popup.getMenuInflater().inflate(R.menu.menu_product_item, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.action_view_details) {
                    holder.itemView.performClick();
                    return true;
                } else if (id == R.id.action_add_to_cart) {
                    holder.btnAddToCart.performClick();
                    return true;
                } else if (id == R.id.action_add_to_wishlist) {
                    holder.btnWishlist.performClick();
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateList(List<Product> newList) {
        this.products = newList;
        notifyDataSetChanged();
    }

    public void sortLowToHigh() {
        Collections.sort(products, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        notifyDataSetChanged();
    }

    public void sortHighToLow() {
        Collections.sort(products, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        notifyDataSetChanged();
    }

    public void sortByRating() {
        Collections.sort(products, (p1, p2) -> Float.compare(p2.getRating(), p1.getRating()));
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvName, tvPrice, tvBadge;
        RatingBar ratingBar;
        Button btnAddToCart;
        ImageButton btnWishlist, btnMore;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
            tvBadge = itemView.findViewById(R.id.tvBadge);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
            btnWishlist = itemView.findViewById(R.id.btnWishlist);
            btnMore = itemView.findViewById(R.id.btnMore);
        }
    }
}
