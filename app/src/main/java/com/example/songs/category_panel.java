package com.example.songs;

import android.Manifest;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class category_panel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_panel);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 85, 85)));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Music Categories");

        CardView fav_btn = findViewById(R.id.fav);
        fav_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, fav_activity.class);
            startActivity(intent);
        });

        LinearLayout PopSongButton = findViewById(R.id.layout1);

        PopSongButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PopSong.class);
            startActivity(intent);
        });

        LinearLayout RockMusicButton = findViewById(R.id.layout);

        RockMusicButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RockMusic.class);
            startActivity(intent);
        });

        LinearLayout CountryMusicButton = findViewById(R.id.layout2);

        CountryMusicButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, CountryMusic.class);
            startActivity(intent);
        });

        LinearLayout folk_music = findViewById(R.id.folk_music);

        folk_music.setOnClickListener(v -> {
            Intent intent = new Intent(this, folk_music.class);
            startActivity(intent);
        });
    }

    public void sgnOut(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(category_panel.this, MainActivity.class));
        finish();
    }
}