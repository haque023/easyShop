package com.maserrajjo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maserrajjo.R;
import com.maserrajjo.database.model.Product;
import com.maserrajjo.main.MainActivity;
import com.maserrajjo.main.ProductView;
import com.maserrajjo.model.Items;

import java.io.Serializable;
import java.util.List;

public class MainChildAdapter extends RecyclerView.Adapter<MainChildAdapter.ChildViewHolder> implements Serializable {

    private Context context;
    private List<Product> items;
    AdapterListener adapterListener;
    public MainChildAdapter(Context context, List<Product> items, AdapterListener adapterListener) {
        this.context = context;
        this.items = items;
        this.adapterListener=adapterListener;
        System.out.println(items.get(0).getWhichTitle()+"jdkjks dfd");
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recycle_shop,parent,false);
        return  new ChildViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, final int position) {
        final Product item = items.get(position);
        String title= item.getWhichTitle();
        String desc= item.getWhichShortDescription();
        String image= item.getImageUrl();
        String price= item.getPrice();
        holder.price.setText(price+" টাকা");
        holder.title.setText(title);
        holder.description.setText(desc);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(holder.imageView.getContext()).load(image).apply(options).into(holder.imageView);
       holder.itemAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            adapterListener.addNew();
           }
       });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, ProductView.class);
                myIntent.putExtra("item", (Serializable) items); //Optional parameters
                myIntent.putExtra("position", (Serializable) position); //Optional parameters
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder{
        TextView price;
        ImageView imageView;
        TextView description;
        TextView title;
        TextView textNew,howMuch;
        Button itemAdd;
        ImageButton itemMore,itemLess;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            price= itemView.findViewById(R.id.price);
            imageView=itemView.findViewById(R.id.itemImage);
            description=itemView.findViewById(R.id.desc);
            title=itemView.findViewById(R.id.itemTitle);
            textNew=itemView.findViewById(R.id.textNew);
            itemAdd=itemView.findViewById(R.id.addItem);
            itemMore=itemView.findViewById(R.id.itemMore);
            itemLess=itemView.findViewById(R.id.itemLess);
            howMuch=itemView.findViewById(R.id.howMuch);
        }
    }
}
