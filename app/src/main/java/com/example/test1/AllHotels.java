package com.example.test1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllHotels extends AppCompatActivity {

    private RecyclerView rv;
    private FirebaseServices fbs;
    private ArrayList<hotels> hotels;
    private Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hotels);


        // data to populate the RecyclerView with
        ArrayList<String> HotelNames = new ArrayList<>();
        HotelNames.add("Helton");
        HotelNames.add("Ramada");
        HotelNames.add("Plaza");
        HotelNames.add("Golden Crown");
        HotelNames.add("Sheraton");

        fbs = FirebaseServices.getInstance();
        hotels = new ArrayList<hotels>();
        readData();


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvRestsAllHotel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, hotels);
        recyclerView.setAdapter(adapter);
    }

    private void readData() {
        fbs.getFire().collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                        hotels.add(document.toObject(hotels.class));
                            }
                        } else {
                            Log.w("AllHotels: readData", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
