package controller;

import model.Review;
import model.SongData;
import model.UserProfile;
import repository.SongPageRepo;
import view.*;

import javax.swing.*;
import java.util.ArrayList;

public class SongPageController {
    SongPageView view;
    UserProfile profile;
    SongData song;
    SongPageRepo repo = new SongPageRepo();
    Review myReview;

    public SongPageController(SongPageView view, UserProfile profile, SongData song) {
        this.view = view;
        this.profile = profile;
        this.song = song;
    }

    public Review getMyReview() {
        return myReview;
    }

    public void setMyReview(Review myReview) {
        this.myReview = myReview;
    }

    public SongPageController(SongPageView view, UserProfile profile, SongData song, Review review) {
        this.view = view;
        this.profile = profile;
        this.song = song;
        this.myReview = review;
    }

    public void populateReviews() {
        ArrayList<Review> reviews = repo.getReviews(profile.getId(), song.getSid());
        for(Review r : reviews){
            Object[] rowData = {r.getUsername(), ("( " + r.getNickname() + " )"), (r.getRating() + " / 5"), r.getText() };
            view.addReview(rowData);
        }
    }

    public void goToArtist() {
        ArtistView nv = new ArtistView(profile, song.getArtist());
        view.closeView();
    }

    public void goToAlbum() {
        AlbumView nv = new AlbumView(profile, song.getAlbum());
        view.closeView();
    }

    public void likePressed() {
        int removedFromLiked = repo.removeFromLiked(song.getSid(), profile.getId());

        if(removedFromLiked > 0){
            JOptionPane.showMessageDialog(null, "Removed from liked songs!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else if (removedFromLiked == -1){
            JOptionPane.showMessageDialog(null, "Error at database level removing!", "", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int addToLiked = repo.addToLiked(song.getSid(), profile.getId());
        if(addToLiked > 0){
            JOptionPane.showMessageDialog(null, "Added to liked songs!", "", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Error at database level adding!", "", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void setView(SongPageView view) {
        this.view = view;
    }

    public void backToMenu() {
        UserMenuView nview = new UserMenuView(profile);
        this.view.closeView();
    }

    public void addReview() {
        int rating = view.getComboBoxrating();
        String text = view.getReviewText();
        if(text.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty text!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int result = repo.addReview(rating, text, profile.getId(), song.getSid());
        if(result != -1){
            JOptionPane.showMessageDialog(null, "Review posted successfully!", "", JOptionPane.INFORMATION_MESSAGE);
            Review r = repo.getThisReview(profile.getId(), song.getSid());
            System.out.println(r.getText() + " fetched successfully after insertion");
            SongPageReviewedView newview = new SongPageReviewedView(profile, song, r);
            view.closeView();
        }
        else{
            JOptionPane.showMessageDialog(null, "Error adding review!", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeReview() {

        int result = repo.deleteReview(myReview.getRevId());
        if (result == -1){
            JOptionPane.showMessageDialog(null, "Error deleting review!", "", JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Review deleted successfully!", "", JOptionPane.INFORMATION_MESSAGE);
            SongPageView newview = new SongPageView(profile, song, null);
            view.closeView();
        }

    }
}
