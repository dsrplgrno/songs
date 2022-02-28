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

public class CountryMusic extends AppCompatActivity {
    RecyclerView country_songList;
    DatabaseReference songListRef;
    String AngHulingElBimbo, Tadhana, AngHimigNatin, Narda, Binibini, SaYo, DiNaMuli, BeforeILetYouGo, TwoOneFour,
            WhyCantItBe, HanggangKailan, BeforeHeCheats, TheGambler, KissanAngelGoodMorning, RhinestoneCowboy, FriendsInLowPlaces, SomebodyLikeYou,
            TheDance, Fancy, TakeMeHomeCountryRoads, IWalktheLine, StandByYourMan, SmokyMountainRain, FancyLike, BlueEyesCryingInTheRain,
            ForeverandEverAmen, ForeverAfterAll, StrawberryWine, TheGoodOnes,ChasingAfterYou;

    List<String> songs;
    CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_music);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 85, 85)));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Country Music");

        songs = new ArrayList<>();
        country_songList = findViewById(R.id.country_list);
        country_songList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        country_songList.setLayoutManager(linearLayoutManager);

        songListRef = FirebaseDatabase.getInstance().getReference("songList").child("CountryMusic");
        songListRef.child("songTitle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AngHulingElBimbo = snapshot.child("AngHulingElBimbo").getValue(String.class);
                Tadhana = snapshot.child("Tadhana").getValue(String.class);
                AngHimigNatin = snapshot.child("AngHimigNatin").getValue(String.class);
                Narda = snapshot.child("Narda").getValue(String.class);
                Binibini = snapshot.child("Binibini").getValue(String.class);
                SaYo = snapshot.child("SaYo").getValue(String.class);
                DiNaMuli = snapshot.child("DiNaMuli").getValue(String.class);
                BeforeILetYouGo = snapshot.child("BeforeILetYouGo").getValue(String.class);
                TwoOneFour = snapshot.child("TwoOneFour").getValue().toString();
                WhyCantItBe = snapshot.child("WhyCantItBe").getValue(String.class);
                HanggangKailan = snapshot.child("HanggangKailan").getValue(String.class);
                BeforeHeCheats = snapshot.child("BeforeHeCheats").getValue(String.class);
                TheGambler = snapshot.child("TheGambler").getValue(String.class);
                KissanAngelGoodMorning = snapshot.child("KissanAngelGoodMorning").getValue(String.class);
                RhinestoneCowboy = snapshot.child("RhinestoneCowboy").getValue(String.class);
                FriendsInLowPlaces = snapshot.child("FriendsInLowPlaces").getValue(String.class);
                SomebodyLikeYou = snapshot.child("SomebodyLikeYou").getValue(String.class);
                TheDance = snapshot.child("TheDance").getValue(String.class);
                Fancy = snapshot.child("Fancy").getValue(String.class);
                TakeMeHomeCountryRoads = snapshot.child("TakeMeHomeCountryRoads").getValue(String.class);
                IWalktheLine = snapshot.child("IWalktheLine").getValue(String.class);
                StandByYourMan = snapshot.child("StandByYourMan").getValue(String.class);
                SmokyMountainRain = snapshot.child("SmokyMountainRain").getValue(String.class);
                FancyLike = snapshot.child("FancyLike").getValue(String.class);
                BlueEyesCryingInTheRain = snapshot.child("BlueEyesCryingInTheRain").getValue(String.class);
                ForeverandEverAmen = snapshot.child("ForeverandEverAmen").getValue(String.class);
                ForeverAfterAll = snapshot.child("ForeverAfterAll").getValue(String.class);
                StrawberryWine = snapshot.child("StrawberryWine").getValue(String.class);
                TheGoodOnes = snapshot.child("TheGoodOnes").getValue(String.class);
                ChasingAfterYou = snapshot.child("ChasingAfterYou").getValue(String.class);


                songs.add(AngHulingElBimbo);
                songs.add(BeforeHeCheats);
                songs.add(TheGambler);
                songs.add(KissanAngelGoodMorning);
                songs.add(RhinestoneCowboy);
                songs.add(FriendsInLowPlaces);
                songs.add(SomebodyLikeYou);
                songs.add(TheDance);
                songs.add(Fancy);
                songs.add(TakeMeHomeCountryRoads);
                songs.add(IWalktheLine);
                songs.add(StandByYourMan);
                songs.add(SmokyMountainRain);
                songs.add(FancyLike);
                songs.add(BlueEyesCryingInTheRain);
                songs.add(ForeverandEverAmen);
                songs.add(ForeverAfterAll);
                songs.add(StrawberryWine);
                songs.add(TheGoodOnes);
                songs.add(ChasingAfterYou);
                songs.add(Tadhana);
                songs.add(AngHimigNatin);
                songs.add(Narda);
                songs.add(Binibini);
                songs.add(SaYo);
                songs.add(DiNaMuli);
                songs.add(BeforeILetYouGo);
                songs.add(TwoOneFour);
                songs.add(WhyCantItBe);
                songs.add(HanggangKailan);
                countryAdapter = new CountryAdapter(CountryMusic.this, songs);
                country_songList.setAdapter(countryAdapter);
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
                countryAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}