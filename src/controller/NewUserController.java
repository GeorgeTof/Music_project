package controller;

import repository.NewUserRepo;
import view.LoginView;
import view.NewUserView;

import javax.swing.*;
import java.util.Objects;

public class NewUserController {
    private NewUserView view;
    private NewUserRepo repo = new NewUserRepo();

    public NewUserView getView() {
        return view;
    }
    public void setView(NewUserView view) {
        this.view = view;
    }

    public void checkFields(){
        if(Objects.equals(view.getUsername(), "")){
            JOptionPane.showMessageDialog(null, "Empty username row!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(Objects.equals(view.getNickname(), "")){
            JOptionPane.showMessageDialog(null, "Empty nickname row!", "", JOptionPane.WARNING_MESSAGE);
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
        if(result == -1){
            JOptionPane.showMessageDialog(null, "Error at database level!", "", JOptionPane.WARNING_MESSAGE);
        }
        else if (result == 0){
            System.out.println("Insert new user in database");
            insertAccount(view.getUsername(), view.getPassword());
        }
        else{
            JOptionPane.showMessageDialog(null, "Username already exists!", "", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void insertAccount(String user, String pass){
        int result = repo.addAccount(user, pass);
        if (result == -1){
            JOptionPane.showMessageDialog(null, "Error at creating new user (code 1)", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int accountId = repo.verifyUser(user);
        result = repo.addUser(accountId, view.getNickname());
        if (result == -1){
            JOptionPane.showMessageDialog(null, "Error at creating new user (code 2)", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "User created successfully", "", JOptionPane.INFORMATION_MESSAGE);
        LoginView newView = new LoginView();
        view.closeView();
    }

}
