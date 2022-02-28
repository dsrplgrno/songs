package com.example.songs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class fav_activity extends AppCompatActivity {

    RecyclerView fav_list;
    DatabaseReference ref;
    List<FavoritesModel> my_list;
    FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        fav_list = findViewById(R.id.fav_list);
        fav_list.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        fav_list.setLayoutManager(linearLayoutManager);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 85, 85)));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Favorites");

        my_list = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("FavSong");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                my_list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FavoritesModel favoritesModel = dataSnapshot.getValue(FavoritesModel.class);
                    my_list.add(favoritesModel);
                }
                favoriteAdapter = new FavoriteAdapter(fav_activity.this, my_list);
                fav_list.setAdapter(favoriteAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}