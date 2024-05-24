package projectfiles.Dao;

import java.sql.SQLException;

import projectfiles.model.Customer;


/**
 * The customerDAO intereface provides the methods that must be implemented by any class that will provide the data access object for the Customer model.
 * Will define the CRUD operations for the {@link Customer} objects.
 * {@phildeemed56 Hi philiip, I wonder what this does}
 */
public interface CustomerDAO {
    /**
     * Adds a new customer object to the database.
     * @param customer the {@link Customer} object to be added.
     * @throws SQLException If an error occurs during the database operation.
     * @return boolean true if the customer was added successfully, false otherwise.
     */
	boolean addCustomer(Customer customer) throws SQLException; 

    /**
     * Customer method for retrieving a {@link Customer} object by their {@link Customer#getId() CustomerID}.
     * @param customerId The ID of the customer to retrieve.
     * @return The customer object if found, otherwise null.
     */
    Customer getCustomerById(String customerId) throws SQLException;

    /**
     * Update the details of a customer in the Customer datbase.Takes {@link Customer} object forces new values  into the database 
     * where CustomerID = customer.getID(). See {@link Customer#getId() Customer.getId()}
     * @param customer The customer object to be updated.
     * @throws SQLException If an error occurs during the database operation.
     * @return void
     */
    void updateCustomer(Customer customer) throws SQLException;
}
