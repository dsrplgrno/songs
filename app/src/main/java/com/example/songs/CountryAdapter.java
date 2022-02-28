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

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> implements Filterable {
    Context context;
    List<String> song_list;
    List<String> song_list_all;
    List<String> songsLyrics;
    DatabaseReference reference, fav_ref;
    String AngHulingElBimbo, Tadhana, AngHimigNatin, Narda, Binibini, SaYo, DiNaMuli, BeforeILetYouGo, TwoOneFour,
            WhyCantItBe, HanggangKailan, BeforeHeCheats, TheGambler, KissanAngelGoodMorning, RhinestoneCowboy, FriendsInLowPlaces, SomebodyLikeYou,
            TheDance, Fancy, TakeMeHomeCountryRoads, IWalktheLine, StandByYourMan, SmokyMountainRain, FancyLike, BlueEyesCryingInTheRain,
    ForeverandEverAmen, ForeverAfterAll, StrawberryWine, TheGoodOnes,ChasingAfterYou, favorite = "not_added";

    public CountryAdapter(Context context, List<String> song_list) {
        this.context = context;
        this.song_list = song_list;
        this.song_list_all = new ArrayList<>(song_list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_list_item, parent, false);

        return new CountryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        songsLyrics = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("songList").child("CountryMusic");
        reference.child("SongLyrics").addValueEventListener(new ValueEventListener() {
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
                TwoOneFour = snapshot.child("TwoOneFour").getValue(String.class);
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
            if(song_list.get(position).equals("Ang Huling El Bimbo")) {
                builder.setMessage(AngHulingElBimbo);
                builder.show();
            }
            if(song_list.get(position).equals("Tadhana")) {
                builder.setMessage(Tadhana);
                builder.show();
            }
            if(song_list.get(position).equals("Before He Cheats")) {
                builder.setMessage(BeforeHeCheats);
                builder.show();
            }
            if(song_list.get(position).equals("The Gambler")) {
                builder.setMessage(TheGambler);
                builder.show();
            }
            if(song_list.get(position).equals("Kiss an Angel Good Morning")) {
                builder.setMessage(KissanAngelGoodMorning);
                builder.show();
            }
            if(song_list.get(position).equals("Rhinestone Cowboy")) {
                builder.setMessage(RhinestoneCowboy);
                builder.show();
            }
            if(song_list.get(position).equals("Friends In Low Places")) {
                builder.setMessage(FriendsInLowPlaces);
                builder.show();
            }
            if(song_list.get(position).equals("Somebody Like You")) {
                builder.setMessage(SomebodyLikeYou);
                builder.show();
            }
            if(song_list.get(position).equals("The Dance")) {
                builder.setMessage(TheDance);
                builder.show();
            }
            if(song_list.get(position).equals("Fancy")) {
                builder.setMessage(Fancy);
                builder.show();
            }
            if(song_list.get(position).equals("Take Me Home Country Roads")) {
                builder.setMessage(TakeMeHomeCountryRoads);
                builder.show();
            }
            if(song_list.get(position).equals("I Walk the Line")) {
                builder.setMessage(IWalktheLine);
                builder.show();
            }
            if(song_list.get(position).equals("Stand By Your Man")) {
                builder.setMessage(StandByYourMan);
                builder.show();
            }
            if(song_list.get(position).equals("Smoky Mountain Rain")) {
                builder.setMessage(SmokyMountainRain);
                builder.show();
            }
            if(song_list.get(position).equals("Fancy Like")) {
                builder.setMessage(FancyLike);
                builder.show();
            }
            if(song_list.get(position).equals("Blue Eyes Crying In The Rain")) {
                builder.setMessage(BlueEyesCryingInTheRain);
                builder.show();
            }
            if(song_list.get(position).equals("Forever and Ever Amen")) {
                builder.setMessage(ForeverandEverAmen);
                builder.show();
            }
            if(song_list.get(position).equals("Forever After All")) {
                builder.setMessage(ForeverAfterAll);
                builder.show();
            }
            if(song_list.get(position).equals("Strawberry Wine")) {
                builder.setMessage(StrawberryWine);
                builder.show();
            }
            if(song_list.get(position).equals("The Good Ones")) {
                builder.setMessage(TheGoodOnes);
                builder.show();
            }
            if(song_list.get(position).equals("Chasing After You")) {
                builder.setMessage(ChasingAfterYou);
                builder.show();
            }
            if(song_list.get(position).equals("Ang Himig Natin")) {
                builder.setMessage(AngHimigNatin);
                builder.show();
            }
            if(song_list.get(position).equals("Narda")) {
                builder.setMessage(Narda);
                builder.show();
            }
            if(song_list.get(position).equals("Binibini")) {
                builder.setMessage(Binibini);
                builder.show();
            }
            if(song_list.get(position).equals("Sa'yo")) {
                builder.setMessage(SaYo);
                builder.show();
            }
            if(song_list.get(position).equals("Di Na Muli")) {
                builder.setMessage(DiNaMuli);
                builder.show();
            }
            if(song_list.get(position).equals("Before I Let You Go")) {
                builder.setMessage(BeforeILetYouGo);
                builder.show();
            }
            if(song_list.get(position).equals("214")) {
                builder.setMessage(TwoOneFour);
                builder.show();
            }
            if(song_list.get(position).equals("Why Cant It Be")) {
                builder.setMessage(WhyCantItBe);
                builder.show();
            }
            if(song_list.get(position).equals("Hanggang Kailan")) {
                builder.setMessage(HanggangKailan);
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

