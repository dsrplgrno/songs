package com.example.songs;

import android.app.ActionBar;
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

import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RockMusic extends AppCompatActivity {
    RecyclerView songList;
    DatabaseReference songListRef;
    String LikeARollingStone, WalkThisWay, Layla, SmokeOnTheWater, BrownEyeGirl, WholeLottaLove,
            FreeBird, RollingInTheDeep, SweetChildOMine, RockYouLikeAHurricane, WontGetFooledAgain, HonkyTonkWomen,
            WildThing, PurpleHaze, IntoTheMystic, SlowRide, SultansOfSwing, LowRider,JumpinJackFlash, Funk49 ,HighwaytoHell,MaggieMay,
            Comfortablynumb, YouShookMeAllNightLong, SchoolsOut, BohemianRhapsody, Kashmir, BornToBeWild, YouReallyGotMe, AnotherBrickInTheWall;

    List<String> songs;
    RockSongAdapter rockSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_music);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 85, 85)));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Rock Music");

        songs = new ArrayList<>();
        songList = findViewById(R.id.song_list);
        songList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        songList.setLayoutManager(linearLayoutManager);
        songListRef = FirebaseDatabase.getInstance().getReference("songList").child("RockMusic");
        songListRef.child("songTitle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BrownEyeGirl = snapshot.child("BrownEyeGirl").getValue(String.class);
                LikeARollingStone = snapshot.child("LikeARollingStone").getValue(String.class);
                WalkThisWay = snapshot.child("WalkThisWay").getValue(String.class);
                Layla = snapshot.child("Layla").getValue(String.class);
                SmokeOnTheWater = snapshot.child("SmokeOnTheWater").getValue(String.class);
                WholeLottaLove = snapshot.child("WholeLottaLove").getValue(String.class);
                FreeBird = snapshot.child("FreeBird").getValue(String.class);
                RollingInTheDeep = snapshot.child("RollingInTheDeep").getValue(String.class);
                SweetChildOMine = snapshot.child("SweetChildOMine").getValue(String.class);
                WontGetFooledAgain = snapshot.child("WontGetFooledAgain").getValue(String.class);
                HonkyTonkWomen = snapshot.child("HonkyTonkWomen").getValue(String.class);
                WildThing = snapshot.child("WildThing").getValue(String.class);
                PurpleHaze = snapshot.child("PurpleHaze").getValue(String.class);
                IntoTheMystic = snapshot.child("IntoTheMystic").getValue(String.class);
                SlowRide = snapshot.child("SlowRide").getValue(String.class);
                SultansOfSwing = snapshot.child("SultansOfSwing").getValue(String.class);
                LowRider = snapshot.child("LowRider").getValue(String.class);
                JumpinJackFlash = snapshot.child("JumpinJackFlash").getValue(String.class);
                Funk49 = snapshot.child("Funk49").getValue(String.class);
                HighwaytoHell = snapshot.child("HighwaytoHell").getValue(String.class);
                MaggieMay = snapshot.child("MaggieMay").getValue(String.class);
                RockYouLikeAHurricane = snapshot.child("RockYouLikeAHurricane").getValue(String.class);
                Comfortablynumb = snapshot.child("Comfortablynumb").getValue(String.class);
                YouShookMeAllNightLong = snapshot.child("YouShookMeAllNightLong").getValue(String.class);
                SchoolsOut = snapshot.child("SchoolsOut").getValue(String.class);
                BohemianRhapsody = snapshot.child("BohemianRhapsody").getValue(String.class);
                Kashmir = snapshot.child("Kashmir").getValue(String.class);
                BornToBeWild = snapshot.child("BornToBeWild").getValue(String.class);
                YouReallyGotMe = snapshot.child("YouReallyGotMe").getValue(String.class);
                AnotherBrickInTheWall = snapshot.child("AnotherBrickInTheWall").getValue(String.class);

                songs.add(BrownEyeGirl);
                songs.add(Comfortablynumb);
                songs.add(YouShookMeAllNightLong);
                songs.add(SchoolsOut);
                songs.add(BohemianRhapsody);
                songs.add(Kashmir);
                songs.add(BornToBeWild);
                songs.add(YouReallyGotMe);
                songs.add(AnotherBrickInTheWall);
                songs.add(WontGetFooledAgain);
                songs.add(HonkyTonkWomen);
                songs.add(WildThing);
                songs.add(PurpleHaze);
                songs.add(IntoTheMystic);
                songs.add(SlowRide);
                songs.add(SultansOfSwing);
                songs.add(LowRider);
                songs.add(JumpinJackFlash);
                songs.add(Funk49);
                songs.add(HighwaytoHell);
                songs.add(MaggieMay);
                songs.add(LikeARollingStone);
                songs.add(WalkThisWay);
                songs.add(Layla);
                songs.add(SmokeOnTheWater);
                songs.add(WholeLottaLove);
                songs.add(FreeBird);
                songs.add(RollingInTheDeep);
                songs.add(SweetChildOMine);
                songs.add(RockYouLikeAHurricane);
                rockSongAdapter = new RockSongAdapter(RockMusic.this, songs);
                songList.setAdapter(rockSongAdapter);
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
                rockSongAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}