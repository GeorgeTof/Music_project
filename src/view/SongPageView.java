package view;

import controller.SongPageController;
import model.Review;
import model.SongData;
import model.UserProfile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SongPageView {
    SongPageController controller;
    Review review = null;

    JFrame frame = new JFrame("Song page");


    JLabel snameLabel = new JLabel();
    JLabel artistLabel = new JLabel();
    JLabel albumLabel = new JLabel();
    JLabel lengthLabel = new JLabel();
    JLabel yearLabel = new JLabel();

    JButton artistButton = new JButton("Check out");
    JButton albumButton = new JButton("Check out");
    JButton likeButton = new JButton("like");
    JButton backButton = new JButton("Back to menu");

    JPanelNotOpaque dataPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque titlePanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque auxDataPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque leftDataPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque midDataPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque rightDataPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque artistPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque albumPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));


    JLabel reviewsLabel = new JLabel("Other Reviews");
    JPanelNotOpaque reviewsPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque tablePanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque backPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable reviewsTable = new JTable(tableModel);



    JPanelNotOpaque thisReviewPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque auxReviewPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JLabel addReviewLabel = new JLabel();
    JButton addreviewButton = new JButton();
    Integer[] ratings = {1, 2, 3, 4, 5};
    JComboBox<Integer> ratingComboBox = new JComboBox<>(ratings);
    JTextField reviewTextField = new JTextField();



    private void setupDataPanel(SongData song){
//        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
        auxDataPanel.setLayout(new GridLayout(1, 2, 10, 10));
        leftDataPanel.setLayout(new BoxLayout(leftDataPanel, BoxLayout.Y_AXIS));
        snameLabel.setText(song.getName());
        snameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        //snameLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(snameLabel);
        dataPanel.setLayout(new BorderLayout());
        dataPanel.add(titlePanel, BorderLayout.NORTH);
        artistLabel.setText("by: " + song.getArtist());
        artistPanel.add(artistLabel, BorderLayout.CENTER);
        artistPanel.add(artistButton, BorderLayout.EAST);
        leftDataPanel.add(artistPanel);
        albumLabel.setText("on album: " + song.getAlbum());
        albumPanel.add(albumLabel, BorderLayout.CENTER);
        albumPanel.add(albumButton, BorderLayout.EAST);
        leftDataPanel.add(albumPanel);
        auxDataPanel.add(leftDataPanel);
        lengthLabel.setText("Length: "+song.getLength()+" minutes");
        lengthLabel.setHorizontalAlignment(JLabel.CENTER);
        yearLabel.setText("Year: "+song.getYear());
        yearLabel.setHorizontalAlignment(JLabel.CENTER);
        midDataPanel.setLayout(new BoxLayout(midDataPanel, BoxLayout.Y_AXIS));
        midDataPanel.add(lengthLabel);
        midDataPanel.add(yearLabel);
        auxDataPanel.add(midDataPanel);
        dataPanel.add(auxDataPanel, BorderLayout.CENTER);
        artistButton.setPreferredSize(new Dimension(105, 20));
        albumButton.setPreferredSize(new Dimension(105, 20));
        likeButton.setPreferredSize(new Dimension(70, 20));
        rightDataPanel.add(likeButton);
        dataPanel.add(rightDataPanel, BorderLayout.EAST);
    }

    protected void setupThisReviewPanel(){
        auxReviewPanel.setPreferredSize(new Dimension(600, 50));
        addReviewLabel.setText("Leave a review:");
        addreviewButton.setText("Post review");
        auxReviewPanel.setLayout(new GridLayout(1, 4));
        JPanelNotOpaque labelPanel = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
        labelPanel.add(addReviewLabel);
        auxReviewPanel.add(labelPanel);
        auxReviewPanel.add(ratingComboBox);
        reviewTextField.setPreferredSize(new Dimension(100, 30));
        auxReviewPanel.add(reviewTextField);
        auxReviewPanel.add(addreviewButton);
        addreviewButton.setFocusable(false);
        addreviewButton.addActionListener(e -> controller.addReview());
        thisReviewPanel.add(auxReviewPanel);
    }



    public SongPageView(UserProfile userProfile, SongData song, Review review) {

        this.createController(userProfile, song, review);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700, 630));
        frame.setLocation(300, 200);
//        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        // - - - top panel - - -
        setupDataPanel(song);
        frame.add(dataPanel);
        artistButton.setFocusable(false);
        albumButton.setFocusable(false);
        likeButton.setFocusable(false);
        artistButton.addActionListener(e -> controller.goToArtist());
        albumButton.addActionListener(e -> controller.goToAlbum());
        likeButton.addActionListener(e -> controller.likePressed());

        // - - - middle panel - - -
        setupThisReviewPanel();
        frame.add(thisReviewPanel);

        // - - - bottom panel - - -
        reviewsPanel.add(reviewsLabel, BorderLayout.NORTH);
        //String[] columnNames = {"user", "nickname", "rating", "review"};
        //tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addColumn("user");
        tableModel.addColumn("nickname");
        tableModel.addColumn("rating");
        tableModel.addColumn("review");
        controller.populateReviews();
        reviewsTable.setPreferredSize(new Dimension(650, 600));
        //tablePanel.add(reviewsTable);
        reviewsPanel.add(reviewsTable, BorderLayout.CENTER);
        frame.getContentPane().add(reviewsPanel);

        // - - - back button - - -
        backButton.setPreferredSize(new Dimension(150, 20));
        backPanel.add(backButton);
        frame.add(backPanel);
        backButton.addActionListener(e -> controller.backToMenu());

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

    }



    public int getComboBoxrating(){
        return (int) ratingComboBox.getSelectedItem();
    }

    public String getReviewText() { return reviewTextField.getText();}

    public void addReview(Object[] rowData){
        this.tableModel.addRow(rowData);
    }

    public void closeView(){
        this.frame.setVisible(false);
    }

    private void createController(UserProfile userProfile, SongData song, Review review){
        this.controller = new SongPageController(this, userProfile, song, review);
    }

}
