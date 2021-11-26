package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.core.View;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

    public class addHotel extends AppCompatActivity {

        private static final String TAG = "AddHotel";
        private EditText etName, etCountry, etFloor;
        private Spinner spCategory;
        private ImageView ivCover;
        private FirebaseServices fbs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity);
            etName = findViewById(R.id.etName);
            etCountry = findViewById(R.id.etCountry);
            etFloor = findViewById(R.id.etFloor);
            spCategory = findViewById(R.id.spCategoryAddHotel);
            ivCover = findViewById(R.id.ivCover);
            fbs = FirebaseServices.getInstance();
            spCategory.setAdapter(new ArrayAdapter<HotelCategory>(this, android.R.layout.simple_list_item_1, spCategryAddHotel.values()));
        }

        public void add(View View) {
            String name, country , category, photo , floor;
            name = etName.getText().toString();
            country = etCountry.getText().toString();
            floor = etFloor.getText().toString();
            category = spCategory.getSelectedItem().toString();
            if (ivCover.getDrawable() == null)
                photo = "no_image";
            else photo = ivCover.getDrawable().toString();

            if (name.trim().isEmpty() || country.trim().isEmpty() || floor.trim().isEmpty()
                    || category.trim().isEmpty() || photo.trim().isEmpty()) {
                Toast.makeText(this, "Some fields are empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            hotels hotel = new hotels(name, country , floor, spCategory.valueOf(category));
            fbs.getFire().collection("hotels")
                    .add(hotels)
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

        public void selectPhoto(View view) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"),40);
        }

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 40) {
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                            ivCover.setBackground(null);
                            ivCover.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (resultCode == Activity.RESULT_CANCELED)  {
                    Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


