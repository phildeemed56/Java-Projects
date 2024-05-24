package projectfiles.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import projectfiles.model.Recipient;

public class RecipientDAOImpl implements RecipientDAO {
    //dont need userDAO here
    //private UserDAO userDAO; //Depeendency to handle user replated database operaetion

    /**
     * Adds a new {@link recipient} object to the database.
     * @param recipient the {@link Recipient} object to be added.
     * @throws SQLException If an error occurs during the database operation.
     * @return An integre contining the databse generaeted ReceipientID for the newly added recipient object.
     */
    @Override
    public int addRecipient(Recipient recipient) throws SQLException {
        String sql = "INSERT INTO Recipient (FirstName, LastName, PhoneNumber, Email, Country, City, Address) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseCreds.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, recipient.getFirstName());
            pstm.setString(2, recipient.getLastName());
            pstm.setString(3, recipient.getPhoneNumber());
            pstm.setString(4, recipient.getEmail());
            pstm.setString(5, recipient.getCountry());
            pstm.setString(6, recipient.getCity());
            pstm.setString(7, recipient.getAddress());
            pstm.executeUpdate();
            
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {//try with resources
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Assuming the auto-incremented key is the first column
                } else {
                    throw new SQLException("Creating recipient failed, no ID obtained.");
                }
            }
        }

    }

    /**
     * Recipient method for retrieving a {@link Recipient} object by their {@link Recipient# RecipientId}.
     * @param recipientId The ID of the Recipient to retrieve.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If no recipient is found with the given ID.
     * @return The Recipient object
     */
    @Override
    public Recipient getRecipientById(int recipientId) throws SQLException {
        String sql = "SELECT * FROM Recipient WHERE RecipientId = ?";

        try (Connection conn = DatabaseCreds.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {//try with resources
            pstm.setInt(1, recipientId);
            ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    Recipient recipient = new Recipient();
                    recipient.setId(rs.getInt("RecipientId"));
                    recipient.setFirstName(rs.getString("FirstName"));
                    recipient.setLastName(rs.getString("LastName"));
                    recipient.setPhoneNumber(rs.getString("PhoneNumber"));
                    recipient.setEmail(rs.getString("Email"));
                    recipient.setCountry(rs.getString("Country"));
                    recipient.setCity(rs.getString("City"));
                    recipient.setAddress(rs.getString("Address"));
                    return recipient;
                } else {
                    throw new SQLException("No recipient found with ID: " + recipientId);
                }
            
        }
   
    }

    /**
     * DEPRECATED. Do not use.
     * Update the details of a recipient in the Recipient database. Takes {@link Recipient} object and updates the values in the database where RecipientID = recipient.getID().
     * @param recipient The recipient object to be updated.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If the recipient does not exist in the database. Handled by the {@link RecipientDAO#getRecipientById(int)} method.
     * @return void
     */
    @Override
    public boolean updateRecipient(Recipient recipient) throws SQLException {
        String sql = "UPDATE Recipient SET FirstName = ?, LastName = ?, PhoneNumber = ?, Email = ?, Country = ?, City = ?, Address = ? WHERE RecipientId = ?";

        try (Connection conn = DatabaseCreds.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {//try with resources
            pstm.setString(1, recipient.getFirstName());
            pstm.setString(2, recipient.getLastName());
            pstm.setString(3, recipient.getPhoneNumber());
            pstm.setString(4, recipient.getEmail());
            pstm.setString(5, recipient.getCountry());
            pstm.setString(6, recipient.getCity());
            pstm.setString(7, recipient.getAddress());
            pstm.setInt(8, recipient.getId());
            pstm.executeUpdate();
           
            if (pstm.getUpdateCount() == 0) {
                throw new SQLException("SQL Error: No Recipient with ID " + recipient.getId() + " found. ");
            }else   {
            return true;
            
        }

    }
}

    /**
     * Delete a recipient from the database.
     * @param recipientId The ID of the recipient to be deleted.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If no recipient is found with the given ID.
     * @return boolean value indicating if the recipient was successfully deleted.
     */
    @Override
    public boolean deleteRecipient(int recipientId) throws SQLException {
        String sql = "DELETE FROM Recipient WHERE RecipientId = ?";

        try (Connection conn = DatabaseCreds.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {//try with resources
            pstm.setInt(1, recipientId);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No recipient found with ID: " + recipientId);
            } else {
                return true;
            }
        }
    }
    
}
