package projectfiles.Dao;


import projectfiles.model.Partner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * The PartnerDAO interface provides the methods that must be implemented by any class that will provide the data access object for the Partner model.
 */
public class PartnerDAOImpl implements PartnerDAO {
    private UserDAO userDAO; // Dependency to handle User related database operations
    
    /**
     * Constructor to initialize the UserDAO dependency.
     */
    public PartnerDAOImpl() {
        this.userDAO = new UserDAOImpl(); // Initialize with concrete implementation
    }
    
    /**
     * Add a new partner to the Partner database. Takes {@link Partner} object and uses methods in the super class User to add 
     * to PartnerID (userID foreing refference) and password to the user table, the rest of the partner object is added to the partner table.
     * @param partner The partner object to be added.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If the user already exists in the database. Handled by the {@link UserDAO#doesUserExist(String)} method.
     * @return void
     */
    @Override
    public void addPartner(Partner partner) throws SQLException {
        // Check if the user already exists using the provided UserDAO
       
            // Since the user does not exist, add them to the User table first
            userDAO.addUser(partner);  // This handles the insertion into the 'users' table

            // SQL statement to insert the new customer into the 'customers' table
            String sql = "INSERT INTO Partner (PartnerId, Name, Type, Country, City, Address) VALUES (?, ?, ?, ?, ?, ?)";
            try (Connection conn = DatabaseCreds.getConnection();  // Ensure the connection is managed correctly
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, partner.getId());
                pstmt.setString(2, partner.getName());
                pstmt.setString(3, partner.getType());
                pstmt.setString(4, partner.getCountry());
                pstmt.setString(5, partner.getCity());
                pstmt.setString(6, partner.getAddress());

                pstmt.executeUpdate();
            } 
        
    }

    /**
     * Retrieve a {@link Partner} object from the database using the {@link Partner#getId() PartnerId}.
     * @param countryId The ID of the partner to retrieve.
     * @return The partner object if found, otherwise null.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If no partner is found with the given ID.
     */
    @Override
    public List<Partner> getPartnerByCountry(String countryId) throws SQLException {
        String sql = "SELECT PartnerId, Name, Type, Country, City, Address FROM Partner WHERE Country = ?";  // Exclude password for security
        try (Connection conn = DatabaseCreds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, countryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<Partner> partners = new ArrayList<>();
                while (rs.next()) {
                    Partner partner = new Partner(
                        rs.getString("PartnerId"),
                        "",
                        2,
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getString("Country"),
                        rs.getString("City"),
                        rs.getString("Address")
                    );
                    partners.add(partner);
                }
                return partners;
            }
        }
    }

    /**
     * Update the details of a partner in the Partner database. Takes {@link Partner} object and updates the values in the database where PartnerID = partner.getID().
     * @param partner The partner object to be updated.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If the partner does not exist in the database. Handled by the {@link UserDAO#doesUserExist(String)} method.
     * @return void
     */
    @Override
    public void updatePartner(Partner partner) throws SQLException {
        if (userDAO.doesUserExist(partner.getId())) {//check if user exists
            String sql = "UPDATE Partner SET Name = ?, Type = ?, Country = ?, City = ?, Address = ? WHERE PartnerId = ?";
            try (Connection conn = DatabaseCreds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, partner.getName());
                pstmt.setString(2, partner.getType());
                pstmt.setString(3, partner.getCountry());
                pstmt.setString(4, partner.getCity());
                pstmt.setString(5, partner.getAddress());
                pstmt.setString(6, partner.getId());
                pstmt.executeUpdate();
            } 
            
        }else {
            throw new SQLException("Partner not found with partnerID: "+ partner.getId());
        }
    }



    @Override
    public Partner getPartnerByNameAndCountry(String name, String country) throws SQLException {
        String sql = "SELECT PartnerId, Name, Type, Country, City, Address FROM Partner WHERE Name = ? AND Country = ?";  // Exclude password for security
        try (Connection conn = DatabaseCreds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, country);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Partner partner = new Partner(
                        rs.getString("PartnerId"),
                        "",
                        2,
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getString("Country"),
                        rs.getString("City"),
                        rs.getString("Address")
                    );
                    return partner;
                }
            }
        }
        return null;
        }
    
/**
     * Retrieve a {@link Partner} object from the database using the {@link Partner#getId() PartnerId}.
     * @param countryId The ID of the partner to retrieve.
     * @return The partner object if found, otherwise null.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If no partner is found with the given ID.
     */
    @Override
    public Partner getPartnerById(String countryId) throws SQLException {
        String sql = "SELECT PartnerId, Name, Type, Country, City, Address FROM Partner WHERE PartnerId = ?";  // Exclude password for security
        try (Connection conn = DatabaseCreds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, countryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Partner(
                        rs.getString("PartnerId"),
                        "",
                        2,
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getString("Country"),
                        rs.getString("City"),
                        rs.getString("Address")
                    );
                }else{
                    throw new SQLException("Partner not found with partnerID: "+ countryId );
                    }
                }   
            }
    }

    /**
     * Check if a country is served by a partner.
     * @param country
     * @return
     */
    public boolean countryServed(String country) throws SQLException {
            String sql = "SELECT * FROM Partner WHERE Country = ?";
            try (Connection conn = DatabaseCreds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, country);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }

}

