package projectfiles.Dao;

import projectfiles.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of the UserDAO interface. Provides method for handlign {@link User} objects in the database
 */
public class UserDAOImpl implements UserDAO {

    /**
     * Adds a new {@link User} object to the database. 
     * First checks if the User object already exists in the database using User methods to get ID. See {@link User#getId() User.getId()}. 
     * @param user The user object to be added.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If the user already exists in the database.
     * @return void
     */
    @Override
    public void addUser(User user) throws SQLException {
        if (!doesUserExist(user.getId())) {
            String sql = "INSERT INTO User (UserId, Password, PermissionLevel) VALUES (?, ?, ?)";
            try  (Connection conn = DatabaseCreds.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getId());
                pstmt.setString(2, user.getPassword());
                pstmt.setInt(3, user.getPermissionLevel());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println( e.getMessage());
                throw e;
            }
        } else {
            throw new SQLException("User already exists with ID: " + user.getId());
        } 
        
    }
    
    /**
     * Checks if a user with the given ID exists in the database.
     * Also used in the DAO for User inhereted classes.
     * @param userId a String Containig the ID of the user to check.
     * @return true if the user exists, false otherwise.
     * @throws SQLException If an error occurs during the database operation.
     */
    @Override 
    public boolean doesUserExist(String userId) throws SQLException {
        String sql = "SELECT 1 FROM User WHERE UserId = ?";
        try (Connection conn = DatabaseCreds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if there is at least one result, meaning the user ID exists.
            }
        }finally{   
            //nada
        }
    }

    /**
     * Retrieve a {@link User} object from the database using the {@link User#getId() UserId}.
     * @param userId The ID of the user to retrieve.
     * @return The user object if found, otherwise null.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If no user is found with the given ID.
     * @return User
     */
    @Override
    public User getUserById(String userId) throws SQLException {
        String sql = "SELECT * FROM User WHERE UserId = ?";
        try (Connection conn = DatabaseCreds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                    rs.getString("UserId"), 
                    rs.getString("Password"),
                    rs.getInt("PermissionLevel"));
                } else {
                    throw new SQLException("No User found with ID: " + userId);
                }
            } 
        } 
    }
       

    /**
     * Update the password of a user in the User database.
     * @param userId The ID of the user to update.
     * @param newPassword The new password to set for the user.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If no user is found with the given ID.
     * @return void
     */
    @Override
    public void updatePassword(String userId, String newPassword) throws SQLException {
        // Assume getUserById is a method to retrieve a User object from the database
        User user = getUserById(userId);
        if (user != null) {
            user.setPassword(newPassword);  // Update the model
            updatePasswordInDb(user);       // Persist changes
        } 
    }
    
    private void updatePasswordInDb(User user) throws SQLException {
        String sql = "UPDATE User SET Password = ? WHERE UserId = ?";
        try (Connection conn = DatabaseCreds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getId());
            int updatedRows = pstmt.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException("Updating password failed, no rows affected.");
            }
        }

    }
}