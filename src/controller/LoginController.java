package controller;

import repository.LoginRepo;
import view.*;

import javax.swing.*;
import java.util.Objects;

public class LoginController {
    private LoginView view;
    private LoginRepo repo = new LoginRepo();

    public LoginView getView() {
        return view;
    }
    public void setView(LoginView view) {
        this.view = view;
    }

    public void checkUser(){
        System.out.println("."+view.getUsername()+"."+view.getPassword()+".");
        if(Objects.equals(view.getUsername(), "")){
            JOptionPane.showMessageDialog(null, "Empty username row!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(Objects.equals(view.getPassword(), "")){
            JOptionPane.showMessageDialog(null, "Empty password row!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int result = repo.verifyLogin(view.getUsername(), view.getPassword());
        if (result == 0){
            System.out.println("No such username");
            JOptionPane.showMessageDialog(null, "Incorrect username or password!", "Failed log in", JOptionPane.ERROR_MESSAGE);
        }
        else if(result == 1){
            System.out.println("User found!");

            System.out.println("\nDEBUG");
            System.out.println(view.getUsername());
            System.out.println(repo.verifyUser(view.getUsername()));
            System.out.println("DEBUG\n");

            UserMenuView newView = new UserMenuView(repo.verifyUser(view.getUsername()));
            view.closeView();
        }
        else if (result == 2) {
            System.out.println("Artist found!");

            String artistName = repo.verifyArtist(view.getUsername());

            if(artistName == null){
                System.out.println("Error at database level fetching artist name!");
                return;
            }

            System.out.println("\nDEBUG");
            System.out.println(view.getUsername());
            System.out.println(artistName);
            System.out.println("DEBUG\n");

            ArtistView newView = new ArtistView(artistName);
            this.view.closeView();

        }
        else
            System.out.println("WTF wrong result");
    }

    public void registerNew(){
        String[] options = {"Register new user", "Register new artist"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "What kind of account do you wnt to create?",
                "Please select",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        System.out.println("Selected option " + choice);
        if(choice == 1){
            NewArtistView newView = new NewArtistView();
            view.closeView();
        }
        else{
            NewUserView newView = new NewUserView();
            view.closeView();
        }
    }


}
