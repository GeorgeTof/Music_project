package view;

import controller.FindController;
import model.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FindView {
    FindController controller = new FindController();


    JPanelNotOpaque mainPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque inputPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque outputPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JFrame frame = new JFrame();

    JButton backButton = new JButton("Back to menu");
    JPanelNotOpaque backPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));

    JScrollPane scrollPane;

    public FindView(UserProfile profile){
        controller.setProfile(profile);
        controller.setView(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700, 600));
        frame.setLocation(300, 150);


        setupInputPanel();


        scrollPane = new JScrollPane(outputPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);

        mainPanel.setLayout(new GridLayout(2, 1, 10 ,10));
        mainPanel.add(inputPanel);
        mainPanel.add(scrollPane);

        backButton.setPreferredSize(new Dimension(150, 30));
        backButton.addActionListener(e -> controller.backToMenu());
        backButton.setFocusable(false);
        backPanel.add(backButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(backPanel, BorderLayout.SOUTH);


        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

    }


    public void updateOutputPanel(ArrayList<SongBanner> sBanners){

        System.out.println("Update songs");
        outputPanel.removeAll();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        for(SongBanner sb : sBanners){
            outputPanel.add(sb.getBannerPanel());
        }

        for(int i = 0; i<sBanners.size(); i++){
            final int no = i;
            sBanners.get(i).getPlayButton().addActionListener(e -> controller.goToSong(no));
        }

        outputPanel.revalidate();
        outputPanel.repaint();

    }

    public void updateOutputPanel(ArrayList<ArtistBanner> aBanners, String nullString){
        System.out.println("Update artists");
        outputPanel.removeAll();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        for(ArtistBanner ab : aBanners){
            outputPanel.add(ab.getBannerPanel());
        }

        for(int i = 0; i<aBanners.size(); i++){
            final int no = i;
            aBanners.get(i).getPlayButton().addActionListener(e -> controller.goToArtist(no));
        }

        outputPanel.revalidate();
        outputPanel.repaint();

    }

    public void updateOutputPanel(ArrayList<AlbumBanner> aBanners, int nullInt){

        System.out.println("Update songs");
        outputPanel.removeAll();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        for(AlbumBanner ab : aBanners){
            outputPanel.add(ab.getBannerPanel());
        }

        for(int i = 0; i<aBanners.size(); i++){
            final int no = i;
            aBanners.get(i).getPlayButton().addActionListener(e -> controller.goToAlbum(no));
        }

        outputPanel.revalidate();
        outputPanel.repaint();

    }



    protected JTextField textField = new JTextField();
    protected JComboBox<String> searchInComboBox = new JComboBox<>(new String[]{"Songs", "Artists", "Albums"});
    protected JComboBox<String> sortByComboBox = new JComboBox<>(new String[]{"Name", "Year"});
    protected JComboBox<String> sortOrderComboBox = new JComboBox<>(new String[]{"Asc.", "Desc."});
    protected JComboBox<String> filter1ComboBox = new JComboBox<>(new String[]{"None", "rock", "jazz", "pop", "hard rock", "blues", "experimental", "pop-punk"});
    protected JComboBox<String> filter2ComboBox = new JComboBox<>(new String[]{"None", "2010s", "2000s", "90s", "80s", "70s", "Older"});

    protected void setupInputPanel(){
        JLabel label1 = new JLabel("Search for ");
        JLabel labelIn = new JLabel("Search in ");
        JLabel labelSort = new JLabel("Sort by ");
        JLabel labelFilter = new JLabel("Filter by ");

        textField.setPreferredSize(new Dimension(250, 20));

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 20));
        searchButton.addActionListener(e -> controller.search());

        JPanelNotOpaque p1 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
        p1.add(label1);
        p1.add(textField);
        p1.add(searchButton);

        JPanelNotOpaque p2 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
        p2.add(labelIn);
        p2.add(searchInComboBox);

        JPanelNotOpaque p3 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
        p3.add(labelSort);
        p3.add(sortByComboBox);
        p3.add(sortOrderComboBox);

        JPanelNotOpaque p4 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
        p4.add(labelFilter);
        p4.add(filter1ComboBox);
        p4.add(filter2ComboBox);

        inputPanel.add(p1);
        inputPanel.add(p2);
        inputPanel.add(p3);
        inputPanel.add(p4);

    }

    public String getSearchText() { return textField.getText(); }
    public String getSearchIn(){
        return (String) searchInComboBox.getSelectedItem();
    }
    public String getOrder(){
        return (String) sortOrderComboBox.getSelectedItem();
    }
    public String getSortBy(){
        return (String) sortByComboBox.getSelectedItem();
    }
    public String getFilter1(){
        return (String) filter1ComboBox.getSelectedItem();
    }
    public String getFilter2(){
        return (String) filter2ComboBox.getSelectedItem();
    }


    public void closeView(){
        this.frame.setVisible(false);
    }

}
