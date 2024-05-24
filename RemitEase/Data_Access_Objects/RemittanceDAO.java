package projectfiles.Dao;

import java.sql.SQLException;

import projectfiles.model.Remittance;

/**
 * This class is the Data Access Object (DAO) class that handles the database operations for the {@link Remittance} data model.
 */
public interface RemittanceDAO {

    /**
     * Adds a new remittance object to the database.
     * @param remittance the {@link Remittance} object to be added.
     * @throws SQLException If an error occurs during the database operation.
     * @return void
     */
    int addRemittance(Remittance remittance) throws SQLException; 
 
    /**
     * Remittance method for retrieving a {@link Remittance} object by their {@link Remittance#getRemittanceId() RemittanceId}.
     * @param RemittanceId The ID of the Remittance to retrieve.
     * @return The Remittance object if found, otherwise null.
     */
    Remittance getRemittanceById(int RemittanceId) throws SQLException;

    /**
     * Update the details of a remittance in the Remittance database. 
     * @param remittance The remittance object to be updated.
     * @throws SQLException If an error occurs during the database operation.
     * @return void
     */
    void updateRemittance(Remittance remittance) throws SQLException;
    

    
}
