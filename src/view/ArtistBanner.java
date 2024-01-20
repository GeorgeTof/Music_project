package view;

import model.Artist;
import view.UIColors;

import javax.swing.*;
import java.awt.*;

public class ArtistBanner {
    JLabel name = new JLabel();
    JLabel country = new JLabel();
    JLabel isBand = new JLabel();
    JButton playButton = new JButton("select");
    JPanel bannerPanel = new JPanel();
    JPanel playPanel = new JPanel(new GridBagLayout());
    Artist artist;



    public ArtistBanner(Artist art){
        artist = art;
        name.setText(artist.getArtistName());
        name.setPreferredSize(new Dimension(100, 50));
        country.setText(artist.getCountry());
        country.setPreferredSize(new Dimension(100, 50));
        isBand.setText(String.valueOf( ((artist.getIsBand() == 1)?"band":"single")) );
        isBand.setPreferredSize(new Dimension(100, 50));
        playButton.setFocusable(false);
        playButton.setPreferredSize(new Dimension(80, 45));
        bannerPanel.setPreferredSize(new Dimension(400, 60));
        bannerPanel.setLayout(new GridLayout(1, 4, 10, 10));

        bannerPanel.add(name);
        bannerPanel.add(country);
        bannerPanel.add(isBand);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        playPanel.add(playButton, gbc);
        bannerPanel.add(playPanel);

        playPanel.setBackground(UIColors.RESULT.color);
        bannerPanel.setBackground(UIColors.RESULT.color);

    }

    public Artist getArtist() {
        return artist;
    }
    public JButton getPlayButton() {
        return playButton;
    }
    public JPanel getBannerPanel() {
        return bannerPanel;
    }
}
