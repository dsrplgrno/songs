package com.example.songs;

public class RockSongModel {
    String title, lyrics;

    public RockSongModel() {
    }

    public RockSongModel(String title, String lyrics) {
        this.title = title;
        this.lyrics = lyrics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
