package projectfiles.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to handle the database credentials and database secrets.
 */
public class DatabaseCreds {
    //no need to document static final as they are only visible within the class
    private static final String URL = "jdbc:mysql://remitease.cr2esock8dpy.us-west-2.rds.amazonaws.com:3306/RemitEaseDev?useSSL=false&serverTimezone=UTC";
    private static final String USER = "admin";
    private static final String PASSWORD = "rEmitEase$";

    static {
        try {
            // This explicitly loads the JDBC driver class to ensure it is registered.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    /**
     * Gets a connection to the database using the predefined credentials.
     * @return A connection to the database
     * @throws SQLException If a database access error occurs or the url is null
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

