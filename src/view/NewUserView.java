package view;

import controller.NewUserController;

import javax.swing.*;
import java.awt.*;

public class NewUserView {
    NewUserController controller = new NewUserController();

    JFrame frame = new JFrame("Register new user");
    JButton registerButton = new JButton("Register the account");
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

    public NewUserView(){
        controller.setView(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(350, 350));
        frame.setLocation(300, 200);

        //frame.setLayout(new GridLayout(3,1));

        textFieldUser.setPreferredSize(new Dimension(140, 19));
        textFieldPassword.setPreferredSize(new Dimension(140, 19));
        textFieldNickname.setPreferredSize(new Dimension(140, 19));
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

    public void closeView(){
        this.frame.setVisible(false);
    }

}
