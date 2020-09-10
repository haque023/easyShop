package com.maserrajjo.database.presenter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.maserrajjo.database.model.Catagory;
import com.maserrajjo.database.model.Product;
import com.maserrajjo.model.ProductsWithCategory;

import java.util.ArrayList;

public class CatagoryManagementPresenter {
    CategoryInterfce categoryInterfce;
    public CatagoryManagementPresenter(CategoryInterfce categoryInterfce) {
        this.categoryInterfce=categoryInterfce;
        init();
    }
    private  void init(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("catagory");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    final ArrayList<ProductsWithCategory>productsWithCategoryList=new ArrayList<>();

                    for(DataSnapshot dss: snapshot.getChildren()){
                        final Catagory catagory= dss.getValue(Catagory.class);

                        Query query= FirebaseDatabase.getInstance().getReference().child("product")
                                .orderByChild("whichCatagory").
                                        equalTo(catagory.getWhichId());
                        query.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshots) {
                                if(snapshots.exists()){
                                    final ArrayList<Product>productList=new ArrayList<>();
                                    ProductsWithCategory productsWithCategory = new ProductsWithCategory();
                                    productsWithCategory.setTitle(catagory.getWhichName());
                                    for(DataSnapshot dss2: snapshots.getChildren()) {
                                        Product product = dss2.getValue(Product.class);
                                        productList.add(product);
                                        System.out.println(product.getImageUrl()+"xyz"+product.getWhichTitle()+"xyzs");
                                    }
                                    productsWithCategory.setItemsArrayList(productList);
                                    System.out.println(productsWithCategory.getTitle()+"xyz");
                                    boolean mybool=false;

                                    for (ProductsWithCategory pr:productsWithCategoryList) {
                                        if(pr.getTitle().contains(productsWithCategory.getTitle())){
                                            pr.setItemsArrayList(productList);
                                            mybool=true;

                                        }
                                    }
                                    if(!mybool)
                                    productsWithCategoryList.add(productsWithCategory);
                                    categoryInterfce.setData(productsWithCategoryList);

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
