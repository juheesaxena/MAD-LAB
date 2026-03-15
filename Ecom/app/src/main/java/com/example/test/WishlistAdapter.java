package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private final List<Product> wishlistItems;
    private final Context context;
    private final Runnable updateUI;

    public WishlistAdapter(List<Product> wishlistItems, Context context, Runnable updateUI) {
        this.wishlistItems = wishlistItems;
        this.context = context;
        this.updateUI = updateUI;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        Product product = wishlistItems.get(position);
        holder.tvName.setText(product.getProductName());
        holder.tvPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.ivProduct.setImageResource(product.getImageResource());

        if (holder.layoutQty != null) {
            holder.layoutQty.setVisibility(View.GONE);
        }
        
        holder.itemView.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                DataManager.addToCart(product);
                DataManager.wishlistList.remove(currentPos);
                notifyItemRemoved(currentPos);
                updateUI.run();
                Toast.makeText(context, "Moved to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnRemove.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                DataManager.wishlistList.remove(currentPos);
                notifyItemRemoved(currentPos);
                updateUI.run();
                Toast.makeText(context, "Removed from Wishlist", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }

    public static class WishlistViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvName, tvPrice;
        View layoutQty;
        ImageButton btnRemove;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivCartProduct);
            tvName = itemView.findViewById(R.id.tvCartProductName);
            tvPrice = itemView.findViewById(R.id.tvCartProductPrice);
            View qtyView = itemView.findViewById(R.id.tvCartQty);
            if (qtyView != null) {
                layoutQty = (View) qtyView.getParent();
            }
            btnRemove = itemView.findViewById(R.id.btnRemoveFromCart);
        }
    }
}
