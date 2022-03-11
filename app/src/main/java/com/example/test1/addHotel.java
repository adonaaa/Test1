package com.example.test1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class addHotel extends AppCompatActivity {

    private static final String TAG = "AddHotel";
    private EditText etName, etCountry, etFloor , etDesc ,etPhone , etAddress;
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

        etDesc = findViewById(R.id.etDescriptionAddHotel);
        etName = findViewById(R.id.etNameAdd);
        etCountry = findViewById(R.id.etAddressAddHotel);
        etFloor = findViewById(R.id.etFloorAdd);
        spCategory = findViewById(R.id.spCategoryAddHotel);
        ivCover = findViewById(R.id.ivCoverAdd);
        fbs = FirebaseServices.getInstance();
        spCategory.setAdapter(new ArrayAdapter<HotelCategory>(this, android.R.layout.simple_list_item_1, HotelCategory.values()));
        storageReference = fbs.getStorage().getReference();
        etPhone = findViewById(R.id.etPhoneAddHotel);
        etAddress = findViewById(R.id.etAddressAddHotel);


    }
    public void add(View view) {
        // check if any field is empty
        String name, description, address, phone, category, photo;
        name = etName.getText().toString();
        description = etDesc.getText().toString();
        address = etAddress.getText().toString();
        phone = etPhone.getText().toString();
        category = spCategory.getSelectedItem().toString();
        if (ivCover.getDrawable() == null)
            photo = "no_image";
        else photo = storageReference.getDownloadUrl().toString();

        if (name.trim().isEmpty() || description.trim().isEmpty() || address.trim().isEmpty() ||
                phone.trim().isEmpty() || category.trim().isEmpty() || photo.trim().isEmpty())
        {
            Toast.makeText(this, R.string.err_fields_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        hotels hotel = new hotels(name, description, address, .valueOf(category), photo, phone);
        fbs.getFire().collection("restaurants")
                .add(hotel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
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
//                    try {
                    filePath = data.getData();
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
//                        ivPhoto.setBackground(null);
//                        ivPhoto.setImageBitmap(bitmap);
                    Picasso.get().load(filePath).into(ivCover);
                    uploadImage();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(addHotel.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(addHotel.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }
}






