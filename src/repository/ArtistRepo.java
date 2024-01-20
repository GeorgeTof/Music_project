package repository;

import model.Album;
import model.Artist;
import model.DBcredentials;
import model.SongData;

import java.sql.*;
import java.util.ArrayList;

public class ArtistRepo {

    String url = DBcredentials.URL.value;
    String dbPass = DBcredentials.PASSWORD.value;
    String dbUser = DBcredentials.USER.value;

    public Artist getArtist(String artistName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Artist artist = null;

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select * from artist join country c on artist.countryid = c.countryid where aname = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, artistName);


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                artist = new Artist();
                artist.setArtistId(resultSet.getInt("artistid"));
                artist.setArtistName(resultSet.getString("aname"));
                artist.setIsBand(resultSet.getInt("isband"));
                artist.setBio(resultSet.getString("bio"));
                artist.setCountry(resultSet.getString("cname"));

                System.out.println("Fetched artist "+resultSet.getInt("artistid")+" successfully");
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
                return artist;
            } catch (SQLException e) {
                e.printStackTrace();
                return artist;
            }
        }
    }

    public ArrayList<Album> getAlbums(int artistId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Album> albums = new ArrayList<Album>();

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select distinct * from album al join artist ar on al.artistid = ar.artistid where al.artistid = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, artistId);

            ResultSet resultSet = preparedStatement.executeQuery();
            int result = 0;
            while (resultSet.next()) {
                Album a = new Album();

                a.setAlbumId(resultSet.getInt("albumid"));
                a.setArtistName(resultSet.getString("aname"));
                a.setTitle(resultSet.getString("albname"));
                a.setYear(resultSet.getInt("year"));
                a.setMonth(resultSet.getInt("month"));

                System.out.println("Fetched "+a.getTitle()+" successfully");
                albums.add(a);
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
                return albums;
            } catch (SQLException e) {
                e.printStackTrace();
                return albums;
            }
        }

    }

    public int updateBio(String userInput, int artistId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = -1;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "update artist set bio = ? where artistid = ?";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, userInput);
            preparedStatement.setInt(2, artistId);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error DB updating bio");
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
}
