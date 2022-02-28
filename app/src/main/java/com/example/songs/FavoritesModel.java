package com.example.songs;

public class FavoritesModel {
    String fav_title;

    public FavoritesModel() {
    }

    public FavoritesModel(String fav_title) {
        this.fav_title = fav_title;
    }

    public String getFav_title() {
        return fav_title;
    }

    public void setFav_title(String fav_title) {
        this.fav_title = fav_title;
    }
}
