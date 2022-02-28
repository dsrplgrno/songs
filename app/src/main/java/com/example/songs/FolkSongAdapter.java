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

public class FolkSongAdapter extends RecyclerView.Adapter<FolkSongAdapter.ViewHolder> implements Filterable {

    Context context;
    List<String> song_list;
    List<String> song_list_all;
    List<String> songsLyrics;
    DatabaseReference reference, fav_ref;
    String MrTambourineMan, TomDooley, WhereHaveAllTheFlowersGone, PuffTheMagicDragon, AtSeventeen, FreightTrain, TheBoxer,
            TangledupinBlue, BridgeOverTroubledWater, ThirstBoots, Hallelujah, Catchthewind,Canthecirclebeunbroken, SomedaySoon, TheBandPlayedWaltzingMatilda, FountainofSorrow,
    TamLin, ThereButForFortune, Crucifixion, lastthingonmymind, DesolationRow, HobosLullaby, ChillyWinds, TecumsehValley, Deportee,
    GoodnightIrene, LeavingOnAJetPlane, AcrosstheGreatDivide, MastersofWar, Arrow, favorite = "not_added";

    public FolkSongAdapter(Context context, List<String> song_list) {
        this.context = context;
        this.song_list = song_list;
        this.song_list_all = new ArrayList<>(song_list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_list_item, parent, false);

        return new FolkSongAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return song_list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        songsLyrics = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("songList").child("FolkMusic");
        reference.child("SongLyrics").addValueEventListener(new ValueEventListener() {
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
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.title_of_song.setText(song_list.get(position));
        holder.itemView.setOnClickListener(v -> {
            builder.setTitle(song_list.get(position));
            if(song_list.get(position).equals("Mr Tambourine Man")) {
                builder.setMessage(MrTambourineMan);
                builder.show();
            }
            if(song_list.get(position).equals("Chilly Winds")) {
                builder.setMessage(ChillyWinds);
                builder.show();
            }
            if(song_list.get(position).equals("Tecumseh Valley")) {
                builder.setMessage(TecumsehValley);
                builder.show();
            }
            if(song_list.get(position).equals("Deportee")) {
                builder.setMessage(Deportee);
                builder.show();
            }
            if(song_list.get(position).equals("Goodnight Irene")) {
                builder.setMessage(GoodnightIrene);
                builder.show();
            }
            if(song_list.get(position).equals("Leaving On A Jet Plane")) {
                builder.setMessage(LeavingOnAJetPlane);
                builder.show();
            }
            if(song_list.get(position).equals("Across the Great Divide")) {
                builder.setMessage(AcrosstheGreatDivide);
                builder.show();
            }
            if(song_list.get(position).equals("Masters of War")) {
                builder.setMessage(MastersofWar);
                builder.show();
            }
            if(song_list.get(position).equals("Arrow")) {
                builder.setMessage(Arrow);
                builder.show();
            }
            if(song_list.get(position).equals("Can the circle be unbroken")) {
                builder.setMessage(Canthecirclebeunbroken);
                builder.show();
            }
            if(song_list.get(position).equals("Someday Soon")) {
                builder.setMessage(SomedaySoon);
                builder.show();
            }
            if(song_list.get(position).equals("The Band Played Waltzing Matilda")) {
                builder.setMessage(TheBandPlayedWaltzingMatilda);
                builder.show();
            }
            if(song_list.get(position).equals("Fountain of Sorrow")) {
                builder.setMessage(FountainofSorrow);
                builder.show();
            }
            if(song_list.get(position).equals("Tam Lin")) {
                builder.setMessage(TamLin);
                builder.show();
            }
            if(song_list.get(position).equals("There But For Fortune")) {
                builder.setMessage(ThereButForFortune);
                builder.show();
            }
            if(song_list.get(position).equals("Crucifixion")) {
                builder.setMessage(Crucifixion);
                builder.show();
            }
            if(song_list.get(position).equals("last thing on my mind")) {
                builder.setMessage(lastthingonmymind);
                builder.show();
            }
            if(song_list.get(position).equals("Desolation Row")) {
                builder.setMessage(DesolationRow);
                builder.show();
            }
            if(song_list.get(position).equals("Hobos Lullaby")) {
                builder.setMessage(HobosLullaby);
                builder.show();
            }
            if(song_list.get(position).equals("Catch the wind")) {
                builder.setMessage(Catchthewind);
                builder.show();
            }
            if(song_list.get(position).equals("Hallelujah")) {
                builder.setMessage(Hallelujah);
                builder.show();
            }
            if(song_list.get(position).equals("Tom Dooley")) {
                builder.setMessage(TomDooley);
                builder.show();
            }
            if(song_list.get(position).equals("Where Have all the Flowers Gone")) {
                builder.setMessage(WhereHaveAllTheFlowersGone);
                builder.show();
            }
            if(song_list.get(position).equals("Puff The Magic Dragon")) {
                builder.setMessage(PuffTheMagicDragon);
                builder.show();
            }
            if(song_list.get(position).equals("At Seventeen")) {
                builder.setMessage(AtSeventeen);
                builder.show();
            }
            if(song_list.get(position).equals("Freight Train")) {
                builder.setMessage(FreightTrain);
                builder.show();
            }
            if(song_list.get(position).equals("The Boxer")) {
                builder.setMessage(TheBoxer);
                builder.show();
            }
            if(song_list.get(position).equals("Tangled up in Blue")) {
                builder.setMessage(TangledupinBlue);
                builder.show();
            }
            if(song_list.get(position).equals("Bridge Over Troubled Water")) {
                builder.setMessage(BridgeOverTroubledWater);
                builder.show();
            }
            if(song_list.get(position).equals("Thirst Boots")) {
                builder.setMessage(ThirstBoots);
                builder.show();
            }
        });

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
