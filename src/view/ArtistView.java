package view;

import controller.ArtistController;
import model.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArtistView {
    ArtistController controller = new ArtistController();

    JLabel titleLabel = new JLabel();
    JFrame frame = new JFrame("Liked songs");
    JButton backButton = new JButton("Back to menu");

    ArrayList<JPanelNotOpaque> songPanels;

    JPanelNotOpaque detailsPanel = new JPanelNotOpaque();

    JPanelNotOpaque panel1 = new JPanelNotOpaque();
    JPanelNotOpaque panelAux = new JPanelNotOpaque();
    JScrollPane scrollPane;

    public ArtistView(UserProfile profile, String artistName){
        controller.fetchArtist(artistName);
        controller.setView(this);
        controller.setProfile(profile);
        controller.fetchAlbums();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700, 450));
        frame.setLocation(300, 200);
        //frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
//        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.setLayout(new BorderLayout());

        this.setupDetailsPanel();
        frame.add(detailsPanel, BorderLayout.NORTH);

        backButton.setFocusable(false);
        JPanelNotOpaque buttonFrame = new JPanelNotOpaque();
        buttonFrame.add(backButton);
        backButton.addActionListener(e -> controller.backToMenu());
        frame.add(buttonFrame, BorderLayout.SOUTH);

        // - - - center
        scrollPane = new JScrollPane(panelAux);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));

        for(AlbumBanner ab : controller.getBanners()){
            panelAux.add(ab.getBannerPanel());
        }
        frame.add(scrollPane, BorderLayout.CENTER);

        for(int i = 0; i<controller.getBanners().size(); i++){
            final int no = i;
            controller.getBanners().get(i).getPlayButton().addActionListener(e -> controller.goToAlbum(no));
        }
        // - - - center

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

    }

    public ArtistView(String artistName){
        controller.fetchArtist(artistName);
        controller.setView(this);
        controller.fetchAlbums();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700, 450));
        frame.setLocation(300, 200);
        //frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
//        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.setLayout(new BorderLayout());

        this.setupDetailsPanelForArtist();
        frame.add(detailsPanel, BorderLayout.NORTH);

        backButton.setFocusable(false);
        backButton.setText("Log out");
        JPanelNotOpaque buttonFrame = new JPanelNotOpaque();
        buttonFrame.add(backButton);
        backButton.addActionListener(e -> controller.logOut());
        frame.add(buttonFrame, BorderLayout.SOUTH);

        // - - - center
        scrollPane = new JScrollPane(panelAux);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));

        for(AlbumBanner ab : controller.getBanners()){
            panelAux.add(ab.getBannerPanel());
        }
        frame.add(scrollPane, BorderLayout.CENTER);

        for(int i = 0; i<controller.getBanners().size(); i++){
            final int no = i;
            //controller.getBanners().get(i).getPlayButton().addActionListener(e -> controller.goToAlbum(no));
        }
        // - - - center

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

    }

    JPanelNotOpaque titlePanel = new JPanelNotOpaque();
    JPanelNotOpaque bioPanel = new JPanelNotOpaque();

    protected void setupDetailsPanel(){
        //detailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        detailsPanel.setLayout(new GridLayout(3, 1));
        JPanelNotOpaque auxPanel = new JPanelNotOpaque();

        JLabel artistName = new JLabel(controller.getArtist().getArtistName());
        artistName.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel country = new JLabel("from "+controller.getArtist().getCountry());


        JLabel isBand = new JLabel();
        isBand.setText( (controller.getArtist().getIsBand()==1)?"Band ":"Single artist " );
        auxPanel.add(isBand);
        auxPanel.add(country);
        JLabel bio = new JLabel(controller.getArtist().getBio());

        titlePanel.add(artistName);
        detailsPanel.add(titlePanel);
        detailsPanel.add(auxPanel);
        bioPanel.add(bio);
        detailsPanel.add(bioPanel);

    }

    protected void setupDetailsPanelForArtist(){
        //detailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        detailsPanel.setLayout(new GridLayout(4, 1));
        JPanelNotOpaque auxPanel = new JPanelNotOpaque();

        JLabel artistName = new JLabel(controller.getArtist().getArtistName());
        artistName.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel country = new JLabel("from "+controller.getArtist().getCountry());


        JLabel isBand = new JLabel();
        isBand.setText( (controller.getArtist().getIsBand()==1)?"Band ":"Single artist " );
        auxPanel.add(isBand);
        auxPanel.add(country);
        JLabel bio = new JLabel(controller.getArtist().getBio());

        JButton editButton = new JButton("Edit bio");
        editButton.setPreferredSize(new Dimension(100, 20));
        editButton.setFocusable(false);
        editButton.addActionListener(e -> controller.editBio());
        JPanelNotOpaque editButtonPanel = new JPanelNotOpaque();

        titlePanel.add(artistName);
        detailsPanel.add(titlePanel);
        detailsPanel.add(auxPanel);
        bioPanel.add(bio);
        detailsPanel.add(bioPanel);

        editButtonPanel.add(editButton);
        detailsPanel.add(editButtonPanel);

    }


    public void closeView(){
        this.frame.setVisible(false);
    }

}
