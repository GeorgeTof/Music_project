package view;

import model.Review;
import model.SongData;
import model.UserProfile;

import javax.swing.*;
import java.awt.*;

public class SongPageReviewedView extends SongPageView{

    @Override
    protected void setupThisReviewPanel(){
        System.out.println("This review is: "+controller.getMyReview().getText());
        JLabel reviewTitleLabel = new JLabel("My review: ");
        JLabel ratingLabel = new JLabel(controller.getMyReview().getRating()+" / 5");
        JTextArea reviewTextArea = new JTextArea(controller.getMyReview().getText());
        JButton deleteReviewButton = new JButton("Delete my review");
        deleteReviewButton.setFocusable(false);
        deleteReviewButton.setPreferredSize(new Dimension(150, 20));
        deleteReviewButton.addActionListener(e -> controller.removeReview());
        // action

        thisReviewPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        thisReviewPanel.add(reviewTitleLabel);
        thisReviewPanel.add(ratingLabel);
        thisReviewPanel.add(reviewTextArea);
        thisReviewPanel.add(deleteReviewButton);


    }

    public SongPageReviewedView(UserProfile userProfile, SongData song, Review review) {
        super(userProfile, song, review);
    }

}
