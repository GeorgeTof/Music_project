package repository;

import model.DBcredentials;

import java.sql.*;

public class LoginRepo {
    String url = DBcredentials.URL.value;
    String dbPass = DBcredentials.PASSWORD.value;
    String dbUser = DBcredentials.USER.value;

    public int verifyLogin(String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select * from account where username = ? and password = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String usernamew = resultSet.getString("username");
                String passwordw = resultSet.getString("password");
                System.out.println(usernamew + "         " + passwordw);
                result = resultSet.getInt("type");
            }
            System.out.println(" ");

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
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

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
            while (resultSet.next()) {
                String usernamew = resultSet.getString("username");
                System.out.println("User " + usernamew + " found at " + resultSet.getInt("accountid"));
                result = resultSet.getInt("accountid");
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
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public String verifyArtist(String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String resultName = null;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select a.artistid, a.aname, ac.accountid from artist a join account ac on ac.accountid = a.accountid where ac.username = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultName = resultSet.getString("aname");
                System.out.println("Artist " + resultName + " found at " + resultSet.getInt("accountid"));
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
                return resultName;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


}
