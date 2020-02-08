package booking.timer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {

    private static final String DB_URL = "jdbc:sqlite:passwords.db";
    private static DatabaseHandler handler = null;
    private static Connection conn = null;
    private static Statement stmt = null;

    {
        createConnection();
        setUpPasswordTable();
    }

    private DatabaseHandler() {
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    private static void createConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL);

            System.out.println("Connection to database Successful");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void setUpPasswordTable() {
        try {
            stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS PASSWORDS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "password text NOT NULL UNIQUE" +
                    ");");
            System.out.println("password table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Connection getConn() {
        return conn;
    }

}
