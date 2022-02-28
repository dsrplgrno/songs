package com.example.songs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class folk_music extends AppCompatActivity {

    RecyclerView songList;
    DatabaseReference songListRef;
    String MrTambourineMan, TomDooley, WhereHaveAllTheFlowersGone, PuffTheMagicDragon, AtSeventeen, FreightTrain, TheBoxer, TangledupinBlue,BridgeOverTroubledWater,
            ThirstBoots, Hallelujah, Catchthewind, Canthecirclebeunbroken, SomedaySoon, TheBandPlayedWaltzingMatilda, FountainofSorrow,
            TamLin, ThereButForFortune, Crucifixion, lastthingonmymind, DesolationRow, HobosLullaby, ChillyWinds, TecumsehValley, Deportee,
            GoodnightIrene, LeavingOnAJetPlane, AcrosstheGreatDivide, MastersofWar, Arrow;

    List<String> songs;
    FolkSongAdapter folkSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folk_music);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 85, 85)));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Folk Music");

        songs = new ArrayList<>();
        songList = findViewById(R.id.song_list);
        songList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        songList.setLayoutManager(linearLayoutManager);
        songListRef = FirebaseDatabase.getInstance().getReference("songList").child("FolkMusic");
        songListRef.child("songTitle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MrTambourineMan = snapshot.child("MrTambourineMan").getValue(String.class);
                TomDooley = snapshot.child("TomDooley").getValue(String.class);
                WhereHaveAllTheFlowersGone = snapshot.child("WhereHaveAllTheFlowersGone").getValue(String.class);
                PuffTheMagicDragon = snapshot.child("PuffTheMagicDragon").getValue(String.class);
                AtSeventeen = snapshot.child("AtSeventeen").getValue(String.class);
                FreightTrain = snapshot.child("FreightTrain").getValue(String.class);
                TheBoxer = snapshot.child("TheBoxer").getValue(String.class);
                TangledupinBlue = snapshot.child("TangledupinBlue").getValue(String.class);
                BridgeOverTroubledWater = snapshot.child("BridgeOverTroubledWater").getValue(String.class);
                ThirstBoots = snapshot.child("ThirstBoots").getValue(String.class);
                Hallelujah = snapshot.child("Hallelujah").getValue(String.class);
                Catchthewind = snapshot.child("Catchthewind").getValue(String.class);
                Canthecirclebeunbroken = snapshot.child("Canthecirclebeunbroken").getValue(String.class);
                SomedaySoon = snapshot.child("SomedaySoon").getValue(String.class);
                TheBandPlayedWaltzingMatilda = snapshot.child("TheBandPlayedWaltzingMatilda").getValue(String.class);
                FountainofSorrow = snapshot.child("FountainofSorrow").getValue(String.class);
                TamLin = snapshot.child("TamLin").getValue(String.class);
                ThereButForFortune = snapshot.child("ThereButForFortune").getValue(String.class);
                Crucifixion = snapshot.child("Crucifixion").getValue(String.class);
                lastthingonmymind = snapshot.child("lastthingonmymind").getValue(String.class);
                DesolationRow = snapshot.child("DesolationRow").getValue(String.class);
                HobosLullaby = snapshot.child("HobosLullaby").getValue(String.class);
                ChillyWinds = snapshot.child("ChillyWinds").getValue(String.class);
                TecumsehValley = snapshot.child("TecumsehValley").getValue(String.class);
                Deportee = snapshot.child("Deportee").getValue(String.class);
                GoodnightIrene = snapshot.child("GoodnightIrene").getValue(String.class);
                LeavingOnAJetPlane = snapshot.child("LeavingOnAJetPlane").getValue(String.class);
                AcrosstheGreatDivide = snapshot.child("AcrosstheGreatDivide").getValue(String.class);
                MastersofWar = snapshot.child("MastersofWar").getValue(String.class);
                Arrow = snapshot.child("Arrow").getValue(String.class);

                songs.add(MrTambourineMan);
                songs.add(ChillyWinds);
                songs.add(TecumsehValley);
                songs.add(Deportee);
                songs.add(GoodnightIrene);
                songs.add(LeavingOnAJetPlane);
                songs.add(AcrosstheGreatDivide);
                songs.add(MastersofWar);
                songs.add(Arrow);
                songs.add(Canthecirclebeunbroken);
                songs.add(SomedaySoon);
                songs.add(TheBandPlayedWaltzingMatilda);
                songs.add(FountainofSorrow);
                songs.add(TamLin);
                songs.add(ThereButForFortune);
                songs.add(Crucifixion);
                songs.add(lastthingonmymind);
                songs.add(DesolationRow);
                songs.add(HobosLullaby);
                songs.add(Catchthewind);
                songs.add(Hallelujah);
                songs.add(ThirstBoots);
                songs.add(TomDooley);
                songs.add(WhereHaveAllTheFlowersGone);
                songs.add(PuffTheMagicDragon);
                songs.add(AtSeventeen);
                songs.add(FreightTrain);
                songs.add(TheBoxer);
                songs.add(TangledupinBlue);
                songs.add(BridgeOverTroubledWater);
                folkSongAdapter = new FolkSongAdapter(folk_music.this, songs);
                songList.setAdapter(folkSongAdapter);
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
                folkSongAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}