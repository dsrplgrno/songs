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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    List<FavoritesModel> fav_list;
    Context context;
    DatabaseReference reference, popRef, rockRef;
    String AngHulingElBimbo, Tadhana, AngHimigNatin, Narda, Binibini, SaYo, DiNaMuli, BeforeILetYouGo, TwoOneFour,
            WhyCantItBe, HanggangKailan, CallMeMaybe, RainOnMe, UpTownFunk, BillieJean, WhenDovesCry, Butter, EasyOnMe, DejaVu, MyUniverse,
            LeaveTheDoorOpen, LikeARollingStone, WalkThisWay, Layla, SmokeOnTheWater, BrownEyeGirl, WholeLottaLove,
            FreeBird, RollingInTheDeep, SweetChildOMine, RockYouLikeAHurricane;

    public FavoriteAdapter(Context context, List<FavoritesModel> fav_list) {
        this.context = context;
        this.fav_list = fav_list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_list_item, parent, false);

        return new FavoriteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        FavoritesModel model = fav_list.get(position);
        holder.title_of_song.setText(model.getFav_title());
        holder.fav_icon.setImageResource(R.drawable.star);

        rockRef = FirebaseDatabase.getInstance().getReference("songList").child("RockMusic");
        rockRef.child("SongLyrics").addValueEventListener(new ValueEventListener() {
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        popRef = FirebaseDatabase.getInstance().getReference("songList").child("PopMusic");
        popRef.child("SongLyrics").addValueEventListener(new ValueEventListener() {
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(v -> {
            builder.setTitle(model.getFav_title());
            if(model.getFav_title().equals("Ang Huling El Bimbo")) {
                builder.setMessage(AngHulingElBimbo);
                builder.show();
            }
            if(model.getFav_title().equals("Ang Huling El Bimbo")) {
                builder.setMessage(AngHulingElBimbo);
                builder.show();
            }
            if(model.getFav_title().equals("Tadhana")) {
                builder.setMessage(Tadhana);
                builder.show();
            }
            if(model.getFav_title().equals("Ang Himig Natin")) {
                builder.setMessage(AngHimigNatin);
                builder.show();
            }
            if(model.getFav_title().equals("Narda")) {
                builder.setMessage(Narda);
                builder.show();
            }
            if(model.getFav_title().equals("Binibini")) {
                builder.setMessage(Binibini);
                builder.show();
            }
            if(model.getFav_title().equals("Sa'yo")) {
                builder.setMessage(SaYo);
                builder.show();
            }
            if(model.getFav_title().equals("Di Na Muli")) {
                builder.setMessage(DiNaMuli);
                builder.show();
            }
            if(model.getFav_title().equals("Before I Let You Go")) {
                builder.setMessage(BeforeILetYouGo);
                builder.show();
            }
            if(model.getFav_title().equals("214")) {
                builder.setMessage(TwoOneFour);
                builder.show();
            }
            if(model.getFav_title().equals("Why Cant It Be")) {
                builder.setMessage(WhyCantItBe);
                builder.show();
            }
            if(model.getFav_title().equals("Hanggang Kailan")) {
                builder.setMessage(HanggangKailan);
                builder.show();
            }
            if(model.getFav_title().equals("Call Me Maybe")) {
                builder.setMessage(CallMeMaybe);
                builder.show();
            }
            if(model.getFav_title().equals("Leave The Door Open")) {
                builder.setMessage(LeaveTheDoorOpen);
                builder.show();
            }
            if(model.getFav_title().equals("My Universe")) {
                builder.setMessage(MyUniverse);
                builder.show();
            }
            if(model.getFav_title().equals("Rain On Me")) {
                builder.setMessage(RainOnMe);
                builder.show();
            }
            if(model.getFav_title().equals("Up Town Funk")) {
                builder.setMessage(UpTownFunk);
                builder.show();
            }
            if(model.getFav_title().equals("Billie Jean")) {
                builder.setMessage(BillieJean);
                builder.show();
            }
            if(model.getFav_title().equals("When Doves Cry")) {
                builder.setMessage(WhenDovesCry);
                builder.show();
            }
            if(model.getFav_title().equals("Butter")) {
                builder.setMessage(Butter);
                builder.show();
            }
            if(model.getFav_title().equals("Easy on Me")) {
                builder.setMessage(EasyOnMe);
                builder.show();
            }
            if(model.getFav_title().equals("deja vu")) {
                builder.setMessage(DejaVu);
                builder.show();
            }
            if(model.getFav_title().equals("Brown Eyed Girl")) {
                builder.setMessage(BrownEyeGirl);
                builder.show();
            }
            if(model.getFav_title().equals("Smoke on the Water")) {
                builder.setMessage(SmokeOnTheWater);
                builder.show();
            }
            if(model.getFav_title().equals("Layla")) {
                builder.setMessage(Layla);
                builder.show();
            }
            if(model.getFav_title().equals("Walk This Way")) {
                builder.setMessage(WalkThisWay);
                builder.show();
            }
            if(model.getFav_title().equals("Like A Rolling Stone")) {
                builder.setMessage(LikeARollingStone);
                builder.show();
            }
            if(model.getFav_title().equals("Whole Lotta Love")) {
                builder.setMessage(WholeLottaLove);
                builder.show();
            }
            if(model.getFav_title().equals("Free Bird")) {
                builder.setMessage(FreeBird);
                builder.show();
            }
            if(model.getFav_title().equals("Rolling In The Deep")) {
                builder.setMessage(RollingInTheDeep);
                builder.show();
            }
            if(model.getFav_title().equals("Sweet Child O Mine")) {
                builder.setMessage(SweetChildOMine);
                builder.show();
            }
            if(model.getFav_title().equals("Rock You Like A Hurricane")) {
                builder.setMessage(RockYouLikeAHurricane);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fav_list.size();
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FavoritesModel> filteredList = new ArrayList<>();

            if(constraint.toString().isEmpty()) {
                filteredList.addAll(fav_list);
            }else {
                for (FavoritesModel song : fav_list) {
                    if(song.getFav_title().toLowerCase().contains(constraint.toString().toLowerCase())) {
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
            fav_list.clear();
            fav_list.addAll((Collection<? extends FavoritesModel>) results.values);
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
