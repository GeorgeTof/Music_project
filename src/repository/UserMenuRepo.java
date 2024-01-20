package repository;

import model.DBcredentials;
import model.UserProfile;

import java.sql.*;

public class UserMenuRepo {
    String url = DBcredentials.URL.value;
    String dbPass = DBcredentials.PASSWORD.value;
    String dbUser = DBcredentials.USER.value;

    public UserProfile retrieveUser(int accountId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        UserProfile profile = null;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "select * from \"user\" u join account on account.accountid = u.accountid where u.accountid = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, accountId);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Attempting to retrieve user: "+accountId);
            int result = 0;
            while (resultSet.next()) {
                profile = new UserProfile();
                profile.setId(resultSet.getInt("userid"));
                profile.setNickname(resultSet.getString("nickname"));
                profile.setUsername(resultSet.getString("username"));
                System.out.println(profile+" retrieved from db");
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
                return profile;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public int updateNickname(int id, String newNickname) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = -1;
        try{
            connection = DriverManager.getConnection(url, dbUser, dbPass);
            String query = "update \"user\" set nickname = ? where userid = ?";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, newNickname);
            preparedStatement.setInt(2, id);

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
}
