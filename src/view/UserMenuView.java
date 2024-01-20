package view;

import controller.UserMenuController;
import model.UserProfile;

import javax.swing.*;
import java.awt.*;

public class UserMenuView {

    UserMenuController controller = new UserMenuController();

    JFrame frame = new JFrame("Main Menu");
    JButton likedButton = new JButton("Liked songs");
    JButton findButton = new JButton("Find music");
    JButton renameButton = new JButton("Change nickname");
    JButton logOutButton = new JButton("Log Out");

    JLabel labelUser = new JLabel("Username: ");
    JTextField textFieldUser = new JTextField();
    JLabel labelNickname = new JLabel("Nickname: ");
    JTextField textFieldNickname = new JTextField();
    JLabel labelPassword = new JLabel("Password: ");
    JTextField textFieldPassword = new JTextField();

    JPanelNotOpaque panel1 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel2 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel3 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panelAux = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel4 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel5 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));

    public UserMenuView(int id){

        controller.createProfile(id);
        controller.setView(this);

        //controller.setView(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(350, 350));
        //frame.setLayout(new GridLayout(3,1));
        frame.setLocation(300, 200);


        textFieldUser.setPreferredSize(new Dimension(140, 19));
        textFieldPassword.setPreferredSize(new Dimension(140, 19));
        textFieldNickname.setPreferredSize(new Dimension(140, 19));

        likedButton.setFocusable(false);
        likedButton.setPreferredSize(new Dimension(200, 30));
        findButton.setFocusable(false);
        findButton.setPreferredSize(new Dimension(200, 30));
        logOutButton.setFocusable(false);
        logOutButton.setPreferredSize(new Dimension(140, 20));
        renameButton.setFocusable(false);
        renameButton.setPreferredSize(new Dimension(140, 20));

        panel1.add(likedButton);
        panelAux.add(panel1);
        panel2.add(findButton);
        panelAux.add(panel2);
        frame.add(panelAux, BorderLayout.CENTER);

        panel3.add(renameButton);
        panel5.add(panel3);
        panel4.add(logOutButton);
        panel5.add(panel4);
        frame.add(panel5, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

        renameButton.addActionListener(e -> controller.changeUsername());
        findButton.addActionListener(e -> controller.goToFind());
        likedButton.addActionListener(e -> controller.goToLiked());
        logOutButton.addActionListener(e -> controller.logOut());
        //registerButton.addActionListener(e -> controller.checkFields());

    }

    public UserMenuView(UserProfile profile){       // de adaptat ulterior

        controller.setProfile(profile);
        controller.setView(this);

        //controller.setView(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(350, 350));
        //frame.setLayout(new GridLayout(3,1));
        frame.setLocation(300, 200);

        textFieldUser.setPreferredSize(new Dimension(140, 19));
        textFieldPassword.setPreferredSize(new Dimension(140, 19));
        textFieldNickname.setPreferredSize(new Dimension(140, 19));

        likedButton.setFocusable(false);
        likedButton.setPreferredSize(new Dimension(200, 30));
        findButton.setFocusable(false);
        findButton.setPreferredSize(new Dimension(200, 30));
        logOutButton.setFocusable(false);
        logOutButton.setPreferredSize(new Dimension(140, 20));
        renameButton.setFocusable(false);
        renameButton.setPreferredSize(new Dimension(140, 20));

        panel1.add(likedButton);
        panelAux.add(panel1);
        panel2.add(findButton);
        panelAux.add(panel2);
        frame.add(panelAux, BorderLayout.CENTER);

        panel3.add(renameButton);
        panel5.add(panel3);
        panel4.add(logOutButton);
        panel5.add(panel4);
        frame.add(panel5, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

        renameButton.addActionListener(e -> controller.changeUsername());
        findButton.addActionListener(e -> controller.goToFind());
        likedButton.addActionListener(e -> controller.goToLiked());
        logOutButton.addActionListener(e -> controller.logOut());
        //registerButton.addActionListener(e -> controller.checkFields());

    }

    public int getUserId(){return this.controller.getAccountId();}
    public UserProfile getProfile(){return controller.getProfile();}

    public void closeView(){
        this.frame.setVisible(false);
    }
}
