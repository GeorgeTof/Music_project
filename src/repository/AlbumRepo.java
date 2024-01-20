package repository;

import model.Album;
import model.DBcredentials;
import model.Review;
import model.SongData;

import java.sql.*;
import java.util.ArrayList;

public class AlbumRepo {

    String url = DBcredentials.URL.value;
    String dbPass = DBcredentials.PASSWORD.value;
    String dbUser = DBcredentials.USER.value;

    public ArrayList<SongData> getSongs(int albumId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<SongData> songs = new ArrayList<SongData>();

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select distinct songname, albname, aname, length, a.year, s.songid from songs s \n" +
                    "                                                        join album a on s.albumid = a.albumid\n" +
                    "                                                        join artist ar on a.artistid = ar.artistid\n" +
                    "where a.albumid = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, albumId);

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

    public Album getAlbum(String albumName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Album album = null;

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select * from album join artist a on album.artistid = a.artistid where albname = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, albumName);


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                album = new Album();
                album.setAlbumId(resultSet.getInt("albumid"));
                album.setArtistName(resultSet.getString("aname"));
                album.setTitle(resultSet.getString("albname"));
                album.setArtistId(resultSet.getInt("artistid"));
                album.setYear(resultSet.getInt("year"));
                album.setMonth(resultSet.getInt("month"));

                System.out.println("Fetched album "+resultSet.getInt("albumid")+" successfully");
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
                return album;
            } catch (SQLException e) {
                e.printStackTrace();
                return album;
            }
        }
    }

    //select generename from albumtogenere where albumid = 5
    public ArrayList<String> getGeneres(int albumId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> generes = new ArrayList<String>();

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select generename from albumtogenere where albumid = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, albumId);

            ResultSet resultSet = preparedStatement.executeQuery();
            int result = 0;
            while (resultSet.next()) {
                String newGenere = resultSet.getString("generename");

                generes.add(newGenere);

                System.out.println("Fetched "+newGenere+" successfully");
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
                return generes;
            } catch (SQLException e) {
                e.printStackTrace();
                return generes;
            }
        }
    }

}
