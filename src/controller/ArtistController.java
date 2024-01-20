package controller;

import model.*;
import repository.ArtistRepo;
import view.*;

import javax.swing.*;
import java.util.ArrayList;

public class ArtistController {
    Artist artist;
    ArtistView view;
    ArtistRepo repo = new ArtistRepo();
    UserProfile profile;

    ArrayList<Album> albums;
    ArrayList<AlbumBanner> banners = new ArrayList<AlbumBanner>();

    public void fetchAlbums(){
        albums = repo.getAlbums(artist.getArtistId());

        System.out.println("The "+ albums.size() +" albums written by the artist are: ");
        for(Album a : albums){
            System.out.println(a.getTitle());
            banners.add(new AlbumBanner(a));
        }
        System.out.println("");

    }

    public void fetchArtist(String artistName) {
        this.artist = repo.getArtist(artistName);
        if(this.artist == null){
            System.out.println("ERROR AT DB FETCHING ARTIST");
            return;
        }
    }

    public void backToMenu() {
        UserMenuView nv = new UserMenuView(profile);
        view.closeView();
    }

    public void goToAlbum(int no){
        AlbumView nv = new AlbumView(profile, albums.get(no).getTitle());
        view.closeView();
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public ArtistView getView() {
        return view;
    }

    public void setView(ArtistView view) {
        this.view = view;
    }

    public ArtistRepo getRepo() {
        return repo;
    }

    public void setRepo(ArtistRepo repo) {
        this.repo = repo;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public ArrayList<AlbumBanner> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<AlbumBanner> banners) {
        this.banners = banners;
    }


    public void logOut() {
        LoginView newView = new LoginView();
        this.view.closeView();
    }

    public void editBio() {

        String userInput = JOptionPane.showInputDialog(null, "Enter your new bio:");

        if (userInput != null) {
            int result = repo.updateBio(userInput, artist.getArtistId());

            if(result < 1 ){
                JOptionPane.showMessageDialog(null, "Error updating input!");
            }

            else{
                JOptionPane.showMessageDialog(null, "Bio updated successfully!");

                ArtistView newView = new ArtistView(artist.getArtistName());
                this.view.closeView();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "You canceled the update.", "Canceled", JOptionPane.WARNING_MESSAGE);
        }

    }
}
