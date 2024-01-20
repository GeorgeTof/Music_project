package view;

import model.Album;
import view.UIColors;

import javax.swing.*;
import java.awt.*;

public class AlbumBanner {
    JLabel name = new JLabel();
    JLabel artist = new JLabel();
    JLabel year = new JLabel();
    JButton playButton = new JButton("select");
    JPanel bannerPanel = new JPanel();
    JPanel playPanel = new JPanel(new GridBagLayout());
    Album album;



    public AlbumBanner(Album alb){
        album = alb;
        name.setText(album.getTitle());
        name.setPreferredSize(new Dimension(100, 50));
        artist.setText(album.getArtistName());
        artist.setPreferredSize(new Dimension(100, 50));
        year.setText(String.valueOf(album.getYear()));
        year.setPreferredSize(new Dimension(100, 50));
        playButton.setFocusable(false);
        playButton.setPreferredSize(new Dimension(80, 45));
        bannerPanel.setPreferredSize(new Dimension(400, 60));
        bannerPanel.setLayout(new GridLayout(1, 4, 10, 10));

        bannerPanel.add(name);
        bannerPanel.add(artist);
        bannerPanel.add(year);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        playPanel.add(playButton, gbc);
        bannerPanel.add(playPanel);

        playPanel.setBackground(UIColors.RESULT.color);
        bannerPanel.setBackground(UIColors.RESULT.color);

    }

    public Album getAlbum() {
        return album;
    }
    public JButton getPlayButton() {
        return playButton;
    }
    public JPanel getBannerPanel() {
        return bannerPanel;
    }

}
