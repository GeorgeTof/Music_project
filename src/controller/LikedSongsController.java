package controller;

import view.SongBanner;
import model.SongData;
import model.Review;
import model.UserProfile;
import repository.LikedSongsRepo;
import view.LikedSongsView;
import view.SongPageReviewedView;
import view.SongPageView;
import view.UserMenuView;

import java.util.ArrayList;

public class LikedSongsController {

    LikedSongsView view = null;
    LikedSongsRepo repo = new LikedSongsRepo();
    UserProfile profile = null;

    ArrayList<SongData> songs;
    ArrayList<SongBanner> banners = new ArrayList<SongBanner>();

    public void fetchSongs(){
        songs = repo.getSongs(profile.getId());

        for(SongData s : songs){
            System.out.println(s);
            banners.add(new SongBanner(s));
        }
    }

    public void goToSong (int no){
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



    public void backToMenu(){
        UserMenuView newView = new UserMenuView(profile);
        this.view.closeView();
    }

    public ArrayList<SongBanner> getBanners() {
        return banners;
    }
    public UserProfile getProfile() {
        return profile;
    }
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
    public void setView(LikedSongsView view) {
        this.view = view;
    }

}
