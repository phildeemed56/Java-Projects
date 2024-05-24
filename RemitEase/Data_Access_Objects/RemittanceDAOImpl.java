package projectfiles.Dao;


import projectfiles.model.Customer;
import projectfiles.model.Remittance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RemittanceDAOImpl implements RemittanceDAO {

    /**
     * Adds a new remittance object to the database.
     * @param remittance the {@link Remittance} object to be added.
     * @throws SQLException If an error occurs during the database operation.
     * @return An integer containing the database generated TransactionId for the newly added remittance object.
     */
    @Override
    public int addRemittance(Remittance remittance) throws SQLException {
String sql = "INSERT INTO Remittance (SenderId, RecipientId, PartnerId, AmountSent, AmountReceived, SourceCurrency, TargetCurrency, Status, CancellationReason, ConfirmationDetails) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseCreds.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, remittance.getSenderID().getId()); // Assuming getSenderID returns a Customer object with an getId method
            stmt.setInt(2, remittance.getRecipientID().getId()); // Assuming getRecipientID returns a Recipient object with an getId method
            stmt.setString(3, remittance.getPartnerID().getId()); // Assuming getPartnerID returns a Partner object with an getId method
            stmt.setDouble(4, remittance.getAmountSent());
            stmt.setDouble(5, remittance.getAmountReceived());
            stmt.setString(6, remittance.getSourceCurrency());
            stmt.setString(7, remittance.getTargetCurrency());
            stmt.setString(8, remittance.getStatus());
            stmt.setString(9, remittance.getCancellationReason());
            stmt.setString(10, remittance.getConfirmationDetails());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Assuming the auto-incremented key is the first column
                } else {
                    
                   throw new SQLException("Creating remittance failed, no ID obtained.");
                } 
            } 
            
        
        }catch (SQLException e) {
            throw new SQLException("SQL Error: " + e.getMessage());
        }
            
            
    }

    /**
     * Remittance method for retrieving a {@link Remittance} object by their {@link Remittance#getRemittanceId() RemittanceId}.
     * @param remittanceId The RemittanceId of the remittance to be retrieved.
     * @throws SQLException If an error occurs during the database operation.
     * @return A {@link Remittance} object with the given RemittanceId.
     */
    @Override
    public Remittance getRemittanceById(int remittanceId) throws SQLException {
        String sql = "SELECT * FROM Remittance WHERE TransactionId = ?";
        //isntation DAO for customer and partner and recipient
        CustomerDAO customerDAO = new CustomerDAOImpl();
        PartnerDAO partnerDAO = new PartnerDAOImpl();
        RecipientDAO recipientDAO = new RecipientDAOImpl();

        try (Connection conn = DatabaseCreds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, remittanceId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Remittance(
                    rs.getInt("TransactionId"),
                    customerDAO.getCustomerById(rs.getString("SenderId")),
                    recipientDAO.getRecipientById(rs.getInt("RecipientId")),
                    partnerDAO.getPartnerById(rs.getString("PartnerId")),
                    rs.getDouble("AmountSent"),
                    rs.getDouble("AmountReceived"),
                    rs.getString("SourceCurrency"),
                    rs.getString("TargetCurrency"),
                    rs.getString("Status"),
                    rs.getTimestamp("CreatedAt").toLocalDateTime(),
                    rs.getTimestamp("UpdatedAt").toLocalDateTime(),
                    rs.getString("CancellationReason"),
                    rs.getString("ConfirmationDetails")
                );
            } else {
                throw new SQLException("SQL Error: No remittance with ID " + remittanceId + " found. ");
            }
        }
 
    }

    /**
     * Upadate the details of a remittance in the REmittance table. Takes {@link Remittance} and {@link Customer#getId() } to force new values into the correct remittance transaction record
     * @param remittance A Remittance object contianing at least the RemittanceId and the other values to be updated. 
     * @throws SQLException IF an error occurs when doing database operation.
     * @return void
     */
    @Override
    public void updateRemittance(Remittance remittance) throws SQLException {
        // No need to check if user remittance ID exists, handled by the SQL execption
        String sql = "UPDATE Remittance SET SenderId = ?, RecipientId = ?, PartnerId = ?, AmountSent = ?, AmountReceived = ?, SourceCurrency = ?, TargetCurrency = ?, Status = ?, CancellationReason = ?, ConfirmationDetails = ? WHERE TransactionId = ?";

        try (Connection conn = DatabaseCreds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, remittance.getSenderID().getId()); // Not sending the entire object, just the ID strign. {@phildeemed56}
            stmt.setInt(2, remittance.getRecipientID().getId()); // Not sending the entire object, just the ID strign.
            stmt.setString(3, remittance.getPartnerID().getId()); // Not sending the entire object, just the ID strign.
            stmt.setDouble(4, remittance.getAmountSent());
            stmt.setDouble(5, remittance.getAmountReceived());
            stmt.setString(6, remittance.getSourceCurrency());
            stmt.setString(7, remittance.getTargetCurrency());
            stmt.setString(8, remittance.getStatus());
            stmt.setString(9, remittance.getCancellationReason());
            stmt.setString(10, remittance.getConfirmationDetails());
            stmt.setInt(11, remittance.getTransactionId());
            stmt.executeUpdate();
            
            if (stmt.getUpdateCount() == 0) {
                throw new SQLException("SQL Error: No remittance with ID " + remittance.getTransactionId() + " found. ");
            }
        }
    }

}
