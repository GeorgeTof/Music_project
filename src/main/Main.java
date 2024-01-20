package main;

import model.DBcredentials;
import repository.LoginRepo;
import view.LoginView;
import view.UserMenuView;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome!");

//        UserMenuView view1 = new UserMenuView(1);
        LoginView viewLogIn = new LoginView();

    }
}