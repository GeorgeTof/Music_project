package repository;

import model.DBcredentials;
import model.Review;
import model.SongData;
import model.UserProfile;

import java.sql.*;
import java.util.ArrayList;

public class SongPageRepo {

    String url = DBcredentials.URL.value;
    String dbPass = DBcredentials.PASSWORD.value;
    String dbUser = DBcredentials.USER.value;

    public ArrayList<Review> getReviews(int userId, int songId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Review> reviews = new ArrayList<Review>();

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select r.rating, r.reviewtext, a.username, u.nickname from reviews r join \"user\" u on r.userid = u.userid join account a on a.accountid = u.accountid where songid = ? and r.userid <> ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, songId);
            preparedStatement.setInt(2, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            int result = 0;
            while (resultSet.next()) {
                Review r = new Review();
                r.setText(resultSet.getString("reviewtext"));
                r.setRating(resultSet.getInt("rating"));
                r.setUsername(resultSet.getString("username"));
                r.setNickname(resultSet.getString("nickname"));

                System.out.println("Fetched "+r.getText()+" successfully");
                reviews.add(r);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return reviews;
            } catch (SQLException e) {
                e.printStackTrace();
                return reviews;
            }
        }
    }


    public int addReview(int rating, String text, int userId, int songId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = -1;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "insert into reviews (rating, reviewtext, userid, songid) values (?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, rating);
            preparedStatement.setString(2, text);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, songId);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error DB at creating review");
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                System.out.println(rowsAffected + " Rows Affected. Success! Connection Closed!");
                return rowsAffected;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error at closing DB at creating review");
                return -1;
            }
        }
    }

    public Review getThisReview(int userId, int songId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Review thisReview = null;

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select r.rating, r.reviewtext, a.username, u.nickname, r.reviewid from reviews r join \"user\" u on r.userid = u.userid join account a on a.accountid = u.accountid where songid = ? and r.userid = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, songId);
            preparedStatement.setInt(2, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                thisReview = new Review();
                thisReview.setText(resultSet.getString("reviewtext"));
                thisReview.setRating(resultSet.getInt("rating"));
                thisReview.setRevId(resultSet.getInt("reviewid"));
                thisReview.setUsername(resultSet.getString("username"));
                thisReview.setNickname(resultSet.getString("nickname"));

                System.out.println("Fetched "+thisReview.getText()+" successfully");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                return thisReview;
            } catch (SQLException e) {
                e.printStackTrace();
                return thisReview;
            }
        }
    }

    public int deleteReview(int revId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = -1;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "delete from reviews where reviewid = ?;";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, revId);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error DB at deleting review");
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                System.out.println(rowsAffected + " Rows Affected. Success! Connection Closed!");
                return rowsAffected;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error at closing DB at deleting review");
                return -1;
            }
        }
    }

    public int removeFromLiked(int sid, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = -1;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "delete from likedsongs where userid = ? and songid = ?;";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, sid);


            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error DB at deleting from liked");
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                System.out.println(rowsAffected + " Rows Affected. Success! Connection Closed!");
                return rowsAffected;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error at closing DB at deleting liked");
                return -1;
            }
        }
    }

    public int addToLiked(int sid, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = -1;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "insert into likedsongs (userid, songid) values (?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, sid);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error DB at creating review");
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                System.out.println(rowsAffected + " Rows Affected. Success! Connection Closed!");
                return rowsAffected;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error at closing DB at creating review");
                return -1;
            }
        }
    }
}
