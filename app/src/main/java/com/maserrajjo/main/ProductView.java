package com.maserrajjo.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maserrajjo.R;
import com.maserrajjo.database.model.Product;

import java.io.Serializable;
import java.util.List;

public class ProductView extends AppCompatActivity implements Serializable{
    ImageView backgroundProduct,productImage;
    SlidingPaneLayout slidingPaneLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);


        List<Product> product = (List<Product>) getIntent().getSerializableExtra("item");
        int position = (int) getIntent().getSerializableExtra("position");
        backgroundProduct=findViewById(R.id.backgroundProductImage);
        productImage=findViewById(R.id.productImage);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(this).load(product.get(position).getImageUrl()).apply(options).into(productImage);
        Glide.with(this).load(product.get(position).getImageUrl()).apply(options).into(backgroundProduct);

    }
}

