package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constants.CommonConstants;

public class MyJDBC {
    // we will first start with registering new users to our DB

    public static boolean register(String username, String password) {
        // first check if the username already exists in the database
        // we will make a seprate method to check if the user already exist
        try {
            // the logic is that we will only register if the user does not found in the DB
            if (!checkUser(username)) {
                // connection to the databse
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD);

                // create insert query
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonConstants.DB_USER_TABLE + "(username, password)" + "VALUES(?, ?)");

                insertUser.setString(1, username);
                insertUser.setString(2, password);

                // update db with new user
                insertUser.executeUpdate();

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // check if username already exist in the database
    // false - user does not exist in the DB
    // true - user exist in the DB
    public static boolean checkUser(String username) {
        try {
            // JDBC Server connect
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);

            PreparedStatement checkUsersExist = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USER_TABLE + " WHERE USERNAME = ?");

            // we will replace the "?" with values using the setString()
            checkUsersExist.setString(1, username);

            // then we will store our result in a result set which we will be able to access
            ResultSet resultSet = checkUsersExist.executeQuery();

            // check to see if the result set is empty
            // if it is empty it means that there was no data row that contains the username
            // (Ex- user does not exist)
            // we use the isBeforeFirst() to point to the first row of data that is returned
            // to our result set
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // if the user does not exist in the DB
        return true;
    }

    // validate user login
    // validate login credentials by checking to see if username/password pair
    // exists in the database
    public static boolean validateLogin(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);
            
            // create select query
            PreparedStatement validateUser = connection.prepareStatement(
                "SELECT * FROM " + CommonConstants.DB_USER_TABLE + " WHERE USERNAME = ? AND PASSWORD = ?"
            );

            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();

            //isBeforeFirst is used here to see if your query returned any rows that matched our query
            if(!resultSet.isBeforeFirst()){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // if it is true it means that there was a username/password pair that matched  the user input
        return true;
    }
}
