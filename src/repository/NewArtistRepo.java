package repository;

import model.DBcredentials;

import java.sql.*;

public class NewArtistRepo {
    String url = DBcredentials.URL.value;
    String dbUser = DBcredentials.USER.value;
    String dbPass = DBcredentials.PASSWORD.value;

    public int verifyUser(String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select username, accountid from account where username = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("All users are:");
            while (resultSet.next()) {
                String usernamew = resultSet.getString("username");
                System.out.println("User " + usernamew + " already exists ");
                result =  resultSet.getInt("accountid");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            result = -1;
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                result = -1;
            }
        }
        return result;
    }

    public int addAccount(String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = -1;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "INSERT INTO account (username, password, type) VALUES (?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 2);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error DB at inserting account");
            rowsAffected = -1;
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                System.out.println(rowsAffected + " Rows Affected. Success! Connection Closed!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error at closing DB at inserting account");
                rowsAffected = -1;
            }
        }
        return rowsAffected;

    }

    public int fetchCountryId(String country) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select countryid from country where cname = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, country);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result =  resultSet.getInt("countryid");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            result = -1;
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                result = -1;
            }
        }
        return result;
    }

    public int addArtist(int accountId, String aName, int isBand, int countryId, String bio) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = -1;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "INSERT INTO artist (aname, accountid, isband, countryid, bio) VALUES (?, ?, ?, ? ,?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, aName);
            preparedStatement.setInt(2, accountId);
            preparedStatement.setInt(3, isBand);
            preparedStatement.setInt(4, countryId);
            preparedStatement.setString(5, bio);


            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            rowsAffected = -1;
            System.out.println("Error DB at creating artist");
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                System.out.println(rowsAffected + " Rows Affected. Success! Connection Closed!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error at closing DB at inserting artist");
                rowsAffected = -1;
            }
            return rowsAffected;
        }
    }
}
