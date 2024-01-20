package view;

import controller.AlbumController;
import model.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlbumView {

    AlbumController controller = new AlbumController();

    JLabel titleLabel = new JLabel();
    JFrame frame = new JFrame("Liked songs");
    JButton backButton = new JButton("Back to menu");

    ArrayList<JPanelNotOpaque> songPanels;

    JPanelNotOpaque detailsPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));

    JPanelNotOpaque panel1 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panelAux = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JScrollPane scrollPane;

    public AlbumView(UserProfile profile, String albumName){
        controller.fetchAlbum(albumName);
        controller.setView(this);
        controller.setProfile(profile);
        controller.fetchSongs();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700, 450));
        frame.setLocation(300, 200);
        //frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
//        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.setLayout(new BorderLayout());

        this.setupDetailsPanel();
        frame.add(detailsPanel, BorderLayout.NORTH);

        backButton.setFocusable(false);
        JPanelNotOpaque buttonFrame = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
        buttonFrame.add(backButton);
        backButton.addActionListener(e -> controller.backToMenu());
        frame.add(buttonFrame, BorderLayout.SOUTH);

        // - - - center
        scrollPane = new JScrollPane(panelAux);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));

        for(SongBanner sb : controller.getBanners()){
            panelAux.add(sb.getBannerPanel());
        }
        frame.add(scrollPane, BorderLayout.CENTER);

        for(int i = 0; i<controller.getBanners().size(); i++){
            final int no = i;
            controller.getBanners().get(i).getPlayButton().addActionListener(e -> controller.goToSong(no));
        }
        // - - - center

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

    }

    protected void setupDetailsPanel(){
        //detailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        detailsPanel.setLayout(new GridLayout(4, 1));

        JPanelNotOpaque auxPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));

        JLabel title = new JLabel(controller.getAlbum().getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel artist = new JLabel("By "+controller.getAlbum().getArtistName());
        JButton goToArtist = new JButton("Go to artist");
        goToArtist.setPreferredSize(new Dimension(100, 20));
        goToArtist.setFocusable(false);
        goToArtist.addActionListener(e -> controller.goToArtist());
        auxPanel.add(artist);
        auxPanel.add(goToArtist);
        JLabel date = new JLabel();
        date.setText("Published on " + controller.getAlbum().getMonth() +"."+ controller.getAlbum().getYear());
        JLabel genres = new JLabel("Genres:");
        for(String s : controller.getAlbum().getGeneres()){
            genres.setText(genres.getText() + " " + s);
        }

        JPanelNotOpaque titlePanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
        titlePanel.add(title);
        JPanelNotOpaque datePanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
        datePanel.add(date);
        JPanelNotOpaque genresPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
        genresPanel.add(genres);

        detailsPanel.add(titlePanel);
        detailsPanel.add(auxPanel);
        detailsPanel.add(datePanel);
        detailsPanel.add(genresPanel);


    }

    public void closeView(){
        this.frame.setVisible(false);
    }


}
