package view;

import controller.LikedSongsController;
import model.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LikedSongsView {

    LikedSongsController controller = new LikedSongsController();

    JLabel titleLabel = new JLabel();
    JFrame frame = new JFrame("Liked songs");
    JButton backButton = new JButton("Back to menu");

    ArrayList<JPanelNotOpaque> songPanels;

    JPanelNotOpaque panel1 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panelAux = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JScrollPane scrollPane;

    public LikedSongsView(UserProfile profile){

        controller.setView(this);
        controller.setProfile(profile);

        controller.fetchSongs();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 350));
        frame.setLocation(300, 200);

        frame.setLayout(new BorderLayout());

        titleLabel.setText(profile.getNickname()+"'s liked songs:");
        titleLabel.setFont(new Font("Dialog.bold", Font.ITALIC, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setFocusable(false);
        backButton.setPreferredSize(new Dimension(150, 30));

        frame.add(titleLabel, BorderLayout.NORTH);

        scrollPane = new JScrollPane(panelAux);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));

        for(SongBanner sb : controller.getBanners()){
            panelAux.add(sb.getBannerPanel());
        }
        frame.add(scrollPane, BorderLayout.CENTER);

        panel1.add(backButton);
        frame.add(panel1, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

        backButton.addActionListener(e -> controller.backToMenu());
        for(int i = 0; i<controller.getBanners().size(); i++){
            final int no = i;
            controller.getBanners().get(i).getPlayButton().addActionListener(e -> controller.goToSong(no));
        }

    }

    public void closeView(){
        this.frame.setVisible(false);
    }

}
