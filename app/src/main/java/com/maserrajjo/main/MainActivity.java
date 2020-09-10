package com.maserrajjo.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;
import com.maserrajjo.R;
import com.maserrajjo.adapters.AdapterListener;
import com.maserrajjo.adapters.MainParentAdapter;
import com.maserrajjo.database.model.Catagory;
import com.maserrajjo.database.presenter.CatagoryManagementPresenter;
import com.maserrajjo.database.presenter.CategoryInterfce;
import com.maserrajjo.model.Items;
import com.maserrajjo.model.ItemsWithCategory;
import com.maserrajjo.model.ProductsWithCategory;
import com.maserrajjo.uploadProduct.UploadProduct;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener, View.OnClickListener, CategoryInterfce {

    MainParentAdapter mainParentAdapter;
    private RecyclerView recyclerView;
    ArrayList<ProductsWithCategory> itemsWithCategories;
    CounterFab counterFab;
    ImageView menuImageViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsWithCategories = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        counterFab = findViewById(R.id.floatingActionButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        menuImageViewID=findViewById(R.id.menuImageViewID);
        menuImageViewID.setOnClickListener(this);
        saveData();
        setUpNavView();
    }

    private void saveData(){
        System.out.println("i am 1  debugx");
        CatagoryManagementPresenter catagoryManagementPresenter= new CatagoryManagementPresenter(MainActivity.this);
    }

    @Override
    public void addNew() {
        counterFab.increase();
        System.out.println("increse xyz");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menuImageViewID:
                drawerSetup();
                break;

        }
    }


    private void setUpNavView() {

        NavigationView navigationView = findViewById(R.id.navViewID);
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.faqID:
                        Toast.makeText(getApplicationContext(), "Frequent ask", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.productUploadID:
                        upload();
                        break;

                }
                return false;
            }
        });
    }



    @SuppressLint("WrongConstant")
    private void drawerSetup() {
        DrawerLayout navDrawer = findViewById(R.id.drawerLayoutID);
        if(!navDrawer.isDrawerOpen(Gravity.START)) navDrawer.openDrawer(Gravity.START);
        else navDrawer.closeDrawer(Gravity.END);
    }


    @Override
    public void setData(ArrayList<ProductsWithCategory> productsWithCategory) {
        itemsWithCategories=productsWithCategory;
        mainParentAdapter = new MainParentAdapter(this,this,itemsWithCategories);
        recyclerView.setAdapter(mainParentAdapter);
        System.out.println("xyzx"+itemsWithCategories.get(0).getTitle());
        mainParentAdapter.notifyDataSetChanged();
    }


    private void upload(){
        Intent myIntent = new Intent(MainActivity.this, UploadProduct.class);
        MainActivity.this.startActivity(myIntent);
        System.out.println("xyzx "+"upload");
    }


}