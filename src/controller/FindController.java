package controller;

import model.*;
import repository.FindRepo;
import view.*;

import java.util.ArrayList;
import java.util.Objects;

public class FindController {

    FindView view;
    FindRepo repo = new FindRepo();
    UserProfile profile;

    ArrayList<SongData> foundSongs;
    ArrayList<Album> foundAlbums;
    ArrayList<Artist> foundArtists;

    ArrayList<SongBanner> songBanners;
    ArrayList<AlbumBanner> albumBanners;
    ArrayList<ArtistBanner> artistBanners;


    public void search() {
        String in = view.getSearchIn();

        switch (in) {
            case "Songs":
                searchSongs();
                break;
            case "Albums":
                searchAlbums();
                break;
            case "Artists":
                searchArtists();
                break;
            default:
                System.out.println("ERROR! Somehow invalid choice");
        }

    }

    private void searchArtists(){
        String searchtext = view.getSearchText();
        String ascDesc;

        if(Objects.equals(view.getOrder(), "Asc."))
            ascDesc = "ASC";
        else
            ascDesc = "DESC";

        searchtext = "%" + searchtext + "%";

        System.out.println("Searching for artist like " + searchtext + " order by " +  "NAME" + " " +  ascDesc);

        foundArtists = repo.getArtists(searchtext, ascDesc);

        artistBanners = new ArrayList<ArtistBanner>();
        try {
            System.out.println( foundArtists.size() + " Retrieved artists are: ");
            for (Artist a : foundArtists) {
                System.out.println("Found " + a.getArtistName());
                artistBanners.add(new ArtistBanner(a));
            }

            view.updateOutputPanel(artistBanners, null);
        }
        catch (NullPointerException e) {
            System.out.println(" PROBLEM: Empty list of artists retrieved \n " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Unknown error");
            e.printStackTrace();
        }

    }

    private void searchAlbums(){
        String searchtext = view.getSearchText();
        String orderBy;
        String ascDesc;
        String year;
        String genere = view.getFilter1();

        if(Objects.equals(genere, "None"))
            genere = null;
        else
            genere = " and generename = '" + genere + "' ";

        if(Objects.equals(view.getSortBy(), "Name"))
            orderBy = "albname";
        else
            orderBy = "year";

        if(Objects.equals(view.getOrder(), "Asc."))
            ascDesc = "ASC";
        else
            ascDesc = "DESC";

        switch(view.getFilter2()) {
            case "2010s":
                year = "and year >= 2010";
                break;
            case "2000s":
                year = "and year >= 2000 and year < 2010";
                break;
            case "90s":
                year = "and year >= 1990 and year < 2000";
                break;
            case "80s":
                year = "and year >= 1980 and year < 1990";
                break;
            case "70s":
                year = "and year >= 1970 and year < 1980";
                break;
            case "Older":
                year = "and year < 1970";
                break;
            default:
                year = null;

        }

        System.out.println("Searching for album like " + searchtext + " genere=" + genere + " order by " +  orderBy + " " +  ascDesc + " " + year);

        searchtext = "%" + searchtext + "%";

        foundAlbums = repo.getAlbums(searchtext, genere, orderBy, ascDesc, year);
        albumBanners = new ArrayList<AlbumBanner>();
        System.out.println("\nRetrieved albums:");
        for(Album a : foundAlbums){
            System.out.println(a.getTitle()+" "+a.getAlbumId()+" "+a.getArtistName()+" "+a.getYear());
            albumBanners.add(new AlbumBanner(a));
        }

        view.updateOutputPanel(albumBanners, 0);

    }


    private void searchSongs(){
        String searchtext = view.getSearchText();
        String orderBy;
        String ascDesc;
        String year;

        if(Objects.equals(view.getSortBy(), "Name"))
            orderBy = "songname";
        else
            orderBy = "year";

        if(Objects.equals(view.getOrder(), "Asc."))
            ascDesc = "ASC";
        else
            ascDesc = "DESC";

        switch(view.getFilter2()) {
            case "2010s":
                year = "and year >= 2010";
                break;
            case "2000s":
                year = "and year >= 2000 and year < 2010";
                break;
            case "90s":
                year = "and year >= 1990 and year < 2000";
                break;
            case "80s":
                year = "and year >= 1980 and year < 1990";
                break;
            case "70s":
                year = "and year >= 1970 and year < 1980";
                break;
            case "Older":
                year = "and year < 1970";
                break;
            default:
                year = null;

        }

        System.out.println("Searching for song like " + searchtext + " order by " +  orderBy + " " +  ascDesc + " " + year);

        searchtext = "%" + searchtext + "%";

        foundSongs = repo.getSongs(searchtext, orderBy, ascDesc, year);

        songBanners = new ArrayList<SongBanner>();
        System.out.println("\nRetrieved songs:");
        for(SongData s : foundSongs){
            System.out.println(s.getName());
            songBanners.add(new SongBanner(s));
        }

        view.updateOutputPanel(songBanners);

    }



    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public FindView getView() {
        return view;
    }

    public void setView(FindView view) {
        this.view = view;
    }

    public void backToMenu() {
        UserMenuView nv = new UserMenuView(profile);
        view.closeView();
    }

    public void goToSong(int no) {
        Review thisReview = repo.getPersonalReview(foundSongs.get(no).getSid(), profile.getId());
        if(thisReview == null){
            System.out.println("No review on this song");
            SongPageView newView = new SongPageView(profile, foundSongs.get(no), null);
            view.closeView();
        }
        else{
            SongPageView newView = new SongPageReviewedView(profile, foundSongs.get(no), thisReview);
            System.out.println("review: "+ thisReview.getRating() + " " + thisReview.getText());
            view.closeView();
        }
    }

    public void goToAlbum(int no) {
        AlbumView nv = new AlbumView(profile, foundAlbums.get(no).getTitle());
        view.closeView();
    }

    public void goToArtist(int no) {
        ArtistView nv = new ArtistView(profile, foundArtists.get(no).getArtistName());
        view.closeView();
    }

}
