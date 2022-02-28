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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.ViewHolder> implements Filterable {
    Context context;
    List<String> song_list;
    List<String> song_list_all;
    List<String> songsLyrics;
    DatabaseReference reference, fav_ref;
    String CallMeMaybe, RainOnMe, UpTownFunk, BillieJean, WhenDovesCry, Butter, EasyOnMe, DejaVu, MyUniverse,
            LeaveTheDoorOpen,Dynamite, Levitating, BadHabits, Stay, KingsQueens, SinceUBeenGone, driverslicense,
    BlindingLights, Umbrella, Toxic, Rehab, HeyYa, Crazy, DontSpeak, ThereforeIAm, LightSwitch, Mood,
    Happy, PokerFace,ColdHeart, favorite = "not_added";

    public PopAdapter(Context context, List<String> song_list) {
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
        reference = FirebaseDatabase.getInstance().getReference("songList").child("PopMusic");
        reference.child("SongLyrics").addValueEventListener(new ValueEventListener() {
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
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
            if(song_list.get(position).equals("Call Me Maybe")) {
                builder.setMessage(CallMeMaybe);
                builder.show();
            }
            if(song_list.get(position).equals("Dynamite")) {
                builder.setMessage(Dynamite);
                builder.show();
            }
            if(song_list.get(position).equals("Crazy")) {
                builder.setMessage(Crazy);
                builder.show();
            }
            if(song_list.get(position).equals("Dont Speak")) {
                builder.setMessage(DontSpeak);
                builder.show();
            }
            if(song_list.get(position).equals("Therefore I Am")) {
                builder.setMessage(ThereforeIAm);
                builder.show();
            }
            if(song_list.get(position).equals("Light Switch")) {
                builder.setMessage(LightSwitch);
                builder.show();
            }
            if(song_list.get(position).equals("Mood")) {
                builder.setMessage(Mood);
                builder.show();
            }
            if(song_list.get(position).equals("Happy")) {
                builder.setMessage(Happy);
                builder.show();
            }
            if(song_list.get(position).equals("Poker Face")) {
                builder.setMessage(PokerFace);
                builder.show();
            }
            if(song_list.get(position).equals("Cold Heart")) {
                builder.setMessage(ColdHeart);
                builder.show();
            }
            if(song_list.get(position).equals("Levitating")) {
                builder.setMessage(Levitating);
                builder.show();
            }
            if(song_list.get(position).equals("Bad Habits")) {
                builder.setMessage(BadHabits);
                builder.show();
            }
            if(song_list.get(position).equals("Stay")) {
                builder.setMessage(Stay);
                builder.show();
            }
            if(song_list.get(position).equals("Kings & Queens")) {
                builder.setMessage(KingsQueens);
                builder.show();
            }
            if(song_list.get(position).equals("Since U Been Gone")) {
                builder.setMessage(SinceUBeenGone);
                builder.show();
            }
            if(song_list.get(position).equals("drivers license")) {
                builder.setMessage(driverslicense);
                builder.show();
            }
            if(song_list.get(position).equals("Blinding Lights")) {
                builder.setMessage(BlindingLights);
                builder.show();
            }
            if(song_list.get(position).equals("Umbrella")) {
                builder.setMessage(Umbrella);
                builder.show();
            }
            if(song_list.get(position).equals("Toxic")) {
                builder.setMessage(Toxic);
                builder.show();
            }
            if(song_list.get(position).equals("Rehab")) {
                builder.setMessage(Rehab);
                builder.show();
            }
            if(song_list.get(position).equals("Hey Ya!")) {
                builder.setMessage(HeyYa);
                builder.show();
            }
            if(song_list.get(position).equals("Leave The Door Open")) {
                builder.setMessage(LeaveTheDoorOpen);
                builder.show();
            }
            if(song_list.get(position).equals("My Universe")) {
                builder.setMessage(MyUniverse);
                builder.show();
            }
            if(song_list.get(position).equals("Rain On Me")) {
                builder.setMessage(RainOnMe);
                builder.show();
            }
            if(song_list.get(position).equals("Up Town Funk")) {
                builder.setMessage(UpTownFunk);
                builder.show();
            }
            if(song_list.get(position).equals("Billie Jean")) {
                builder.setMessage(BillieJean);
                builder.show();
            }
            if(song_list.get(position).equals("When Doves Cry")) {
                builder.setMessage(WhenDovesCry);
                builder.show();
            }
            if(song_list.get(position).equals("Butter")) {
                builder.setMessage(Butter);
                builder.show();
            }
            if(song_list.get(position).equals("Easy on Me")) {
                builder.setMessage(EasyOnMe);
                builder.show();
            }
            if(song_list.get(position).equals("deja vu")) {
                builder.setMessage(DejaVu);
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


