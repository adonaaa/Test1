package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.ULocale;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

public class addHotel extends AppCompatActivity {

    private static final String TAG = "AddHotel";
    private EditText etName, etCountry, etFloor;
    private Spinner spCategory;
    private ImageView ivCover;
    private FirebaseServices fbs;
    private Uri filePath;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        getSupportActionBar().hide();
        connectComponents();
    }

    private void connectComponents() {

        etName = findViewById(R.id.etNameAdd);
        etCountry = findViewById(R.id.etCountryAdd);
        etFloor = findViewById(R.id.etFloorAdd);
        spCategory = findViewById(R.id.spCategoryAddHotel);
        ivCover = findViewById(R.id.ivCoverAdd);
        fbs = FirebaseServices.getInstance();
        spCategory.setAdapter(new ArrayAdapter<HotelCategory>(this, android.R.layout.simple_list_item_1, HotelCategory.values()));
        storageReference = fbs.getStorage().getReference();
    }


        public void add(View view)
        {
            String name, country, category, photo;
            int floor;
            name = etName.getText().toString();
            country = etCountry.getText().toString();
            floor = Integer.parseInt(etFloor.getText().toString());
            category = spCategory.getSelectedItem().toString();
            if (ivCover.getDrawable() == null)
                photo = "no_image";
            else photo = ivCover.getDrawable().toString();

            if (name.trim().isEmpty() || country.trim().isEmpty() || String.valueOf(floor).trim().isEmpty()
                    || category.trim().isEmpty() || photo.trim().isEmpty()) {
                Toast.makeText(this, "Some fields are empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            hotels hotel = new hotels(name, country, floor, HotelCategory.valueOf(category), photo);
            fbs.getFire().collection("hotels")
                    .add(hotel)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "Document added" + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            ivCover.setDrawingCacheEnabled(true);
            ivCover.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) ivCover.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            StorageReference storageRef = fbs.getStorage().getReference();
            UploadTask uploadTask = storageRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                }
            });
        }
}





