package repository;

import model.DBcredentials;
import model.SongData;
import model.Review;

import java.sql.*;
import java.util.ArrayList;

public class LikedSongsRepo {
    String url = DBcredentials.URL.value;
    String dbPass = DBcredentials.PASSWORD.value;
    String dbUser = DBcredentials.USER.value;

    public Review getPersonalReview(int songID, int userId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Review review = null;

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select * from reviews where userid = ? and songid = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, songID);


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                review = new Review();
                review.setRating(resultSet.getInt("rating"));
                review.setText(resultSet.getString("reviewtext"));
                review.setRevId(resultSet.getInt("reviewid"));

                System.out.println("Fetched review "+resultSet.getInt("reviewid")+" successfully");
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
                return review;
            } catch (SQLException e) {
                e.printStackTrace();
                return review;
            }
        }
    }

    public ArrayList<SongData> getSongs(int userId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<SongData> songs = new ArrayList<SongData>();

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select songname, albname, aname, length, a.year, s.songid from likedsongs join songs s on s.songid = likedsongs.songid\n" +
                    "                                                        join album a on s.albumid = a.albumid\n" +
                    "                                                        join artist ar on a.artistid = ar.artistid\n" +
                    "where likedsongs.userid = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            int result = 0;
            while (resultSet.next()) {
                SongData sd = new SongData();
                sd.setName(resultSet.getString("songname"));
                sd.setArtist(resultSet.getString("aname"));
                sd.setAlbum(resultSet.getString("albname"));
                sd.setLength(resultSet.getInt("length"));
                sd.setSid(resultSet.getInt("songid"));
                sd.setYear(resultSet.getInt("year"));

                System.out.println("Fetched "+sd.getName()+" successfully");
                songs.add(sd);
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
                return songs;
            } catch (SQLException e) {
                e.printStackTrace();
                return songs;
            }
        }
    }

}
