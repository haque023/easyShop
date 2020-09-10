package com.maserrajjo.uploadProduct;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.maserrajjo.R;
import com.maserrajjo.database.model.Catagory;
import com.maserrajjo.database.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UploadProduct extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    EditText price,title,shortDescription;
    Button buttonBrowse,buttonUpload;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    ProgressDialog progressDialog ;
    int whichCategory;
    final Map<String, Integer> categoryMaps = new HashMap<String, Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);
        storageReference = FirebaseStorage.getInstance().getReference("Images");
        databaseReference = FirebaseDatabase.getInstance().getReference("product");
        databaseReference2=FirebaseDatabase.getInstance().getReference("catagory");
        final List<String> arrayList= new ArrayList<>();

        databaseReference2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.add(0,"সিলেক্ট ক্যাটাগরি");
                categoryMaps.put("সিলেক্ট ক্যাটাগরি",0);

                for(DataSnapshot dss: snapshot.getChildren()) {
                    final Catagory catagory = dss.getValue(Catagory.class);
                    arrayList.add(catagory.getWhichName()+"");
                    categoryMaps.put(catagory.getWhichName(),catagory.getWhichId());

                }

                setSpinner(arrayList);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        imageView=findViewById(R.id.image_view);
        buttonBrowse=findViewById(R.id.buttonBrowse);
        buttonUpload=findViewById(R.id.buttonUpload);
        price=findViewById(R.id.price);
        title=findViewById(R.id.title);
        shortDescription=findViewById(R.id.shortDescription);
        progressDialog = new ProgressDialog(UploadProduct.this);
        imageView.setOnClickListener(this);
        buttonBrowse.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(this).load(R.drawable.upload_image).apply(options).into(imageView);



    }
private void setSpinner(List<String> catagories){
    Spinner s = (Spinner) findViewById(R.id.spinnerCat);
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, catagories);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    s.setAdapter(arrayAdapter);
    s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if(adapterView.getItemAtPosition(i).equals("সিলেক্ট ক্যাটাগরি")) whichCategory=0;
            System.out.println(adapterView.getItemAtPosition(i)+" ffffxxxx : " + categoryMaps.get(adapterView.getItemAtPosition(i)));
            whichCategory=categoryMaps.get(adapterView.getItemAtPosition(i));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
}


    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.image_view:
                    browseImage();
                    break;

                case R.id.buttonBrowse:
                    browseImage();
                    break;
                case R.id.buttonUpload:
                    UploadImage();
                    break;
            }
    }


    private void browseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 7);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 7 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public void UploadImage() {

        if (FilePathUri != null) {
            if(whichCategory!=0){
            progressDialog.setTitle("Product is Uploading...");
            progressDialog.show();
            final StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return storageReference2.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downUri = task.getResult();
                        String itemPrice = price.getText().toString();
                        String itemTitle = title.getText().toString();
                        String itemShortDescription = shortDescription.getText().toString();
                        String bigDescription = itemShortDescription;
                        price.setText("");
                        title.setText("");
                        shortDescription.setText("");
                        progressDialog.dismiss();
                        imageView.setImageResource(R.mipmap.ic_launcher);
                        Long tsLong = System.currentTimeMillis() / 1000;
                        String ts = tsLong.toString();
                        Toast.makeText(getApplicationContext(), "Product Uploaded Successfully ", Toast.LENGTH_LONG).show();
                        @SuppressWarnings("VisibleForTests")
                        Product itemProduct = new Product(ts, itemPrice, whichCategory, itemTitle, itemShortDescription, bigDescription,downUri.toString() );
                        String ImageUploadId = databaseReference.push().getKey();
                        databaseReference.child(ImageUploadId).setValue(itemProduct);
                        Log.d("TAG", "onComplete: Url: " + downUri.toString());
                    }
                }
            });
        }
            else {
                Toast.makeText(UploadProduct.this, "Please Select a Category", Toast.LENGTH_LONG).show();

            }
        }
        else {

            Toast.makeText(UploadProduct.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }


    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


}