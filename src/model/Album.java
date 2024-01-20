package model;

import java.util.ArrayList;

public class Album {
    private ArrayList<String> generes;
    private String title;
    private String artistName;
    private int year;
    private int month;
    private int artistId;
    private int albumId;

    public ArrayList<String> getGeneres() {
        return generes;
    }

    public void setGeneres(ArrayList<String> generes) {
        this.generes = generes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistname) {
        this.artistName = artistname;
    }
}
