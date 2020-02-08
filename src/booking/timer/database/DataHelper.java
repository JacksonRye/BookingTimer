package booking.timer.database;

import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class DataHelper {

    public static Boolean addPasswords(HashSet<String> passwords) {
        try {
            for (String password : passwords) {
                PreparedStatement statement = DatabaseHandler.getInstance().getConn().prepareStatement(
                        "INSERT INTO passwords VALUES (?, ?)"
                );
                statement.setString(1, null);
                statement.setString(2, password);
                if (statement.executeUpdate() > 0) continue;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void setPasswordsToView(ObservableList<String> list) {
        try {
            PreparedStatement statement = DatabaseHandler.getInstance().getConn().prepareStatement(
                    "SELECT password FROM passwords"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPasswordValid(String text) {
        try {

            PreparedStatement statement = DatabaseHandler.getInstance().getConn().prepareStatement(
                    "SELECT * FROM PASSWORDS WHERE password = ?"
            );
            statement.setString(1, text);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                statement = DatabaseHandler.getInstance().getConn().prepareStatement(
                        "DELETE FROM PASSWORDS WHERE password = ?"
                );
                statement.setString(1, text);
                return statement.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

