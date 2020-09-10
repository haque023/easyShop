package com.maserrajjo.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maserrajjo.R;
import com.maserrajjo.database.model.Product;
import com.maserrajjo.model.ProductsWithCategory;

import java.util.ArrayList;

public class MainParentAdapter extends RecyclerView.Adapter<MainParentAdapter.viewHolder> {

    Context context;
    ArrayList<ProductsWithCategory>productsWithCategories;

    AdapterListener adapterListener;
    public MainParentAdapter(Context context,AdapterListener adapterListener,ArrayList<ProductsWithCategory>productsWithCategories) {
        this.context = context;
        this.adapterListener=adapterListener;
        this.productsWithCategories=productsWithCategories;

    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.parent_recycle_shop,
                        parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ProductsWithCategory productsWithCategory =productsWithCategories.get(position);
        String title = productsWithCategory.getTitle();
        ArrayList<Product> singleItem = productsWithCategory.getItemsArrayList();
        holder.titleCategory.setText(title);
        MainChildAdapter mainChildAdapter = new MainChildAdapter
                (context,singleItem,adapterListener);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(mainChildAdapter);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"more",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println(productsWithCategories.size()+"xyzfs");

        return productsWithCategories.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;
        private TextView titleCategory;
        private Button btnMore;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.parentViewId);
            titleCategory = itemView.findViewById(R.id.textTitleCategory);
            btnMore = itemView.findViewById(R.id.moreCategory);
        }

    }
}
