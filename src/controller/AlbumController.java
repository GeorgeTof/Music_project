package controller;

import model.*;
import repository.AlbumRepo;
import view.*;

import java.util.ArrayList;

public class AlbumController {
    Album album;
    AlbumView view;
    AlbumRepo repo = new AlbumRepo();
    UserProfile profile;

    ArrayList<SongData> songs;
    ArrayList<SongBanner> banners = new ArrayList<SongBanner>();

    public void fetchSongs(){
        songs = repo.getSongs(album.getAlbumId());

        System.out.println("The songs int the album are: ");
        System.out.println(songs.size());
        for(SongData s : songs){
            System.out.print("X ");
            System.out.println(s);
            banners.add(new SongBanner(s));
        }

    }

    public void fetchAlbum(String albumName) {
        this.album = repo.getAlbum(albumName);
        if(this.album == null){
            System.out.println("ERROR AT DB FETCHING ALBUM");
            return;
        }

        fetchGeneres();
    }

    public void fetchGeneres() {
        this.album.setGeneres(repo.getGeneres(album.getAlbumId()));
        System.out.println("generes:");
        for (String s : album.getGeneres()){
            System.out.println(s);
        }
    }

    public AlbumView getView() {
        return view;
    }

    public void setView(AlbumView view) {
        this.view = view;
    }

    public ArrayList<SongBanner> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<SongBanner> banners) {
        this.banners = banners;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void backToMenu() {
        UserMenuView nv = new UserMenuView(profile);
        view.closeView();
    }

    public void goToArtist(){
        ArtistView nv = new ArtistView(profile, album.getArtistName());
        view.closeView();
    }

    public void goToSong(int no) {
        Review thisReview = repo.getPersonalReview(songs.get(no).getSid(), profile.getId());
        if(thisReview == null){
            System.out.println("No review on this song");
            SongPageView newView = new SongPageView(profile, songs.get(no), null);
            view.closeView();
        }
        else{
            SongPageView newView = new SongPageReviewedView(profile, songs.get(no), thisReview);
            System.out.println("review: "+ thisReview.getRating() + " " + thisReview.getText());
            view.closeView();
        }
    }
}
