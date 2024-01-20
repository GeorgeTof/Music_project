package model;

public class SongData {

    int sid;
    String name;
    String album;
    String artist;
    int length;
    int year;
    Review personalReview;

    public Review getReview() {
        return personalReview;
    }

    public void setReview(Review review) {
        this.personalReview = review;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return sid+" "+name+" "+album+" "+artist+" "+length+" "+year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

}
