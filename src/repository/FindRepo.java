package repository;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class FindRepo {

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

    public ArrayList<SongData> getSongs(String searchtext, String orderBy, String ascDesc, String year){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<SongData> songs = new ArrayList<SongData>();

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);

            String query;

            if(year == null){
                query = "select distinct * from songs s\n" +
                        "                                                        join album al on s.albumid = al.albumid\n" +
                        "                                                        join artist ar on al.artistid = ar.artistid\n" +
                        "where songname like ?  order by " + orderBy + " " + ascDesc ;
            }
            else{
                query = "select distinct * from songs s\n" +
                        "                                                        join album al on s.albumid = al.albumid\n" +
                        "                                                        join artist ar on al.artistid = ar.artistid\n" +
                        "where songname like ? " +  year + " order by " + orderBy + " " + ascDesc ;
            }


            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, searchtext);

            System.out.println("Executing: " + preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
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


    public ArrayList<Album> getAlbums(String searchtext, String genere, String orderBy, String ascDesc, String year) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Album> albums = new ArrayList<Album>();

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);

            String query;

            query = "select distinct albname, aname, al.year, al.albumid from album al\n" +
                    "                           join songs s on s.albumid = al.albumid\n" +
                    "                           join artist ar on al.artistid = ar.artistid\n" +
                    "                            join albumtogenere atg on al.albumid = atg.albumid\n" +
                    "where al.albname like ? " +  ((genere == null)?"":genere)  +  ((year == null)?"":year) + " order by " + orderBy + " " + ascDesc;


            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, searchtext);

            System.out.println("Executing: " + preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Album a = new Album();
                a.setTitle(resultSet.getString("albname"));
                a.setArtistName(resultSet.getString("aname"));
                a.setAlbumId(resultSet.getInt("albumid"));
                a.setYear(resultSet.getInt("year"));

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

    public ArrayList<Artist> getArtists(String searchtext, String ascDesc) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Artist> artists = new ArrayList<Artist>();

        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);

            String query;

            query = "select * from artist join country c on artist.countryid = c.countryid where aname like ? order by aname" + " " + ascDesc;


            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, searchtext);

            System.out.println("Executing: " + preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setArtistId(resultSet.getInt("artistid"));
                artist.setArtistName(resultSet.getString("aname"));
                artist.setIsBand(resultSet.getInt("isband"));
                artist.setBio(resultSet.getString("bio"));
                artist.setCountry(resultSet.getString("cname"));

                System.out.println("Fetched artist "+resultSet.getInt("artistid")+" successfully");
                artists.add(artist);
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
                return artists;
            } catch (SQLException e) {
                e.printStackTrace();
                return artists;
            }
        }

    }
}
