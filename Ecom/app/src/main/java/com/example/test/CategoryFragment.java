package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private String category;
    private ProductAdapter adapter;
    private List<Product> categoryProducts;
    private List<Product> displayedProducts;
    private int currentMaxPrice = 2000;

    public static CategoryFragment newInstance(String category) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("category");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProducts);
        SeekBar seekBar = view.findViewById(R.id.seekBarPrice);
        TextView tvPriceLabel = view.findViewById(R.id.tvPriceLabel);
        ImageButton btnSort = view.findViewById(R.id.btnSort);
        SwipeRefreshLayout swipeRefresh = view.findViewById(R.id.swipeRefresh);

        categoryProducts = DataManager.getProductsByCategory(category);
        displayedProducts = new ArrayList<>(categoryProducts);

        adapter = new ProductAdapter(displayedProducts, getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentMaxPrice = progress;
                tvPriceLabel.setText("$" + progress);
                applyFilters();
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnSort.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(getContext(), v);
            popup.getMenu().add("Price Low to High");
            popup.getMenu().add("Price High to Low");
            popup.getMenu().add("Highest Rating");
            popup.setOnMenuItemClickListener(item -> {
                String title = item.getTitle().toString();
                if (title.equals("Price Low to High")) adapter.sortLowToHigh();
                else if (title.equals("Price High to Low")) adapter.sortHighToLow();
                else if (title.equals("Highest Rating")) adapter.sortByRating();
                return true;
            });
            popup.show();
        });

        swipeRefresh.setOnRefreshListener(() -> {
            // Re-fetch or refresh data
            categoryProducts = DataManager.getProductsByCategory(category);
            applyFilters();
            swipeRefresh.setRefreshing(false);
            Toast.makeText(getContext(), "List Refreshed", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void applyFilters() {
        displayedProducts.clear();
        for (Product p : categoryProducts) {
            if (p.getPrice() <= currentMaxPrice) {
                displayedProducts.add(p);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void searchProducts(String query) {
        if (adapter == null) return;
        List<Product> searchResults = new ArrayList<>();
        for (Product p : categoryProducts) {
            if (p.getProductName().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(p);
            }
        }
        adapter.updateList(searchResults);
    }
}
