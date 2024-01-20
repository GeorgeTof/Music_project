package controller;

import model.ArtistType;
import repository.NewArtistRepo;
import repository.NewUserRepo;
import view.LoginView;
import view.NewArtistView;
import view.NewUserView;

import javax.swing.*;
import java.util.Objects;

public class NewArtistController {
    private NewArtistView view;
    private NewArtistRepo repo = new NewArtistRepo();

    public NewArtistView getView() {
        return view;
    }
    public void setView(NewArtistView view) {
        this.view = view;
    }

    public void checkFields(){
        if(Objects.equals(view.getUsername(), "")){
            JOptionPane.showMessageDialog(null, "Empty username row!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(Objects.equals(view.getNickname(), "")){
            JOptionPane.showMessageDialog(null, "Empty name row!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(Objects.equals(view.getPassword(), "")){
            JOptionPane.showMessageDialog(null, "Empty password row!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        checkUsername();
    }

    public void checkUsername(){
        int result = repo.verifyUser(view.getUsername());
        System.out.println("DEBUG: " + result + "\n");
        if(result == -1){
            JOptionPane.showMessageDialog(null, "Error at database level!", "", JOptionPane.WARNING_MESSAGE);
        }
        else if (result == 0){
            System.out.println("Insert new artist in database");
            insertAccount(view.getUsername(), view.getPassword());
        }
        else{
            JOptionPane.showMessageDialog(null, "Username already exists!", "", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void insertAccount(String user, String pass){
        int result = repo.addAccount(user, pass);
        if (result == -1){
            JOptionPane.showMessageDialog(null, "Error at creating new artist (code 1)", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int accountId = repo.verifyUser(user);
        int countryId = repo.fetchCountryId(view.getCountry());
        if(countryId == -1){
            JOptionPane.showMessageDialog(null, "Error at creating new artist (code 3)", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int isBand = (Objects.equals(view.getIsBand(), "Single"))? ArtistType.SINGLE.value : ArtistType.BAND.value;
        String bio = view.getBio();
        System.out.println("Attempting to insert "+accountId+" "+countryId+" "+isBand+" "+bio);

        result = repo.addArtist(accountId, view.getNickname(), isBand, countryId, bio);
        if (result == -1){
            JOptionPane.showMessageDialog(null, "Error at creating new artist (code 2)", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Arist page created successfully", "", JOptionPane.INFORMATION_MESSAGE);
        LoginView newView = new LoginView();
        view.closeView();
    }

}
