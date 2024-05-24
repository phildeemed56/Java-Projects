package projectfiles.Dao;

import projectfiles.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of the CustomerDAO interface to handle customer related database operations.
 */
public class CustomerDAOImpl implements CustomerDAO {
    private UserDAO userDAO; // Dependency to handle User related database operations

    /**
     * Constructor to initialize the UserDAO dependency.
     */
    public CustomerDAOImpl() {
        this.userDAO = new UserDAOImpl(); // Initialize with concrete implementation
    }
    
    /**
     * Add a new customer to the Customer database. Takes {@link Customer} object and uses methods in the super class User to add 
     * to CustomerID (userID foreing refference) and password to the user table, the rest of the customer object is added to the customer table.
     * @param customer The customer object to be added.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If the user already exists in the database. Handled by the {@link UserDAO#doesUserExist(String)} method.
     * @return boolean true if the customer was added successfully, false otherwise.
     */
    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        // Check if the user already exists using the provided UserDAO
        // no need to check if user exists as it is handled in the UserDAO
            userDAO.addUser(customer);  // This handles the insertion into the 'users' table
            // SQL statement to insert the new customer into the 'customers' table
            String sql = "INSERT INTO Customer (CustomerId, Email, FirstName, LastName, PhoneNumber, Balance, Country, City, Address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = DatabaseCreds.getConnection();  // Ensure the connection is managed correctly
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, customer.getId());
                pstmt.setString(2, customer.getEmail());
                pstmt.setString(3, customer.getFirstName());
                pstmt.setString(4, customer.getLastName());
                pstmt.setString(5, customer.getPhoneNumber());
                pstmt.setDouble(6, customer.getBalance());
                pstmt.setString(7, customer.getCountry());
                pstmt.setString(8, customer.getCity());
                pstmt.setString(9, customer.getAddress());
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            } 
        }
    /**
     * Customer method for retrieving a {@link Customer} object by their {@link Customer#getId() CustomerID}.
     * @param customerId The ID of the customer to retrieve.
     * @return The customer object if found, otherwise null.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If no customer is found with the given ID.
     */
    @Override
    public Customer getCustomerById(String customerId) throws SQLException {
        String sql = "SELECT CustomerId, Email, FirstName, LastName, PhoneNumber, Balance, Country, City, Address FROM Customer WHERE CustomerId = ?";  // Exclude password for security
        try (Connection conn = DatabaseCreds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getString("CustomerId"),
                        "",
                        1,
                        rs.getString("Email"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("PhoneNumber"),
                        rs.getDouble("Balance"),
                        rs.getString("Country"),
                        rs.getString("City"),
                        rs.getString("Address")
                    );
                } else {
                    throw new SQLException("No Customer found with ID: " + customerId);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }
   
    }

    /**
     * Update the details of a customer in the Customer datbase.Takes {@link Customer} object forces new values  into the database 
     * where CustomerID = customer.getID(). See {@link Customer#getId() Customer.getId()}
     * @param customer The customer object to be updated.
     * @throws SQLException If an error occurs during the database operation.
     * @return void
     */
    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        if (userDAO.doesUserExist(customer.getId())) { //check if the customer exists?
            String sql = "UPDATE Customer SET Email = ?, FirstName = ?, LastName = ?, PhoneNumber = ?, Balance = ?, Country = ?, City = ?, Address = ? WHERE CustomerId = ?";
            try (Connection conn = DatabaseCreds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, customer.getEmail());
                pstmt.setString(2, customer.getFirstName());
                pstmt.setString(3, customer.getLastName());
                pstmt.setString(4, customer.getPhoneNumber());
                pstmt.setDouble(5, customer.getBalance());
                pstmt.setString(6, customer.getCountry());
                pstmt.setString(7, customer.getCity());
                pstmt.setString(8, customer.getAddress());
                pstmt.setString(9, customer.getId());
                pstmt.executeUpdate();
                
            } 
        } else {
            throw new SQLException("No Customer found with ID: " + customer.getId());
        }

    }
}
