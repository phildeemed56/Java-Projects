package projectfiles.Dao;

import java.sql.SQLException;

import projectfiles.model.Partner;
import projectfiles.model.Recipient;

/**
 * This class is the Data Access Object (DAO) class that handles the database operations for the {@link Recipient} data model.
 */

public interface RecipientDAO {

        /**
        * Adds a new recipient object to the database.
        * @param recipient the {@link Recipient} object to be added.
        * @throws SQLException If an error occurs during the database operation.
        * @return Returns an integer value containi the database henerated auto incremrne primary key for the added recipient  
        */
        int addRecipient(Recipient recipient) throws SQLException; 
    
        /**
        * Recipient method for retrieving a {@link Recipient} object by their {@link Recipient#getId() RecipientId}.
        * @param recipientId The ID of the Recipient to retrieve.\ 
        * @throws SQLException If an error occurs during the database operation.
        * @throws SQLException If no recipient is found with the given ID.
        * @return The Recipient object 
        */
        Recipient getRecipientById(int recipientId) throws SQLException;
        
    
        /**
        * Update the details of a recipient in the Recipient database. 
        * @param recipient The recipient object to be updated.
        * @throws SQLException If an error occurs during the database operation.
        * @throws SQLException If no recipient is found with the given ID.
        * @return void
        */
        boolean updateRecipient(Recipient recipient) throws SQLException;

        /**
         * Delete a recipient from the database.
         * @param recipientId The ID of the recipient to be deleted.
         * @throws SQLException If an error occurs during the database operation.
         * @throws SQLException If no recipient is found with the given ID.
         * @return boolean value indicating if the recipient was successfully deleted.
         */
        boolean deleteRecipient(int recipientId) throws SQLException;
        

}
