package com.example.songs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RockSongAdapter extends RecyclerView.Adapter<RockSongAdapter.ViewHolder> implements Filterable {

    Context context;
    List<String> song_list;
    List<String> song_list_all;
    List<String> songsLyrics;
    DatabaseReference reference, fav_ref;
    String LikeARollingStone, WalkThisWay, Layla, SmokeOnTheWater, BrownEyeGirl, WholeLottaLove,
            FreeBird, RollingInTheDeep, SweetChildOMine, RockYouLikeAHurricane, WontGetFooledAgain, HonkyTonkWomen,
            WildThing, PurpleHaze, IntoTheMystic, SlowRide, SultansOfSwing, LowRider,JumpinJackFlash, Funk49 ,HighwaytoHell,MaggieMay,
            Comfortablynumb, YouShookMeAllNightLong, SchoolsOut, BohemianRhapsody, Kashmir, BornToBeWild, YouReallyGotMe, AnotherBrickInTheWall,
            favorite = "not_added";

    public RockSongAdapter(Context context, List<String> song_list) {
        this.context = context;
        this.song_list = song_list;
        this.song_list_all = new ArrayList<>(song_list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        songsLyrics = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("songList").child("RockMusic");
        reference.child("SongLyrics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BrownEyeGirl = snapshot.child("BrownEyeGirl").getValue(String.class);
                SmokeOnTheWater = snapshot.child("SmokeOnTheWater").getValue(String.class);
                Layla = snapshot.child("Layla").getValue(String.class);
                WalkThisWay = snapshot.child("WalkThisWay").getValue(String.class);
                LikeARollingStone = snapshot.child("LikeARollingStone").getValue(String.class);
                WholeLottaLove = snapshot.child("WholeLottaLove").getValue(String.class);
                FreeBird = snapshot.child("FreeBird").getValue(String.class);
                RollingInTheDeep = snapshot.child("RollingInTheDeep").getValue(String.class);
                SweetChildOMine = snapshot.child("SweetChildOMine").getValue(String.class);
                RockYouLikeAHurricane = snapshot.child("RockYouLikeAHurricane").getValue(String.class);
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
                Comfortablynumb = snapshot.child("Comfortablynumb").getValue(String.class);
                YouShookMeAllNightLong = snapshot.child("YouShookMeAllNightLong").getValue(String.class);
                SchoolsOut = snapshot.child("SchoolsOut").getValue(String.class);
                BohemianRhapsody = snapshot.child("BohemianRhapsody").getValue(String.class);
                Kashmir = snapshot.child("Kashmir").getValue(String.class);
                BornToBeWild = snapshot.child("BornToBeWild").getValue(String.class);
                YouReallyGotMe = snapshot.child("YouReallyGotMe").getValue(String.class);
                AnotherBrickInTheWall = snapshot.child("AnotherBrickInTheWall").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        fav_ref = FirebaseDatabase.getInstance().getReference("FavSong");
        holder.fav_icon.setOnClickListener(v -> {
            favorite = "not_added";
            holder.fav_icon.setImageResource(R.drawable.star);
            HashMap<Object, String> map = new HashMap<>();
            map.put("fav_title", song_list.get(position));
            fav_ref.push().setValue(map).addOnCompleteListener(task -> {
                Toast.makeText(context, "Add to favorite successful..", Toast.LENGTH_SHORT).show();
                holder.fav_icon.setEnabled(false);
                favorite = "added";
            });
        });

        fav_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FavoritesModel fav = dataSnapshot.getValue(FavoritesModel.class);
                    assert fav != null;
                    if(fav.getFav_title().equals(song_list.get(position))) {
                        holder.fav_icon.setImageResource(R.drawable.star);
                        holder.fav_icon.setEnabled(true);
                        holder.fav_icon.setOnClickListener(v -> {
                            fav_ref.removeValue((error, ref) -> {
                                favorite = "added";
                                holder.fav_icon.setImageResource(R.drawable.star_border);
                                Toast.makeText(context, "favorite removed!", Toast.LENGTH_SHORT).show();
                            });
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.title_of_song.setText(song_list.get(position));
        holder.itemView.setOnClickListener(v -> {
            builder.setTitle(song_list.get(position));
            if(song_list.get(position).equals("Brown Eyed Girl")) {
                builder.setMessage(BrownEyeGirl);
                builder.show();
            }
            if(song_list.get(position).equals("Comfortably numb")) {
                builder.setMessage(Comfortablynumb);
                builder.show();
            }
            if(song_list.get(position).equals("You Shook Me All Night Long")) {
                builder.setMessage(YouShookMeAllNightLong);
                builder.show();
            }
            if(song_list.get(position).equals("Schools Out")) {
                builder.setMessage(SchoolsOut);
                builder.show();
            }
            if(song_list.get(position).equals("Bohemian Rhapsody")) {
                builder.setMessage(BohemianRhapsody);
                builder.show();
            }
            if(song_list.get(position).equals("Kashmir")) {
                builder.setMessage(Kashmir);
                builder.show();
            }
            if(song_list.get(position).equals("Born To Be Wild")) {
                builder.setMessage(BornToBeWild);
                builder.show();
            }
            if(song_list.get(position).equals("You Really Got Me")) {
                builder.setMessage(YouReallyGotMe);
                builder.show();
            }
            if(song_list.get(position).equals("Another Brick In The Wall")) {
                builder.setMessage(AnotherBrickInTheWall);
                builder.show();
            }
            if(song_list.get(position).equals("Wont Get Fooled Again")) {
                builder.setMessage(WontGetFooledAgain);
                builder.show();
            }
            if(song_list.get(position).equals("Honky Tonk Women")) {
                builder.setMessage(HonkyTonkWomen);
                builder.show();
            }
            if(song_list.get(position).equals("Wild Thing")) {
                builder.setMessage(WildThing);
                builder.show();
            }
            if(song_list.get(position).equals("Purple Haze")) {
                builder.setMessage(PurpleHaze);
                builder.show();
            }
            if(song_list.get(position).equals("Into The Mystic")) {
                builder.setMessage(IntoTheMystic);
                builder.show();
            }
            if(song_list.get(position).equals("Slow Ride")) {
                builder.setMessage(SlowRide);
                builder.show();
            }
            if(song_list.get(position).equals("Sultans Of Swing")) {
                builder.setMessage(SultansOfSwing);
                builder.show();
            }
            if(song_list.get(position).equals("Low Rider")) {
                builder.setMessage(LowRider);
                builder.show();
            }
            if(song_list.get(position).equals("Jumpin Jack Flash")) {
                builder.setMessage(JumpinJackFlash);
                builder.show();
            }
            if(song_list.get(position).equals("Funk #49")) {
                builder.setMessage(Funk49);
                builder.show();
            }
            if(song_list.get(position).equals("Highway to Hell")) {
                builder.setMessage(HighwaytoHell);
                builder.show();
            }
            if(song_list.get(position).equals("Maggie May")) {
                builder.setMessage(MaggieMay);
                builder.show();
            }
            if(song_list.get(position).equals("Smoke on the Water")) {
                builder.setMessage(SmokeOnTheWater);
                builder.show();
            }
            if(song_list.get(position).equals("Layla")) {
                builder.setMessage(Layla);
                builder.show();
            }
            if(song_list.get(position).equals("Walk This Way")) {
                builder.setMessage(WalkThisWay);
                builder.show();
            }
            if(song_list.get(position).equals("Like A Rolling Stone")) {
                builder.setMessage(LikeARollingStone);
                builder.show();
            }
            if(song_list.get(position).equals("Whole Lotta Love")) {
                builder.setMessage(WholeLottaLove);
                builder.show();
            }
            if(song_list.get(position).equals("Free Bird")) {
                builder.setMessage(FreeBird);
                builder.show();
            }
            if(song_list.get(position).equals("Rolling In The Deep")) {
                builder.setMessage(RollingInTheDeep);
                builder.show();
            }
            if(song_list.get(position).equals("Sweet Child O Mine")) {
                builder.setMessage(SweetChildOMine);
                builder.show();
            }
            if(song_list.get(position).equals("Rock You Like A Hurricane")) {
                builder.setMessage(RockYouLikeAHurricane);
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return song_list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();

            if(constraint.toString().isEmpty()) {
                filteredList.addAll(song_list_all);
            }else {
                for (String song : song_list_all) {
                    if(song.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(song);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            song_list.clear();
            song_list.addAll((Collection<? extends String>) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title_of_song;
        ImageView fav_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title_of_song =itemView.findViewById(R.id.title_song);
            fav_icon = itemView.findViewById(R.id.fav_icon);
        }
    }
}
