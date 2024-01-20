package model;

public enum ArtistType {
    SINGLE(0),
    BAND(1);

    final public int value;

    ArtistType (int val){
        this.value = val;
    }
}
