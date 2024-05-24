package projectfiles.Dao;

import java.sql.SQLException;

import projectfiles.model.RemittanceList;

public interface RemittanceListDAO {

    /**
     * Retrieves a list of remittance objets by Customer ID
     * @param customerId The ID of the customer to retrieve remittances for.
     * @return A list of remittance objects.
     */
    public RemittanceList getRemittanceList(String customerId) throws SQLException;



}