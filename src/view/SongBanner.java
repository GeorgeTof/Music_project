package view;

import model.SongData;
import view.UIColors;

import javax.swing.*;
import java.awt.*;

public class SongBanner {
    JLabel name = new JLabel();
    JLabel artist = new JLabel();
    JLabel album = new JLabel();
    JButton playButton = new JButton("select");
    JPanel bannerPanel = new JPanel();
    JPanel playPanel = new JPanel(new GridBagLayout());
    int songId;



    public SongBanner(SongData data){
        songId = data.getSid();
        name.setText(data.getName());
        name.setPreferredSize(new Dimension(100, 50));
        artist.setText(data.getArtist());
        artist.setPreferredSize(new Dimension(100, 50));
        album.setText(data.getAlbum());
        album.setPreferredSize(new Dimension(100, 50));
        playButton.setFocusable(false);
        playButton.setPreferredSize(new Dimension(80, 45));
        bannerPanel.setPreferredSize(new Dimension(400, 60));
        bannerPanel.setLayout(new GridLayout(1, 4, 10, 10));

        bannerPanel.add(name);
        bannerPanel.add(artist);
        bannerPanel.add(album);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        playPanel.add(playButton, gbc);
        bannerPanel.add(playPanel);

        playPanel.setBackground(UIColors.RESULT.color);
        bannerPanel.setBackground(UIColors.RESULT.color);


    }

    public int getSongId() {
        return songId;
    }
    public JButton getPlayButton() {
        return playButton;
    }
    public JPanel getBannerPanel() {
        return bannerPanel;
    }


}
