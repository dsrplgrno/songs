package com.example.songs;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PopSong extends AppCompatActivity {
    RecyclerView pop_songList;
    DatabaseReference songListRef;
    String CallMeMaybe, RainOnMe, UpTownFunk, BillieJean, WhenDovesCry, Butter, EasyOnMe, DejaVu, MyUniverse,
            LeaveTheDoorOpen, Dynamite, Levitating, BadHabits, Stay, KingsQueens, SinceUBeenGone, driverslicense,
            BlindingLights, Umbrella, Toxic, Rehab, HeyYa, Crazy, DontSpeak, ThereforeIAm, LightSwitch, Mood,
            Happy, PokerFace,ColdHeart;

    List<String> songs;
    PopAdapter popAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_song);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 85, 85)));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Pop Music");

        songs = new ArrayList<>();
        pop_songList = findViewById(R.id.pop_list);
        pop_songList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        pop_songList.setLayoutManager(linearLayoutManager);

        songListRef = FirebaseDatabase.getInstance().getReference("songList").child("PopMusic");
        songListRef.child("songTitle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CallMeMaybe = snapshot.child("CallMeMaybe").getValue(String.class);
                RainOnMe = snapshot.child("RainOnMe").getValue(String.class);
                UpTownFunk = snapshot.child("UpTownFunk").getValue(String.class);
                BillieJean = snapshot.child("BillieJean").getValue(String.class);
                WhenDovesCry = snapshot.child("WhenDovesCry").getValue(String.class);
                Butter = snapshot.child("Butter").getValue(String.class);
                EasyOnMe = snapshot.child("EasyOnMe").getValue(String.class);
                DejaVu = snapshot.child("DejaVu").getValue(String.class);
                MyUniverse = snapshot.child("MyUniverse").getValue(String.class);
                LeaveTheDoorOpen = snapshot.child("LeaveTheDoorOpen").getValue(String.class);
                Dynamite = snapshot.child("Dynamite").getValue(String.class);
                Levitating = snapshot.child("Levitating").getValue(String.class);
                BadHabits = snapshot.child("BadHabits").getValue(String.class);
                Stay = snapshot.child("Stay").getValue(String.class);
                KingsQueens = snapshot.child("KingsQueens").getValue(String.class);
                SinceUBeenGone = snapshot.child("SinceUBeenGone").getValue(String.class);
                driverslicense = snapshot.child("driverslicense").getValue(String.class);
                BlindingLights = snapshot.child("BlindingLights").getValue(String.class);
                Umbrella = snapshot.child("Umbrella").getValue(String.class);
                Toxic = snapshot.child("Toxic").getValue(String.class);
                Rehab = snapshot.child("Rehab").getValue(String.class);
                HeyYa = snapshot.child("HeyYa").getValue(String.class);
                Crazy = snapshot.child("Crazy").getValue(String.class);
                DontSpeak = snapshot.child("DontSpeak").getValue(String.class);
                ThereforeIAm = snapshot.child("ThereforeIAm").getValue(String.class);
                LightSwitch = snapshot.child("LightSwitch").getValue(String.class);
                Mood = snapshot.child("Mood").getValue(String.class);
                Happy = snapshot.child("Happy").getValue(String.class);
                PokerFace = snapshot.child("PokerFace").getValue(String.class);
                ColdHeart = snapshot.child("ColdHeart").getValue(String.class);

                songs.add(CallMeMaybe);
                songs.add(Crazy);
                songs.add(DontSpeak);
                songs.add(ThereforeIAm);
                songs.add(LightSwitch);
                songs.add(Mood);
                songs.add(Happy);
                songs.add(PokerFace);
                songs.add(ColdHeart);
                songs.add(Dynamite);
                songs.add(Levitating);
                songs.add(BadHabits);
                songs.add(Stay);
                songs.add(KingsQueens);
                songs.add(SinceUBeenGone);
                songs.add(driverslicense);
                songs.add(BlindingLights);
                songs.add(Umbrella);
                songs.add(Toxic);
                songs.add(Rehab);
                songs.add(HeyYa);
                songs.add(RainOnMe);
                songs.add(UpTownFunk);
                songs.add(BillieJean);
                songs.add(WhenDovesCry);
                songs.add(Butter);
                songs.add(EasyOnMe);
                songs.add(DejaVu);
                songs.add(MyUniverse);
                songs.add(LeaveTheDoorOpen);
                popAdapter = new PopAdapter(PopSong.this, songs);
                pop_songList.setAdapter(popAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                popAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}