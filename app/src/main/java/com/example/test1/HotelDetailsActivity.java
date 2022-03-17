package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class HotelDetailsActivity extends AppCompatActivity {
    private TextView tvName, tvDescription, tvAddress, tvCategory, tvPhone;
    private ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        connectComponents();
        Intent i = this.getIntent();
        hotels hotel = (hotels) i.getSerializableExtra("hotel");

        tvName.setText(hotel.getName());
        tvDescription.setText(hotel.getDescription());
        tvAddress.setText(hotel.getAddress());
        tvCategory.setText(hotel.getCategory().toString());
        tvPhone.setText(hotel.getPhone());
        Picasso.get().load(hotel.getPhoto()).into(ivPhoto);
    }

    private void connectComponents() {
        tvName = findViewById(R.id.tvNameHotelDetails);
        tvDescription = findViewById(R.id.tvDescriptionHotelDetails);
        tvAddress = findViewById(R.id.tvAddressHotelDetails);
        tvCategory = findViewById(R.id.tvCategoryHotelDetails);
        tvPhone = findViewById(R.id.tvPhoneHotelDetails);
        ivPhoto = findViewById(R.id.ivPhotoHotelDetails);
    }
}