package view;

import controller.NewArtistController;
import controller.NewUserController;

import javax.swing.*;
import java.awt.*;

public class NewArtistView {
    NewArtistController controller = new NewArtistController();

    JFrame frame = new JFrame("Register new artist");
    JButton registerButton = new JButton("Register the account");
    JLabel labelUser = new JLabel("Username: ");
    JTextField textFieldUser = new JTextField();
    JLabel labelNickname = new JLabel("Name: ");
    JTextField textFieldNickname = new JTextField();
    JLabel labelPassword = new JLabel("Password: ");
    JTextField textFieldPassword = new JTextField();
    JLabel labelBio = new JLabel("Bio: ");
    JTextField textFieldBio = new JTextField();
    JLabel labelCountry = new JLabel("Country: ");
    JComboBox<String> countryComboBox = new JComboBox<>(new String[]{"USA", "UK", "Germany", "Romania"});
    JLabel labelIsBand = new JLabel("Band or single artist: ");
    JComboBox<String> isBandComboBox = new JComboBox<>(new String[]{"Band", "Single"});

    JPanelNotOpaque panel1 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel2 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel3 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panelAux = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel4 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel5 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel6 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel7 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));

    public NewArtistView(){
        controller.setView(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(350, 350));
        frame.setLocation(300, 200);

        //frame.setLayout(new GridLayout(3,1));

        textFieldUser.setPreferredSize(new Dimension(140, 19));
        textFieldPassword.setPreferredSize(new Dimension(140, 19));
        textFieldNickname.setPreferredSize(new Dimension(140, 19));
        textFieldBio.setPreferredSize(new Dimension(140, 19));
        countryComboBox.setPreferredSize(new Dimension(140, 19));
        isBandComboBox.setPreferredSize(new Dimension(140, 19));
        registerButton.setFocusable(false);
        registerButton.setPreferredSize(new Dimension(200, 30));


        panel1.add(labelUser);
        panel1.add(textFieldUser);
        panelAux.add(panel1);
        panel2.add(labelNickname);
        panel2.add(textFieldNickname);
        panelAux.add(panel2);
        panel3.add(labelPassword);
        panel3.add(textFieldPassword);
        panelAux.add(panel3);
        panel5.add(labelBio);
        panel5.add(textFieldBio);
        panelAux.add(panel5);
        panel6.add(labelCountry);
        panel6.add(countryComboBox);
        panelAux.add(panel6);
        panel7.add(labelIsBand);
        panel7.add(isBandComboBox);
        panelAux.add(panel7);


        frame.add(panelAux, BorderLayout.CENTER);

        panel4.add(registerButton);
        frame.add(panel4, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

        registerButton.addActionListener(e -> controller.checkFields());

    }

    public String getUsername(){
        return textFieldUser.getText();
    }
    public String getPassword(){
        return textFieldPassword.getText();
    }
    public String getNickname(){
        return textFieldNickname.getText();
    }
    public String getBio(){
        return textFieldBio.getText();
    }
    public String getIsBand() {return (String) isBandComboBox.getSelectedItem();}
    public String getCountry() {return (String) countryComboBox.getSelectedItem();}

    public void closeView(){
        this.frame.setVisible(false);
    }

}
