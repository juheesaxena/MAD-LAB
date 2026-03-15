package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<Product> cartItems;
    private final Context context;
    private final Runnable updateUI;

    public CartAdapter(List<Product> cartItems, Context context, Runnable updateUI) {
        this.cartItems = cartItems;
        this.context = context;
        this.updateUI = updateUI;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.tvName.setText(product.getProductName());
        holder.tvPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.ivProduct.setImageResource(product.getImageResource());
        holder.tvQty.setText(String.valueOf(product.getQuantity()));

        holder.btnAdd.setOnClickListener(v -> {
            product.setQuantity(product.getQuantity() + 1);
            holder.tvQty.setText(String.valueOf(product.getQuantity()));
            updateUI.run();
        });

        holder.btnRemoveQty.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                holder.tvQty.setText(String.valueOf(product.getQuantity()));
                updateUI.run();
            }
        });

        holder.btnRemove.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                DataManager.cartList.remove(currentPos);
                notifyItemRemoved(currentPos);
                notifyItemRangeChanged(currentPos, cartItems.size());
                updateUI.run();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvName, tvPrice, tvQty;
        ImageButton btnAdd, btnRemoveQty, btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivCartProduct);
            tvName = itemView.findViewById(R.id.tvCartProductName);
            tvPrice = itemView.findViewById(R.id.tvCartProductPrice);
            tvQty = itemView.findViewById(R.id.tvCartQty);
            btnAdd = itemView.findViewById(R.id.btnAddQty);
            btnRemoveQty = itemView.findViewById(R.id.btnRemoveQty);
            btnRemove = itemView.findViewById(R.id.btnRemoveFromCart);
        }
    }
}
