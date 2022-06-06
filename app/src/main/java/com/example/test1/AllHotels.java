package com.example.test1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllHotels extends AppCompatActivity {

    private RecyclerView rv;
    private FirebaseServices fbs;
    private ArrayList<hotels> hotelsArray = new ArrayList<>();
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
        readData();



        // set up the RecyclerView

//        Log.d("AllHotels", hotels.get(0).toString());

    }

    private void readData() {
        fbs.getFire().collection("hotels")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot task) {

                    for (DocumentSnapshot document : task.getDocuments()) {
                        hotels myhotel = document.toObject(hotels.class);
                        Log.d("readData", myhotel.toString());
                        hotelsArray.add(myhotel);
                    }
                    RecyclerView recyclerView = findViewById(R.id.rvRestsAllHotel);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AllHotels.this));
                    Log.d("LengthOfArray", String.valueOf(hotelsArray.size()));
                    adapter = new Adapter(AllHotels.this, hotelsArray);
                    recyclerView.setAdapter(adapter);
                }
            });
        }

    }


