package controller;

import model.UserProfile;
import repository.UserMenuRepo;
import view.FindView;
import view.LikedSongsView;
import view.LoginView;
import view.UserMenuView;

import javax.swing.*;

public class UserMenuController {
    UserMenuView view = null;
    UserMenuRepo repo = new UserMenuRepo();

    int accountId;
    UserProfile profile = null;

    public void createProfile(int accountId){
        this.accountId = accountId;

        this.profile = repo.retrieveUser(this.accountId);

        if (profile == null){
            System.out.println("BIG PROBLEM at DB, please log in again!!");
        }
    }

    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public UserProfile getProfile() {
        return profile;
    }
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
    public void setView(UserMenuView view) {
        this.view = view;
    }

    public void goToLiked() {
        LikedSongsView newView = new LikedSongsView(profile);
        this.view.closeView();
    }

    public void logOut(){
        LoginView newView = new LoginView();
        this.view.closeView();
    }

    public void goToFind() {
        FindView newView = new FindView(profile);
        this.view.closeView();
    }

    public void changeUsername() {

        String userInput = JOptionPane.showInputDialog("Enter your new nickname:");

        if (userInput == null || userInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You canceled or entered an empty username.");
            return;
        }

        int result = repo.updateNickname(profile.getId(), userInput);

        if(result == -1)
            JOptionPane.showMessageDialog(null, "Error at updating nickname.");
        else{
            JOptionPane.showMessageDialog(null, "Nickname updated successfully.");
            profile.setNickname(userInput);
        }


    }
}
