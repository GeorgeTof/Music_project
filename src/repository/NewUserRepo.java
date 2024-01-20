package repository;

import model.DBcredentials;

import java.sql.*;

public class NewUserRepo {
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
            preparedStatement.setInt(3, 1);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error DB at inserting account");
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
                System.out.println("Error at closing DB at inserting account");
                return -1;
            }
        }
    }

    public int addUser(int accId, String nickname){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = -1;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "INSERT INTO \"user\" (nickname, accountid) VALUES (?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, nickname);
            preparedStatement.setInt(2, accId);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error DB at creating user");
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
                System.out.println("Error at closing DB at inserting user");
                return -1;
            }
        }
    }

}
