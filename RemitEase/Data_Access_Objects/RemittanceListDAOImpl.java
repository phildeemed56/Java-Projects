package projectfiles.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import projectfiles.model.Customer;
import projectfiles.model.Partner;
import projectfiles.model.Recipient;
import projectfiles.model.Remittance;
import projectfiles.model.RemittanceList;

public class RemittanceListDAOImpl implements RemittanceListDAO {


    /**
     * Retrieves a list of remittance objets by Customer ID
     * @param customerId The ID of the customer to retrieve remittances for.
     * @return A list of remittance objects.
     * @param customerId
     */
@Override
    public RemittanceList getRemittanceList(String customerId) throws SQLException{
        RemittanceList remittanceList = new RemittanceList();
        String query = "SELECT r.TransactionId, r.AmountSent, r.AmountReceived, r.SourceCurrency, r.TargetCurrency, r.Status, r.CreatedAt, r.UpdatedAt, r.CancellationReason, r.ConfirmationDetails, r.SenderId AS CustomerId, r.RecipientId, r.PartnerId, c.FirstName AS CustomerFirstName, c.LastName AS CustomerLastName, c.Email AS CustomerEmail, c.PhoneNumber AS CustomerPhoneNumber, c.Balance AS CustomerBalance, c.Country AS CustomerCountry, c.City AS CustomerCity, c.Address AS CustomerAddress, rec.FirstName AS RecipientFirstName, rec.LastName AS RecipientLastName, rec.Email AS RecipientEmail, rec.PhoneNumber AS RecipientPhoneNumber, rec.Country AS RecipientCountry, rec.City AS RecipientCity, rec.Address AS RecipientAddress, p.Name AS PartnerName, p.Type AS PartnerType, p.Country AS PartnerCountry, p.City AS PartnerCity, p.Address AS PartnerAddress FROM Remittance r INNER JOIN Customer c ON r.SenderId = c.CustomerId INNER JOIN Recipient rec ON r.RecipientId = rec.RecipientId LEFT JOIN Partner p ON r.PartnerId = p.PartnerId WHERE c.CustomerId = ? ORDER BY\n"
        		+ "    r.TransactionId DESC; ";
        
        try (Connection connection = DatabaseCreds.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Remittance remittance = new Remittance(
                    rs.getInt("TransactionId"),
                    new Customer(
                        rs.getString("CustomerId"),
                        "",
                        1,
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerFirstName"), 
                        rs.getString("CustomerLastName"),
                        rs.getString("CustomerPhoneNumber"), 
                        rs.getDouble("CustomerBalance"),
                        rs.getString("CustomerCountry"), 
                        rs.getString("CustomerCity"), 
                        rs.getString("CustomerAddress")
                    ),
                    new Recipient(
                        rs.getInt("RecipientId"),
                        rs.getString("RecipientEmail"), 
                        rs.getString("RecipientFirstName"),
                        rs.getString("RecipientLastName"),
                        rs.getString("RecipientPhoneNumber"),
                        rs.getString("RecipientCountry"),
                        rs.getString("RecipientCity"), 
                        rs.getString("RecipientAddress")
                    ),
                    new Partner(
                        rs.getString("PartnerId"),
                        "",
                        2,
                        rs.getString("PartnerName"), 
                        rs.getString("PartnerType"),
                        rs.getString("PartnerCountry"),
                        rs.getString("PartnerCity"), 
                        rs.getString("PartnerAddress")
                    ),
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
                remittanceList.addRemittance(remittance);
            }
        } catch (SQLException e) {
            e.printStackTrace();//what dis do?
        }
        return remittanceList;
    }
}

